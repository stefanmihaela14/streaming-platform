package actions;

import UserMoviesData.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.ActionsInput;
import logicAndFunctionalities.SiteLogic;

public class BuyTokensAction extends Action{
    public BuyTokensAction(ActionsInput input) {
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
