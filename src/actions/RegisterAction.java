package actions;

import UserMoviesData.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logicAndFunctionalities.Database;
import logicAndFunctionalities.SiteLogic;
import pages.Page;
import pages.PageFactory;

public class RegisterAction extends Action {
    public RegisterAction(ActionsInput input) {
        super(input);
    }
    @Override
    public void doAction(SiteLogic site) {
        SiteLogic siteLogic = SiteLogic.getInstance();
        String name = siteLogic.getCurrentPage().getPageName();
        if(!name.equals("register")) {
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
            if(username.equals(randomUser.getName())) {
                ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
                newNode.put("error", "Error");
                ArrayNode outputArray = newNode.putArray("currentMoviesList");
                newNode.putNull("currentUser");

                Page newPage = PageFactory.createNew("unauthenticatedPage");
                siteLogic.setCurrentPage(newPage);
            } else {
                User newUser = new User(username, password, country, accountType, balance);
                database.addUser(newUser);

                //show good output
                ObjectNode newNode = SiteLogic.getInstance().getOutput().addObject();
                newNode.putNull("error");
                ArrayNode outputArray = newNode.putArray("currentMoviesList");
                ObjectNode userNode = newNode.putObject("currentUser");
                ObjectNode credentialsNode = userNode.putObject("credentials");
                credentialsNode.put("name", username);
                credentialsNode.put("password", password);
                credentialsNode.put("accountType", country);
                credentialsNode.put("country", accountType);
                credentialsNode.put("balance", balance);
                userNode.put("tokensCount", newUser.getTokensCount());
                userNode.put("numFreePremiumMovies", newUser.getNumFreePremiumMovies());
                ArrayNode purchasedArray = userNode.putArray("purchasedMovies");
                ArrayNode watchedArray = userNode.putArray("watchedMovies");
                ArrayNode likedArray = userNode.putArray("likedMovies");
                ArrayNode ratedArray = userNode.putArray("ratedMovies");
            }
        }
    }
}
