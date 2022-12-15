package pages;

import java.util.ArrayList;
import java.util.Arrays;

public class UnauthenticatedPage extends Page {
    public UnauthenticatedPage() {
        pagesAccessible.add("login");
        pagesAccessible.add("register");

        pageWithMovies = 0;

        loggedUser = 0;
    }

}
