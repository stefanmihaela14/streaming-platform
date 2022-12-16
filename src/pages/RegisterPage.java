package pages;

public class RegisterPage extends Page {
    public RegisterPage() {
        pageName = "register";

        pagesAccessible.add("login");
        actionsAccessible.add("register");

        pageWithMovies = 0;
        loggedUser = 0;
    }
}
