package actions;

import UserMoviesData.Movie;
import UserMoviesData.User;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;

public class PurchaseAction extends Action{
    public PurchaseAction(ActionsInput input) {
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

        for (int i = 0; i < currentUser.getPurchasedMovies().size(); i++) {
            if (currentUser.getPurchasedMovies().get(i).equals(currentPage.getMovieList().get(0))) {
                site.showErrorOutput();
                return;
            }
        }

        if (currentUser.getAccountType().equals("standard")) {
            if (currentUser.getTokensCount() < 2) {
                site.showErrorOutput();
                return;
            }
            currentUser.setTokensCount(currentUser.getTokensCount() - 2);
            currentUser.getPurchasedMovies().add(currentPage.getMovieList().get(0));
        } else if (currentUser.getAccountType().equals("premium")) {
            if (currentUser.getNumFreePremiumMovies() >= 1) {
                    currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
                    currentUser.getPurchasedMovies().add(currentPage.getMovieList().get(0));
            } else {
                if (currentUser.getTokensCount() >= 2) {
                    currentUser.setTokensCount(currentUser.getTokensCount() - 2);
                    currentUser.getPurchasedMovies().add(currentPage.getMovieList().get(0));
                } else {
                    site.showErrorOutput();
                    return;
                }
            }
        }
        site.showOutput();
    }
}
