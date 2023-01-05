package datainput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ActionsInput {
    private String type;
    private String page;
    private String feature;
    private CredentialsInput credentials;
    private String startsWith;
    private String objectType;
    private String movie;
    private FiltersInput filters;
    private int rate;
    private int count;
    private String subscribedGenre;
    private MovieInput addedMovie;
    private String deletedMovie;
}
