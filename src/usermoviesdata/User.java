package usermoviesdata;
import datainput.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@Setter
@Getter
public class User {
    private static final int NUM_FREE_MOVIES_START = 15;
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;
    private ArrayList<Notification> notifications = new ArrayList<Notification>();
    private LinkedList<String> visitedPagesStack = new LinkedList<>();
    private ArrayList<String> subscribedGenre = new ArrayList<String>();
    private int tokensCount = 0;
    private int numFreePremiumMovies = NUM_FREE_MOVIES_START;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    private HashMap<String, Integer> userRatedMovies = new HashMap<>();

    public User(final String name, final String password, final String country,
                final String accountType, final int balance) {
        this.name = name;
        this.password = password;
        this.country = country;
        this.accountType = accountType;
        this.balance = balance;
    }
    public User(final UserInput user) {
        this.name = user.getCredentials().getName();
        this.password = user.getCredentials().getPassword();
        this.country = user.getCredentials().getCountry();
        this.accountType = user.getCredentials().getAccountType();
        this.balance = user.getCredentials().getBalance();
    }

    /**
     * Check the conditions for sending the update.
     * @param movie that has been added
     */
    public final void updateAboutNewMovie(final Movie movie) {
        for (String genre: subscribedGenre) {
            if (movie.getGenres().contains(genre)
                    && !movie.getCountriesBanned().contains(country)) {
                notifications.add(new Notification(movie.getName(), "ADD"));
                return;
            }
        }
    }

    /**
     * Sending the notification.
     * @param movie that has been deleted
     */
    public final void updateAboutDeletedMovie(final Movie movie) {
                notifications.add(new Notification(movie.getName(), "DELETE"));
                return;
    }
}
