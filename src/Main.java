import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import datainput.Input;
import logicAndFunctionalities.SiteLogic;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(args[0]);

        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);

        //For testing:
        System.out.println(inputData);
        System.out.println(inputData.getUsers().get(0));
        //End.


        ArrayNode output = objectMapper.createArrayNode();

        SiteLogic.getInstance().startSite(output, inputData);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//        objectWriter.writeValue(new File("output.json"), output);
    }
}
