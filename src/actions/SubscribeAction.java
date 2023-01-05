package actions;

import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;
import usermoviesdata.Movie;
import usermoviesdata.User;

import java.util.ArrayList;

public class SubscribeAction extends Action {
    public SubscribeAction(final ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(final SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        User currentUser = site.getCurrentUser();
        Page currentPage = site.getCurrentPage();

        if (!name.equals("seeDetails")) {
            site.showErrorOutput();
            return;
        }

        Movie currentMovie = currentPage.getMovieList().get(0);
        ArrayList<String> genres = new ArrayList<>();

        boolean foundGenre = false;
        for (int i = 0; i < currentMovie.getGenres().size(); i++) {
            String currentGenreName = currentMovie.getGenres().get(i);
            genres.add(currentMovie.getGenres().get(i));
            if (currentGenreName.equals(subscribedGenre)) {
                foundGenre = true;
                break;
            }
        }
        if (!foundGenre) {
            site.showErrorOutput();
            return;
        }

        if (currentUser.getSubscribedGenre().contains(subscribedGenre)) {
            site.showErrorOutput();
            return;
        }

        // if there wasn't any error we add the subscribed genre to the user's genre list
        currentUser.getSubscribedGenre().add(subscribedGenre);

        site.getDatabase().addObserver(currentUser);
    }
}
