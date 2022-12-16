package pages;

public class PageFactory {
    public static Page createNew(String pageName){
        if (pageName.equals("login")){
            return new LoginPage();
        } else if (pageName.equals("register")) {
            return new RegisterPage();
        } else if (pageName.equals("logout")){
            return new LogoutPage();
        } else if (pageName.equals("authenticatedPage")){
            return new AuthenticatedPage();
        } else if (pageName.equals("unauthenticatedPage")){
            return new UnauthenticatedPage();
        } else if (pageName.equals("movies")){
            return new MoviesPage();
        } else if (pageName.equals("seeDetails")){
            return new SeeDetailsPage();
        }

        return null;
    }
}
