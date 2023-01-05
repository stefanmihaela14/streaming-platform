package actions;

import datainput.ActionsInput;
import logic.Database;
import logic.SiteLogic;
import usermoviesdata.Movie;

public class DatabaseAddAction extends Action {
    public DatabaseAddAction(final ActionsInput input) {
        super(input);
    }


    @Override
    public void doAction(SiteLogic site) {
        for (Movie movie : site.getDatabase().getMovies()) {
            if (movie.getName().equals(addedMovie.getName())) {
                site.showErrorOutput();
                return;
            }
        }
        site.getDatabase().addMovie(addedMovie);
    }
}
