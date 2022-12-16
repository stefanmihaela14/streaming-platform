package actions;

import UserMoviesData.User;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;

public class WatchAction extends Action{
    public WatchAction(ActionsInput input) {
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
