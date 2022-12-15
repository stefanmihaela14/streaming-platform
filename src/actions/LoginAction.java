package actions;

import UserMoviesData.Movie;
import UserMoviesData.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logicAndFunctionalities.Database;
import logicAndFunctionalities.SiteLogic;
import pages.Page;
import pages.PageFactory;

public class LoginAction extends Action {
    public LoginAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        SiteLogic siteLogic = SiteLogic.getInstance();
        String name = siteLogic.getCurrentPage().getPageName();
        if (!name.equals("login")) {
            ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
            newNode.put("error", "Error");
            ArrayNode outputArray = newNode.putArray("currentMoviesList");
            newNode.putNull("currentUser");
        }

        String username = credentials.getName();
        String password = credentials.getPassword();
        String country = credentials.getCountry();
        String accountType = credentials.getAccountType();
        int balance = credentials.getBalance();

        Database database = Database.getInstance();
        for (int i = 0; i < database.getUsers().size(); i++) {
            User randomUser = database.getUsers().get(i);
            if (username.equals(randomUser.getName()) && password.equals(randomUser.getPassword())){
                Page newPage = PageFactory.createNew("login");
                siteLogic.setCurrentPage(newPage);
                User newUser = database.getUsers().get(i);
                siteLogic.setCurrentUser(database.getUsers().get(i));

                //show good output
                ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
                newNode.putNull("error");
                ArrayNode outputArray = newNode.putArray("currentMoviesList");
                for (int j = 0; j < siteLogic.getCurrentPage().getMovieList().size(); j++) {
                    Movie currentMovie = siteLogic.getCurrentPage().getMovieList().get(j);
                    currentMovie.movieOutput(outputArray);
                }
                ObjectNode userNode = newNode.putObject("currentUser");
                ObjectNode credentialsNode = userNode.putObject("credentials");
                credentialsNode.put("name", username);
                credentialsNode.put("password", password);
                credentialsNode.put("accountType", country);
                credentialsNode.put("country", accountType);
                credentialsNode.put("balance", balance);
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
                return;
            }
        }
        // if username not found throw error and go on unauthenticated page
        ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
        newNode.put("error", "Error");
        ArrayNode outputArray = newNode.putArray("currentMoviesList");
        newNode.putNull("currentUser");

        Page newPage = PageFactory.createNew("unauthenticatedPage");
        siteLogic.setCurrentPage(newPage);
    }
}
