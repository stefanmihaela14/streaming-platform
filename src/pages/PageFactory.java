package pages;

public final class PageFactory {
    private PageFactory() {
    }

    /**
     * Turns the page received into a specific page class
     */
    public static Page createNew(final String pageName) {
        return switch (pageName) {
            case "login" -> new LoginPage();
            case "register" -> new RegisterPage();
            case "logout" -> new LogoutPage();
            case "authenticatedPage" -> new AuthenticatedPage();
            case "unauthenticatedPage" -> new UnauthenticatedPage();
            case "movies" -> new MoviesPage();
            case "seeDetails" -> new SeeDetailsPage();
            case "upgrades" -> new UpgradesPage();
            default -> null;
        };

    }
}
