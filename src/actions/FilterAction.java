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

public class FilterAction extends Action {

    public FilterAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        if(!name.equals("movies")) {
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

        if (filters.getContains() != null) {
            for (int i = 0; i < movieListCopy.size(); i++) {
                if (filters.getContains().getActors() != null) {
                    boolean notFoundActor = false;
                    for (int j = 0; j < filters.getContains().getActors().size(); j++) {
                        if (!movieListCopy.get(i).getActors().contains(filters.getContains().getActors().get(j))){
                            notFoundActor = true;
                            break;
                        }
                    }
                    if(notFoundActor){
                        movieList.remove(movieListCopy.get(i));
                        continue;
                    }
                }
                if (filters.getContains().getGenre() != null) {
                    boolean notFoundGenre = false;
                    for (int j = 0; j < filters.getContains().getGenre().size(); j++) {
                        if (!movieListCopy.get(i).getGenres().contains(filters.getContains().getGenre().get(j))){
                            notFoundGenre = true;
                            break;
                        }
                    }
                    if(notFoundGenre){
                        movieList.remove(movieListCopy.get(i));
                        continue;
                    }
                }
            }
        }
        if (filters.getSort() != null) {
            if (filters.getSort().getRating() == null){
                if (filters.getSort().getDuration().equals("increasing")) {
                    movieList.sort((m1, m2) -> {return m1.getDuration() - m2.getDuration();});
                }
                else{
                    movieList.sort((m1, m2) -> {return m2.getDuration() - m1.getDuration();});
                }
            }
            else if (filters.getSort().getDuration() == null) {
                if (filters.getSort().getRating().equals("increasing")) {
                    movieList.sort((m1, m2) -> {return m1.getRating().compareTo(m2.getRating());});
                }
                else{
                    movieList.sort((m1, m2) -> {return m2.getRating().compareTo(m1.getRating());});
                }
            }
            else {
                String ratingFilter = filters.getSort().getRating();
                String durationFilter = filters.getSort().getDuration();

                if (ratingFilter.equals("increasing") && durationFilter.equals("increasing")) {
                    movieList.sort((m1, m2) -> {
                        if(m1.getDuration() != m2.getDuration()){
                            return m1.getDuration() - m2.getDuration();
                        }
                        else {
                            return m1.getRating().compareTo(m2.getRating());
                        }
                    });
                }
                if (ratingFilter.equals("increasing") && durationFilter.equals("decreasing")) {
                    movieList.sort((m1, m2) -> {
                        if(m1.getDuration() != m2.getDuration()){
                            return m2.getDuration() - m1.getDuration();
                        }
                        else{
                            return m1.getRating().compareTo(m2.getRating());
                        }
                    });
                }
                if (ratingFilter.equals("decreasing") && durationFilter.equals("increasing")) {
                    movieList.sort((m1, m2) -> {
                        if(m1.getDuration() != m2.getDuration()){
                            return m1.getDuration() - m2.getDuration();
                        }
                        else{
                            return m2.getRating().compareTo(m1.getRating());
                        }
                    });
                }
                if (ratingFilter.equals("decreasing") && durationFilter.equals("decreasing")) {
                    movieList.sort((m1, m2) -> {
                        if(m1.getDuration() != m2.getDuration()){
                            return m2.getDuration() - m1.getDuration();
                        }
                        else{
                            return m2.getRating().compareTo(m1.getRating());
                        }
                    });
                }
            }
        }

        site.showOutput();
    }
}
