package logicAndFunctionalities;

import UserMoviesData.Movie;
import UserMoviesData.User;
import actions.Action;
import actions.ActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import datainput.ActionsInput;
import datainput.Input;
import datainput.MovieInput;
import datainput.UserInput;
import lombok.Getter;
import lombok.Setter;
import pages.Page;

import java.util.ArrayList;

public class SiteLogic {
    @Getter
    private ArrayNode output;
    @Getter
    private Input inputData;

    @Getter
    @Setter
    private Page currentPage;

    @Getter
    @Setter
    private User currentUser;

    private final Database database = Database.getInstance();

    private static SiteLogic instance;
    
    /**
     * @return Get Singleton instance.
     */
    public static SiteLogic getInstance() {
        if (instance == null) {
            SiteLogic.instance = new SiteLogic();
        }
        return instance;
    }

    private SiteLogic() {
    }

    public void startSite(ArrayNode output, Input inputData) {
        this.output = output;
        this.inputData = inputData;

        ArrayList<UserInput> userInput = inputData.getUsers();
        for (int i = 0; i < userInput.size(); i++) {
            User newUser = new User(userInput.get(i));
            database.addUser(newUser);
        }
        ArrayList<MovieInput> movieInput = inputData.getMovies();
        for (int i = 0; i < userInput.size(); i++) {
            Movie newMovie = new Movie(movieInput.get(i));
            database.addMovie(newMovie);
        }

        ArrayList<ActionsInput> actionsInputs = inputData.getActions();
        for(int i = 0; i < actionsInputs.size(); i++){
            Action newAction = ActionFactory.createNew(actionsInputs.get(i));
            if(newAction == null){
                continue;
            }
            receiveAction(newAction);
        }

    }

    public void receiveAction(Action action){
        action.doAction(this);
    }
}
