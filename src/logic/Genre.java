package logic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private String genreName;
    private int numOfLikes;


    public Genre(final String genreName, final int numOfLikes) {
        this.genreName = genreName;
        this.numOfLikes = numOfLikes;
    }
}
