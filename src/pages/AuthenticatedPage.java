package pages;

public class AuthenticatedPage extends Page {
    public AuthenticatedPage() {
        pagesAccessible.add("movies");
        pagesAccessible.add("upgrades");
        pagesAccessible.add("logout");

        pageWithMovies = 0;

        loggedUser = 1;
    }
}
