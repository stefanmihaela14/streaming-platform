package pages;

public class SeeDetailsPage extends Page {
    public SeeDetailsPage() {
        pageName = "seeDetails";

        pagesAccessible.add("authenticatedPage");
        pagesAccessible.add("logout");
        pagesAccessible.add("upgrades");
        pagesAccessible.add("movies");
    }
}
