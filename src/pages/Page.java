package pages;

import UserMoviesData.Movie;
import actions.Action;
import lombok.Getter;

import java.util.ArrayList;

public class Page {
    @Getter
    protected String pageName = "";
    @Getter
    protected ArrayList<String> pagesAccessible = new ArrayList<>();
    @Getter
    protected ArrayList<String> actionsAccessible= new ArrayList<>();
    @Getter
    protected ArrayList<Movie> movieList = new ArrayList<>();
    @Getter
    protected int pageWithMovies = 0;
    @Getter
    protected int loggedUser = 0;
}
