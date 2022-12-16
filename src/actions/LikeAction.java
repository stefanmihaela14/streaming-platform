package actions;

import UserMoviesData.Movie;
import UserMoviesData.User;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;

public class LikeAction extends Action{
    public LikeAction(ActionsInput input) {
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
