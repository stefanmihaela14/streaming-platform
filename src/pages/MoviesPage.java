package pages;

public class MoviesPage extends Page {
    public MoviesPage() {
        pagesAccessible.add("authenticatedPage");
        pagesAccessible.add("seeDetails");
        pagesAccessible.add("logout");
        pagesAccessible.add("upgrades"); // ? not so sure

    }
}
