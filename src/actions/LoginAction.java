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
            return;
        }

        String username = credentials.getName();
        String password = credentials.getPassword();

        Database database = Database.getInstance();
        for (int i = 0; i < database.getUsers().size(); i++) {
            User randomUser = database.getUsers().get(i);
            if (username.equals(randomUser.getName()) && password.equals(randomUser.getPassword())){
                Page newPage = PageFactory.createNew("authenticatedPage");
                siteLogic.setCurrentPage(newPage);
                User newUser = database.getUsers().get(i);
                siteLogic.setCurrentUser(database.getUsers().get(i));

                //show good output
                SiteLogic.getInstance().showOutput();
                return;
            }
        }
        // if username not found throw error and go on unauthenticated page
        SiteLogic.getInstance().showErrorOutput();

        Page newPage = PageFactory.createNew("unauthenticatedPage");
        siteLogic.setCurrentPage(newPage);
    }
}
