package actions;

import datainput.ActionsInput;
import logic.SiteLogic;
import usermoviesdata.Movie;
import usermoviesdata.User;

public class DatabaseDeleteAction extends Action {
    public DatabaseDeleteAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for deleting a movie from the database.
     * Verify if the action can be done.
     * Delete the movie from all of user's lists (Purchased/liked/rated etc.)
     * Gave the tokens/number of free premium movies back.
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        //verify is the movie exists
        Movie movie = null;
        for (Movie currentMovie : site.getDatabase().getMovies()) {
            if (currentMovie.getName().equals(deletedMovie)) {
                movie = currentMovie;
                break;
            }
        }
        if (!site.getDatabase().getMovies().contains(movie)) {
            site.showErrorOutput();
            return;
        }

        for (User currentUser : site.getDatabase().getUsers()) {
            // delete the movie from users lists of Purchased, Watched, Liked and Rated movies
            if (currentUser.getPurchasedMovies().contains(movie)) {
                currentUser.getPurchasedMovies().remove(movie);
            }
            if (currentUser.getWatchedMovies().contains(movie)) {
                currentUser.getWatchedMovies().remove(movie);
            }
            if (currentUser.getLikedMovies().contains(movie)) {
                currentUser.getLikedMovies().remove(movie);
            }
            if (currentUser.getRatedMovies().contains(movie)) {
                currentUser.getRatedMovies().remove(movie);
            }
        }

        //give the money back to people
        for (User currentUser : site.getDatabase().getUsers()) {
            if (currentUser.getAccountType().equals("standard")
                    && currentUser.getPurchasedMovies().contains(movie)) {
                int userTokens = currentUser.getTokensCount();
                currentUser.setTokensCount(userTokens + 2);
            } else if (currentUser.getAccountType().equals("premium")
                    && currentUser.getPurchasedMovies().contains(movie)) {
                int userFreeMovies = currentUser.getNumFreePremiumMovies();
                currentUser.setNumFreePremiumMovies(userFreeMovies + 1);
            }
        }
        // last thing to do: delete the movie from the database
        site.getDatabase().deleteMovie(deletedMovie);
    }
}
