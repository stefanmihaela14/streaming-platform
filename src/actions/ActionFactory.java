package actions;

import datainput.ActionsInput;

public class ActionFactory {
    public static Action createNew(ActionsInput input){
        if (input.getType().equals("on page") && input.getFeature().equals("login")) {
            return new LoginAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("register")) {
            return new RegisterAction(input);
        } else if (input.getType().equals("change page") && input.getPage().equals("logout")) {
            return new LogoutAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("search")) {
            return new SearchAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("filter")) {
            return new FilterAction(input);
        } else if (input.getType().equals("change page") && input.getPage().equals("see details")) {
            return new SeeDetailsAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("buy tokens")) {
            return new BuyTokensAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("buy premium account")) {
            return new BuyPremiumAccountAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("purchase")) {
            return new PurchaseAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("watch")) {
            return new WatchAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("like")) {
            return new LikeAction(input);
        } else if (input.getType().equals("on page") && input.getFeature().equals("rate")) {
            return new RateAction(input);
        }
        else if (input.getType().equals("change page")) {
            return new ChangePageAction(input);
        }

        return null;
    }
}
