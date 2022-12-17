package actions;

import usermoviesdata.Movie;
import usermoviesdata.User;
import datainput.ActionsInput;
import logic.Database;
import logic.SiteLogic;
import pages.Page;

import java.util.ArrayList;

public class FilterAction extends Action {

    public FilterAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for filtering movies after rating and duration or actors/genre contained
     * Verify if the action can be done from the current page.
     * Remove the movies that do not contain actors/genre wanted
     * Sort the remaining movies by rating and duration
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

        if (filters.getContains() != null) {
            for (int i = 0; i < movieListCopy.size(); i++) {
                if (filters.getContains().getActors() != null) {
                    boolean notFoundActor = false;
                    for (int j = 0; j < filters.getContains().getActors().size(); j++) {
                        ArrayList<String> actors = movieListCopy.get(i).getActors();
                        if (!actors.contains(filters.getContains().getActors().get(j))) {
                            notFoundActor = true;
                            break;
                        }
                    }
                    if (notFoundActor) {
                        movieList.remove(movieListCopy.get(i));
                        continue;
                    }
                }
                if (filters.getContains().getGenre() != null) {
                    boolean notFoundGenre = false;
                    for (int j = 0; j < filters.getContains().getGenre().size(); j++) {
                        ArrayList<String> genres = movieListCopy.get(i).getGenres();
                        if (!genres.contains(filters.getContains().getGenre().get(j))) {
                            notFoundGenre = true;
                            break;
                        }
                    }
                    if (notFoundGenre) {
                        movieList.remove(movieListCopy.get(i));
                        continue;
                    }
                }
            }
        }
        if (filters.getSort() != null) {
            if (filters.getSort().getRating() == null) {
                if (filters.getSort().getDuration().equals("increasing")) {
                    movieList.sort((m1, m2) -> {
                        return m1.getDuration() - m2.getDuration(); });
                } else {
                    movieList.sort((m1, m2) -> {
                        return m2.getDuration() - m1.getDuration(); });
                }
            } else if (filters.getSort().getDuration() == null) {
                if (filters.getSort().getRating().equals("increasing")) {
                    movieList.sort((m1, m2) -> {
                        return m1.getRating().compareTo(m2.getRating()); });
                } else {
                    movieList.sort((m1, m2) -> {
                        return m2.getRating().compareTo(m1.getRating()); });
                }
            } else {
                String ratingFilter = filters.getSort().getRating();
                String durationFilter = filters.getSort().getDuration();

                if (ratingFilter.equals("increasing") && durationFilter.equals("increasing")) {
                    movieList.sort((m1, m2) -> {
                        if (m1.getDuration() != m2.getDuration()) {
                            return m1.getDuration() - m2.getDuration();
                        } else {
                            return m1.getRating().compareTo(m2.getRating());
                        }
                    });
                }
                if (ratingFilter.equals("increasing") && durationFilter.equals("decreasing")) {
                    movieList.sort((m1, m2) -> {
                        if (m1.getDuration() != m2.getDuration()) {
                            return m2.getDuration() - m1.getDuration();
                        } else {
                            return m1.getRating().compareTo(m2.getRating());
                        }
                    });
                }
                if (ratingFilter.equals("decreasing") && durationFilter.equals("increasing")) {
                    movieList.sort((m1, m2) -> {
                        if (m1.getDuration() != m2.getDuration()) {
                            return m1.getDuration() - m2.getDuration();
                        } else {
                            return m2.getRating().compareTo(m1.getRating());
                        }
                    });
                }
                if (ratingFilter.equals("decreasing") && durationFilter.equals("decreasing")) {
                    movieList.sort((m1, m2) -> {
                        if (m1.getDuration() != m2.getDuration()) {
                            return m2.getDuration() - m1.getDuration();
                        } else {
                            return m2.getRating().compareTo(m1.getRating());
                        }
                    });
                }
            }
        }

        site.showOutput();
    }
}
