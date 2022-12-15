package actions;

import datainput.ActionsInput;

public class ActionFactory {
    public static Action createNew(ActionsInput input){
        if(input.getType().equals("on page") && input.getFeature().equals("login")){
            return new LoginAction(input);
        }

        return null;
    }
}
