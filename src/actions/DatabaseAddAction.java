package actions;

import datainput.ActionsInput;
import logic.SiteLogic;
import usermoviesdata.Movie;

public class DatabaseAddAction extends Action {
    public DatabaseAddAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for adding a movie to the database.
     * Verify if the action can be done.
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        for (Movie movie : site.getDatabase().getMovies()) {
            if (movie.getName().equals(addedMovie.getName())) {
                site.showErrorOutput();
                return;
            }
        }
        site.getDatabase().addMovie(addedMovie);
    }
}
