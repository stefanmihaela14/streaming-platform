package actions;

import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;
import pages.PageFactory;

import java.util.ArrayList;

public class LogoutAction extends Action {
    public LogoutAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for logout
     * Verify if the action can be done from the current page.
     * Send the user on unauthenticated page.
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
        ArrayList<String> unauthPages = new ArrayList<>();
        unauthPages.add("unauthenticatedPage");
        unauthPages.add("register");
        unauthPages.add("login");

        if (unauthPages.contains(site.getCurrentPage().getPageName())) {
            site.showErrorOutput();
            return;
        }

        Page newPage = PageFactory.createNew("unauthenticatedPage");
        site.setCurrentPage(newPage);
        site.getCurrentUser().getVisitedPagesStack().clear();
        site.setCurrentUser(null);

    }
}
