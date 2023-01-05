package actions;

import datainput.ActionsInput;
import datainput.CredentialsInput;
import datainput.FiltersInput;
import logic.SiteLogic;
import usermoviesdata.Movie;

/*
 * Parent class for the other actions
 */
public abstract class Action {
    protected String type;
    protected String page;
    protected String feature;
    protected CredentialsInput credentials;
    protected String startsWith;
    protected String objectType;
    protected String movie;
    protected FiltersInput filters;
    protected int rate;
    protected int count;

    protected String subscribedGenre;

    protected Movie addedMovie;

    public Action(final ActionsInput input) {
        type = input.getType();
        page = input.getPage();
        feature = input.getFeature();
        credentials = input.getCredentials();
        startsWith = input.getStartsWith();
        objectType = input.getObjectType();
        movie = input.getMovie();
        filters = input.getFilters();
        rate = input.getRate();
        count = input.getCount();
        subscribedGenre = input.getSubscribedGenre();
        if (input.getAddedMovie() != null) {
            addedMovie = new Movie(input.getAddedMovie());
        }
    }

    /**
     * Implement the logic for different actions
     * @param site the object which is being modified
     */
    public abstract void doAction(SiteLogic site);

}
