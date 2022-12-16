package pages;

public class UpgradesPage extends Page {
    public UpgradesPage() {
        pageName = "upgrades";

        pagesAccessible.add("authenticatedPage");
        pagesAccessible.add("logout");
        pagesAccessible.add("upgrades");
        pagesAccessible.add("movies");
    }
}
