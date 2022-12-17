package actions;

import usermoviesdata.User;
import datainput.ActionsInput;
import logic.SiteLogic;

public class BuyPremiumAccountAction extends Action {
    private static final int PREMIUM_ACCOUNT_PRICE = 10;
    public BuyPremiumAccountAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for buying a premium account
     * Verify if the action can be done from the current page.
     * Verify if user is standard or premium and if it's standard
     * change the account type to premium
     * @param site the object which is being modified
     */
    @Override
    public void doAction(final SiteLogic site) {
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

        if (userTokensCount < PREMIUM_ACCOUNT_PRICE) {
            site.showErrorOutput();
            return;
        }

        currentUser.setTokensCount(userTokensCount - PREMIUM_ACCOUNT_PRICE);

        currentUser.setAccountType("premium");
    }
}
