package actions;

import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;
import pages.Page;
import pages.PageFactory;

import java.util.ArrayList;

public class ChangePageAction extends Action{

    public ChangePageAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        SiteLogic siteLogic = SiteLogic.getInstance();
        Page currentPage = SiteLogic.getInstance().getCurrentPage();
        Page newPage = PageFactory.createNew(page);

        ArrayList<String> pagesAccessible = currentPage.getPagesAccessible();
        for (int i = 0; i < currentPage.getPagesAccessible().size(); i++) {
            if (newPage.getPageName().equals(pagesAccessible.get(i))) {
                siteLogic.setCurrentPage(newPage);
                if (newPage.getPageName().equals("movies") || newPage.getPageName().equals("see details")) {
                    SiteLogic.getInstance().showOutput();
                }
                return;
            }
        }

        SiteLogic.getInstance().showErrorOutput();
    }
}
