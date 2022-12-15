package pages;

public class LoginPage extends Page {
    public LoginPage() {
        pageName = "login";

        pagesAccessible.add("register");
        actionsAccessible.add("login");

        pageWithMovies = 0;
        loggedUser = 0;
    }

}
