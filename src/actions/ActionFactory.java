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
        }
        else if (input.getType().equals("change page")) {
            return new ChangePageAction(input);
        }

        return null;
    }
}
