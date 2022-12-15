package logicAndFunctionalities;
import UserMoviesData.Movie;
import UserMoviesData.User;
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
    private ArrayList<Movie> movies = new ArrayList<Movie>();


    /**
     * @return Get Singleton instance.
     */
    public static Database getInstance() {
        if (instance == null) {
            Database.instance = new Database();
        }
        return instance;
    }

    private Database() {

    }
    public void addUser(User user) {
        users.add(user);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
