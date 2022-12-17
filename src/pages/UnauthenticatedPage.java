package pages;

public class UnauthenticatedPage extends Page {
    public UnauthenticatedPage() {
        pageName = "unauthenticatedPage";

        pagesAccessible.add("login");
        pagesAccessible.add("register");
        pagesAccessible.add("unauthenticatedPage");
    }

}
