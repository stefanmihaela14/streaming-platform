package logic;

import usermoviesdata.Movie;
import usermoviesdata.User;
import actions.Action;
import actions.ActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import datainput.Input;
import datainput.MovieInput;
import datainput.UserInput;
import lombok.Getter;
import lombok.Setter;
import pages.Page;
import pages.PageFactory;

import java.util.ArrayList;

public final class SiteLogic {
    @Getter
    private ArrayNode output;
    @Getter
    private Input inputData;

    @Getter
    @Setter
    private Page currentPage;

    @Getter
    @Setter
    private User currentUser;

    private Database database;

    private static SiteLogic instance;

    /**
     * delete the instance after each test due to singleton use
     */
    public static void removeInstance() {
        instance = null;
    }
    /**
     * @return Get Singleton instance.
     */
    public static SiteLogic getInstance() {
        if (instance == null) {
            SiteLogic.instance = new SiteLogic();
        }
        return instance;
    }

    private SiteLogic() {
    }

    /**
     * Keeps the current page and user, adds the users and movies in the database
     * and runs the actions.
     * Starts form unauthenticated page
     * @param myOutput arrayNode in which outputs nodes are put
     * @param myInputData the data that we put in the database
     */
    public void startSite(final ArrayNode myOutput, final Input myInputData) {
        output = myOutput;
        inputData = myInputData;
        currentPage = PageFactory.createNew("unauthenticatedPage");
        currentUser = null;
        database = Database.getInstance();
        ArrayList<UserInput> userInput = inputData.getUsers();
        for (int i = 0; i < userInput.size(); i++) {
            User newUser = new User(userInput.get(i));
            database.addUser(newUser);
        }
        ArrayList<MovieInput> movieInput = inputData.getMovies();
        for (int i = 0; i < movieInput.size(); i++) {
            Movie newMovie = new Movie(movieInput.get(i));
            database.addMovie(newMovie);
        }

        ArrayList<ActionsInput> actionsInputs = inputData.getActions();
        for (int i = 0; i < actionsInputs.size(); i++) {
            Action newAction = ActionFactory.createNew(actionsInputs.get(i));
            if (newAction == null) {
                continue;
            }
            receiveAction(newAction);
        }
    }

    /**
     * accepts a visitor-type object and runs it
     */
    public void receiveAction(final Action action) {
        action.doAction(this);
    }

    /**
     * output for error
     */
    public void showErrorOutput() {
        ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();

        newNode.put("error", "Error");
        ArrayNode outputArray = newNode.putArray("currentMoviesList");
        newNode.putNull("currentUser");
    }

    /**
     * output for current user
     */
    public void showOutput() {
        User newUser = currentUser;

        String username = currentUser.getName();
        String password = currentUser.getPassword();
        String country = currentUser.getCountry();
        String accountType = currentUser.getAccountType();
        int balance = currentUser.getBalance();

        ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
        newNode.putNull("error");
        ArrayNode outputArray = newNode.putArray("currentMoviesList");
        for (int j = 0; j < getCurrentPage().getMovieList().size(); j++) {
            Movie currentMovie = getCurrentPage().getMovieList().get(j);
            currentMovie.movieOutput(outputArray);
        }
        ObjectNode userNode = newNode.putObject("currentUser");
        ObjectNode credentialsNode = userNode.putObject("credentials");
        credentialsNode.put("name", username);
        credentialsNode.put("password", password);
        credentialsNode.put("accountType", accountType);
        credentialsNode.put("country", country);
        credentialsNode.put("balance", String.valueOf(balance));
        userNode.put("tokensCount", newUser.getTokensCount());
        userNode.put("numFreePremiumMovies", newUser.getNumFreePremiumMovies());
        ArrayNode purchasedArrayNode = userNode.putArray("purchasedMovies");
        for (int j = 0; j < newUser.getPurchasedMovies().size(); j++) {
            Movie currentMovie = newUser.getPurchasedMovies().get(j);
            currentMovie.movieOutput(purchasedArrayNode);
        }
        ArrayNode watchedArrayNode = userNode.putArray("watchedMovies");
        for (int j = 0; j < newUser.getWatchedMovies().size(); j++) {
            Movie currentMovie = newUser.getWatchedMovies().get(j);
            currentMovie.movieOutput(watchedArrayNode);
        }
        ArrayNode likedArrayNode = userNode.putArray("likedMovies");
        for (int j = 0; j < newUser.getLikedMovies().size(); j++) {
            Movie currentMovie = newUser.getLikedMovies().get(j);
            currentMovie.movieOutput(likedArrayNode);
        }
        ArrayNode ratedArrayNode = userNode.putArray("ratedMovies");
        for (int j = 0; j < newUser.getRatedMovies().size(); j++) {
            Movie currentMovie = newUser.getRatedMovies().get(j);
            currentMovie.movieOutput(ratedArrayNode);
        }
    }
}
