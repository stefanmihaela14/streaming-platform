package actions;

import usermoviesdata.Movie;
import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;
import pages.PageFactory;
import usermoviesdata.User;

public class SeeDetailsAction extends Action {
    public SeeDetailsAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for seeing moving to the page seeDetails.
     * Verify if the action can be done from the current page.
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        User currentUser = site.getCurrentUser();
        if (!name.equals("movies")) {
            site.showErrorOutput();
            return;
        }

        Page currentPage = site.getCurrentPage();
        for (int i = 0; i < currentPage.getMovieList().size(); i++) {
            Movie currentMovie = currentPage.getMovieList().get(i);
            if (currentMovie.getName().equals(movie)) {
                Page newPage = PageFactory.createNew("seeDetails");
                site.setCurrentPage(newPage);
                site.getCurrentPage().getMovieList().add(currentMovie);

                if (currentUser != null) {
                    currentUser.getVisitedPagesStack().push(currentPage.getPageName());
                }

                site.showOutput();
                return;
            }
        }
        site.showErrorOutput();
    }
}
