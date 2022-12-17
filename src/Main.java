import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import datainput.Input;
import logic.Database;
import logic.SiteLogic;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }
    public static void main(final String[] args) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();

        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);

        SiteLogic.removeInstance();
        Database.removeInstance();

        ArrayNode output = objectMapper.createArrayNode();
        SiteLogic.getInstance().startSite(output, inputData);


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
