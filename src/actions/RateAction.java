package actions;

import usermoviesdata.Movie;
import usermoviesdata.User;
import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;

import java.util.concurrent.atomic.AtomicInteger;

public class RateAction extends Action {
    private static final int MAX_RATE = 5;

    public RateAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for rating a movie
     * Verify if the action can be done from the current page.
     * Verify if the grade is lower than 5 and recalculate the movie rating average
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        if (!name.equals("seeDetails")) {
            site.showErrorOutput();
            return;
        }

        User currentUser = site.getCurrentUser();
        Page currentPage = site.getCurrentPage();

        if (!currentUser.getWatchedMovies().contains(currentPage.getMovieList().get(0))) {
            site.showErrorOutput();
            return;
        }

        if (rate > MAX_RATE || rate < 0) {
            site.showErrorOutput();
            return;
        }

        String currentMovieName = currentPage.getMovieList().get(0).getName();

        Movie currentMovie = currentPage.getMovieList().get(0);

        if (currentUser.getRatedMovies().contains(currentPage.getMovieList().get(0))) {
            currentPage.getMovieList().get(0).getRatingsList().remove(currentUser.getUserRatedMovies().get(currentMovieName));
        } else {
            currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);
            currentUser.getRatedMovies().add(currentMovie);
        }

        currentUser.getUserRatedMovies().put(currentMovieName, rate);

        currentMovie.getRatingsList().add(rate);
        AtomicInteger sum = new AtomicInteger();
        currentMovie.getRatingsList().forEach((integer -> {
            sum.set(sum.get() + integer); }));
        currentMovie.setRating((double) sum.get() / (double) currentMovie.getNumRatings());
        site.showOutput();
    }
}
