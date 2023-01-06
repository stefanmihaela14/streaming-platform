package usermoviesdata;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {
    private String movieName;
    private String message;

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    /**
     * Output for notifications
     * @param arrayNode to write the output for notifications
     */
    public final void notificationOutput(final ArrayNode arrayNode) {
        ObjectNode newNotificationNode = arrayNode.addObject();
        newNotificationNode.put("movieName", this.movieName);
        newNotificationNode.put("message", this.message);
    }
}
