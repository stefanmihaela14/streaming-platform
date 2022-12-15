package UserMoviesData;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datainput.MovieInput;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private int numLikes = 0;
    private Double rating = 0d;
    private int numRatings = 0;

    public Movie(MovieInput movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.actors = new ArrayList<String>(movie.getActors());
        this.genres = new ArrayList<String>(movie.getGenres());
        this.countriesBanned = new ArrayList<String>(movie.getCountriesBanned());
    }
    
    public void movieOutput(ArrayNode arrayNode) {
        ObjectNode newMovieNode = arrayNode.addObject();
        newMovieNode.put("name", this.getName());
        newMovieNode.put("year", this.getName());
        newMovieNode.put("duration", this.getName());
        ArrayNode genresArray = newMovieNode.putArray("genres");
        for (int k = 0; k < this.getGenres().size(); k++) {
            genresArray.add(this.getGenres().get(k));
        }
        ArrayNode actorsArray = newMovieNode.putArray("actors");
        for (int k = 0; k < this.getActors().size(); k++) {
            actorsArray.add(this.getActors().get(k));
        }
        ArrayNode countriesBannedArray = newMovieNode.putArray("countriesBanned");
        for (int k = 0; k < this.getCountriesBanned().size(); k++) {
            genresArray.add(this.getCountriesBanned().get(k));
        }
        newMovieNode.put("numLikes", this.getNumLikes());
        newMovieNode.put("rating", this.getRating());
        newMovieNode.put("numRatings", this.getNumRatings());
    }
}
