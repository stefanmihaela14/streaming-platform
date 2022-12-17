package actions;

import usermoviesdata.Movie;
import usermoviesdata.User;
import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;

public class LikeAction extends Action {
    public LikeAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for liking a movie
     * Verify if the action can be done from the current page.
     * Verify if user has already liked the movie and if not add it to
     * the likedMovies list
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
        if (currentUser.getLikedMovies().contains(currentPage.getMovieList().get(0))) {
            site.showErrorOutput();
            return;
        }

        currentUser.getLikedMovies().add(currentPage.getMovieList().get(0));
        Movie currentMovie = currentPage.getMovieList().get(0);
        currentMovie.setNumLikes(currentMovie.getNumLikes() + 1);

        site.showOutput();
    }
}
