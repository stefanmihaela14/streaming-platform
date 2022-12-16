package actions;

import UserMoviesData.Movie;
import UserMoviesData.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;
import pages.PageFactory;

public class SeeDetailsAction extends Action {
    public SeeDetailsAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        if(!name.equals("movies")) {
            site.showErrorOutput();
            return;
        }

        Page currentPage = site.getCurrentPage();
        for (int i = 0; i < currentPage.getMovieList().size(); i++) {
            Movie currentMovie = currentPage.getMovieList().get(i);
            if (currentMovie.getName().equals(movie)) {
                Page newPage = PageFactory.createNew("seeDetails");
                site.setCurrentPage(newPage);
                currentPage.getMovieList().add(currentMovie);
                site.showOutput();
                return;
            }
        }
        site.showErrorOutput();
    }
}
