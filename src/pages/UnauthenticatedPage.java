package pages;

import java.util.ArrayList;
import java.util.Arrays;

public class UnauthenticatedPage extends Page {
    public UnauthenticatedPage() {
        pageName = "unauthenticatedPage";

        pagesAccessible.add("login");
        pagesAccessible.add("register");

        pageWithMovies = 0;

        loggedUser = 0;
    }

}
