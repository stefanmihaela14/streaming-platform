package usermoviesdata;

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
    private Double rating = 0.00;
    private int numRatings = 0;

    private ArrayList<Integer> ratingsList = new ArrayList<>();

    public Movie(final MovieInput movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.actors = new ArrayList<String>(movie.getActors());
        this.genres = new ArrayList<String>(movie.getGenres());
        this.countriesBanned = new ArrayList<String>(movie.getCountriesBanned());
        this.duration = movie.getDuration();
    }

    /**
     * Output for showing the list of movies that can be seen on the current page
     * @param arrayNode where we add the output
     */
    public void movieOutput(final ArrayNode arrayNode) {
        ObjectNode newMovieNode = arrayNode.addObject();
        newMovieNode.put("name", this.getName());
        newMovieNode.put("year", this.getYear());
        newMovieNode.put("duration", this.getDuration());
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
            countriesBannedArray.add(this.getCountriesBanned().get(k));
        }
        newMovieNode.put("numLikes", this.getNumLikes());
        newMovieNode.put("rating", rating);
        newMovieNode.put("numRatings", this.getNumRatings());
    }
}
