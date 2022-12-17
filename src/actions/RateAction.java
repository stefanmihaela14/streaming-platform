package actions;

import UserMoviesData.Movie;
import UserMoviesData.User;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;

import java.util.concurrent.atomic.AtomicInteger;

public class RateAction extends Action {

    public RateAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
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
        if (currentUser.getRatedMovies().contains(currentPage.getMovieList().get(0))) {
            site.showErrorOutput();
            return;
        }
        if(rate > 5 || rate<0){
            site.showErrorOutput();
            return;
        }
        Movie currentMovie = currentPage.getMovieList().get(0);
        currentMovie.getRatingsList().add(rate);
        currentUser.getRatedMovies().add(currentMovie);
        currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);
        AtomicInteger sum = new AtomicInteger();
        currentMovie.getRatingsList().forEach((integer -> {
            sum.set(sum.get() + integer);}));

        currentMovie.setRating((double)sum.get() / (double) currentMovie.getNumRatings());
        site.showOutput();
    }
}
