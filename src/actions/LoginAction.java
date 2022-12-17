package actions;

import usermoviesdata.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logic.Database;
import logic.SiteLogic;
import pages.Page;
import pages.PageFactory;

public class LoginAction extends Action {
    public LoginAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for login
     * Verify if the action can be done from the current page.
     * Verify if credentials are in the database, if not show error, otherwise
     * go to authenticated page.
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
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
            User user = database.getUsers().get(i);
            if (username.equals(user.getName()) && password.equals(user.getPassword())) {
                Page newPage = PageFactory.createNew("authenticatedPage");
                siteLogic.setCurrentPage(newPage);
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
