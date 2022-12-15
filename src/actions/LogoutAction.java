package actions;

import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;
import pages.PageFactory;

public class LogoutAction extends Action{
    public LogoutAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        SiteLogic siteLogic = SiteLogic.getInstance();

        Page newPage = PageFactory.createNew("unauthenticatedPage");
        siteLogic.setCurrentPage(newPage);
    }
}
