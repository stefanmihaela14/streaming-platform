package pages;

public class PageFactory {
    public static Page createNew(String pageName){
        if (pageName.equals("login")){
            return new LoginPage();
        } else if (pageName.equals("unauthenticatedPage")){
            return new UnauthenticatedPage();
        }

        return null;
    }
}
