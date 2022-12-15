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
            return;
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
                SiteLogic.getInstance().showErrorOutput();

                Page newPage = PageFactory.createNew("unauthenticatedPage");
                siteLogic.setCurrentPage(newPage);
                return;
            }
        }
        User newUser = new User(username, password, country, accountType, balance);
        database.addUser(newUser);
        SiteLogic.getInstance().setCurrentUser(newUser);
        Page newPage = PageFactory.createNew("authenticatedPage");
        siteLogic.setCurrentPage(newPage);
        //show good output
        SiteLogic.getInstance().showOutput();
    }
}
