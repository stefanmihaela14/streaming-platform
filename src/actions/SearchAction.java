package actions;

import usermoviesdata.Movie;
import usermoviesdata.User;
import datainput.ActionsInput;
import logic.Database;
import logic.SiteLogic;
import pages.Page;

import java.util.ArrayList;

public class SearchAction extends Action {

    public SearchAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for when a searching a movie after a string.
     * Verify if the action can be done from the current page.
     * Remove from the movieList the movies that do not contain the searched string
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        if (!name.equals("movies")) {
            site.showErrorOutput();
            return;
        }

        User currentUser = SiteLogic.getInstance().getCurrentUser();
        Page currentPage = site.getCurrentPage();

        currentPage.getMovieList().clear();
        for (int j = 0; j < Database.getInstance().getMovies().size(); j++) {
            Movie currentMovie = Database.getInstance().getMovies().get(j);
            if (!currentMovie.getCountriesBanned().contains(currentUser.getCountry())) {
                currentPage.getMovieList().add(currentMovie);
            }
        }

        ArrayList<Movie> movieList = currentPage.getMovieList();
        ArrayList<Movie> movieListCopy = new ArrayList<Movie>(movieList);

        for (int i = 0; i < movieListCopy.size(); i++) {
            if (!movieListCopy.get(i).getName().startsWith(startsWith)) {
                movieList.remove(movieListCopy.get(i));
            }
        }
        site.showOutput();
    }
}
