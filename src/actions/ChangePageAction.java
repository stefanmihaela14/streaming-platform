package actions;

import usermoviesdata.Movie;
import usermoviesdata.User;
import datainput.ActionsInput;
import logic.Database;
import logic.SiteLogic;
import pages.Page;
import pages.PageFactory;

import java.util.ArrayList;

public class ChangePageAction extends Action {

    public ChangePageAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for changing the current page
     * Verify if the action can be done from the current page.
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        SiteLogic siteLogic = SiteLogic.getInstance();
        Page currentPage = SiteLogic.getInstance().getCurrentPage();
        Page newPage = PageFactory.createNew(page);

        ArrayList<String> pagesAccessible = currentPage.getPagesAccessible();
        for (int i = 0; i < currentPage.getPagesAccessible().size(); i++) {
            if (newPage.getPageName().equals(pagesAccessible.get(i))) {
                siteLogic.setCurrentPage(newPage);
                if (newPage.getPageName().equals("movies")) {
                    User currentUser = SiteLogic.getInstance().getCurrentUser();
                    for (int j = 0; j < Database.getInstance().getMovies().size(); j++) {
                        Movie currentMovie = Database.getInstance().getMovies().get(j);
                        if (!currentMovie.getCountriesBanned().contains(currentUser.getCountry())) {
                            newPage.getMovieList().add(currentMovie);
                        }
                    }
                    SiteLogic.getInstance().showOutput();
                }
                return;
            }
        }

        SiteLogic.getInstance().showErrorOutput();
    }
}
