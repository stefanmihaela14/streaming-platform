package pages;

public class AuthenticatedPage extends Page {
    public AuthenticatedPage() {
        pageName = "authenticatedPage";

        pagesAccessible.add("movies");
        pagesAccessible.add("upgrades");
        pagesAccessible.add("logout");
    }
}
