package pages;

import usermoviesdata.Movie;;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Page {
    @Getter
    protected String pageName = "";
    @Getter
    protected ArrayList<String> pagesAccessible = new ArrayList<>();
    @Getter
    @Setter
    protected ArrayList<Movie> movieList = new ArrayList<>();
}
