package actions;

import UserMoviesData.Movie;
import UserMoviesData.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logicAndFunctionalities.Database;
import logicAndFunctionalities.SiteLogic;
import pages.Page;

import java.util.ArrayList;

public class SearchAction extends Action {

    public SearchAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        //System.out.println("cevaaaa ajunge aici");
        String name = site.getCurrentPage().getPageName();
        if(!name.equals("movies")) {
            ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
            newNode.put("error", "Error");
            ArrayNode outputArray = newNode.putArray("currentMoviesList");
            newNode.putNull("currentUser");
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
