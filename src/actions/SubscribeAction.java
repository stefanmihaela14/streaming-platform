package actions;

import datainput.ActionsInput;
import logic.SiteLogic;
import pages.Page;
import usermoviesdata.User;

public class SubscribeAction extends Action{
    public SubscribeAction(final ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        User currentUser = site.getCurrentUser();
        Page currentPage = site.getCurrentPage();

        if (!name.equals("seeDetails")) {
            site.showErrorOutput();
            return;
        }

        currentPage.getMovieList().get(0).getName();
    }
}
