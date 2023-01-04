package actions;

import datainput.ActionsInput;
import logic.Database;
import logic.SiteLogic;
import pages.Page;
import pages.PageFactory;
import usermoviesdata.Movie;
import usermoviesdata.User;

public class BackAction extends Action {

    public BackAction(final ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(final SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        User currentUser = site.getCurrentUser();

        if (name.equals("login") || name.equals("register") || name.equals("unauthenticatedPage")) {
            site.showErrorOutput();
            return;
        }

        if (currentUser.getVisitedPagesStack().isEmpty()) {
            site.showErrorOutput();
            return;
        }

        String pageToGoBackTo = currentUser.getVisitedPagesStack().pop();

        // change the current page to the last accessed page
        Page newPage = PageFactory.createNew(pageToGoBackTo);
        site.setCurrentPage(newPage);

        if (newPage.getPageName().equals("movies")) {
            for (int j = 0; j < Database.getInstance().getMovies().size(); j++) {
                Movie currentMovie = Database.getInstance().getMovies().get(j);
                if (!currentMovie.getCountriesBanned().contains(currentUser.getCountry())) {
                    newPage.getMovieList().add(currentMovie);
                }
            }
            SiteLogic.getInstance().showOutput();
        }

        if (newPage.getPageName().equals("seeDetails")) {
            site.showOutput();
        }
    }
}
