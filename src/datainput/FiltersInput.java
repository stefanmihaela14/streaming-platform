package datainput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FiltersInput {
    private ContainsInput contains;
    private SortInput sort;
}
