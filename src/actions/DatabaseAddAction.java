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
       site.getDatabase().addMovie(addedMovie);
    }
}
