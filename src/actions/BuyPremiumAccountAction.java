package actions;

import UserMoviesData.User;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;

public class BuyPremiumAccountAction extends Action{
    public BuyPremiumAccountAction(ActionsInput input) {
        super(input);
    }

    @Override
    public void doAction(SiteLogic site) {
        String name = site.getCurrentPage().getPageName();
        if (!name.equals("upgrades")) {
            site.showErrorOutput();
            return;
        }

        User currentUser = site.getCurrentUser();
        int userTokensCount = currentUser.getTokensCount();

        if (currentUser.getAccountType().equals("premium")) {
            site.showErrorOutput();
            return;
        }

        if (userTokensCount < 10) {
            site.showErrorOutput();
            return;
        }

        currentUser.setTokensCount(userTokensCount - 10);

        currentUser.setAccountType("premium");
    }
}
