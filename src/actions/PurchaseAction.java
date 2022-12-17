package actions;

import usermoviesdata.User;
import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;

public class PurchaseAction extends Action {

    public PurchaseAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for buying a movie.
     * Verify if the action can be done from the current page.
     * Verify if the movie is already bought and if not, add it to
     * the purchasedMovie list.
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
