package actions;

import usermoviesdata.User;
import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;

public class WatchAction extends Action {
    public WatchAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for when a user watches a movie.
     * Verify if the action can be done from the current page.
     * Add the movie to the current user's watchedMovies list.
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

        if (!currentUser.getPurchasedMovies().contains(currentPage.getMovieList().get(0))) {
            site.showErrorOutput();
            return;
        }

        if (!currentUser.getWatchedMovies().contains(currentPage.getMovieList().get(0))) {
            currentUser.getWatchedMovies().add(currentPage.getMovieList().get(0));
        }

        site.showOutput();
    }
}
