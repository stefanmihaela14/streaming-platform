package pages;

public class MoviesPage extends Page {
    public MoviesPage() {
        pageName = "movies";

        pagesAccessible.add("authenticatedPage");
        pagesAccessible.add("seeDetails");
        pagesAccessible.add("logout");
        pagesAccessible.add("upgrades");
        pagesAccessible.add("movies");

    }
}
