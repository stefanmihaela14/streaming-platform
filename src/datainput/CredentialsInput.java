package datainput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CredentialsInput {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;
}
