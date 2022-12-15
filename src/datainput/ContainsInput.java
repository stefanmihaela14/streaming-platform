package datainput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public class ContainsInput {
    private ArrayList<String> actors;
    private ArrayList<String> genre;
}
