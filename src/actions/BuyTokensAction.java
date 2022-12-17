package actions;

import usermoviesdata.User;

import datainput.ActionsInput;
import logic.SiteLogic;

public class BuyTokensAction extends Action {
    public BuyTokensAction(final ActionsInput input) {
        super(input);
    }

    /**
     * Implement the logic for buying a premium tokens.
     * Verify if the action can be done from the current page.
     * Verify if user is standard or premium and if it's standard we decrease it's
     * number of tokens and if it's premium before decreasing the number of tokens
     * we decrease the number of free movies.
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
        int noTokensToBuy = count;
        int balance = currentUser.getBalance();

        if (balance < noTokensToBuy) {
            site.showErrorOutput();
            return;
        }
        if (noTokensToBuy < 0) {
            site.showErrorOutput();
            return;
        }

        currentUser.setTokensCount(userTokensCount + noTokensToBuy);
        currentUser.setBalance(balance - noTokensToBuy);
    }
}
