package actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;
import pages.PageFactory;

import java.util.ArrayList;

public class LogoutAction extends Action{
    public LogoutAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        SiteLogic siteLogic = SiteLogic.getInstance();
        ArrayList<String> unauthPages = new ArrayList<>();
        unauthPages.add("unauthenticatedPage");
        unauthPages.add("register");
        unauthPages.add("login");

        if(unauthPages.contains(siteLogic.getCurrentPage().getPageName())) {
            site.showErrorOutput();
            return;
        }

        Page newPage = PageFactory.createNew("unauthenticatedPage");
        siteLogic.setCurrentPage(newPage);
        siteLogic.setCurrentUser(null);
    }
}
