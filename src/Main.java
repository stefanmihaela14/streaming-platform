import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import datainput.Input;
import logicAndFunctionalities.Database;
import logicAndFunctionalities.SiteLogic;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);

        SiteLogic.removeInstance();
        Database.removeInstance();

        ArrayNode output = objectMapper.createArrayNode();
        SiteLogic ins = SiteLogic.getInstance();
        SiteLogic.getInstance().startSite(output, inputData);



        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);

    }
}
