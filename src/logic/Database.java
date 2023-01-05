package logic;
import usermoviesdata.Movie;
import usermoviesdata.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public final class Database {
    private static Database instance;
    @Getter
    private ArrayList<User> users = new ArrayList<User>();
    @Getter
    private ArrayList<Movie> movies = new ArrayList<Movie>(); //pt observer

    @Getter
    @Setter
    private ArrayList<User> notificationUsers = new ArrayList<>(); //pt observer


    /**
     * @return Get Singleton instance.
     */
    public static Database getInstance() {
        if (instance == null) {
            Database.instance = new Database();
        }
        return instance;
    }

    /**
     * Delete the data in the database after each test due to singleton use
     */
    public static void removeInstance() {
        instance = null;
    }

    private Database() {

    }

    /**
     * Add a new user to the database
     * @param user the user to be added to the database
     */
    public void addUser(final User user) {
        users.add(user);
    }

    /**
     * Add a new movie to the database
     * @param movie the movie to be added to the database
     */
    public void addMovie(final Movie movie) {
        movies.add(movie);

        for (User user : this.notificationUsers) {
            user.updateAboutNewMovie(movie);
        }
    }

    public void deleteMovie(final String movieName) {
        Movie movie = null;
        for (Movie currentMovie : movies) {
            if (currentMovie.getName().equals(movieName)) {
                movie = currentMovie;
                break;
            }
        }

        if (movie == null) {
            return;
        }

        movies.remove(movie);

        for (User user : this.notificationUsers) {
            user.updateAboutDeletedMovie(movie);
        }
    }

    public void addObserver(final User user) {
        if (notificationUsers.contains(user)) {
            return;
        }
        notificationUsers.add(user);
    }
}
