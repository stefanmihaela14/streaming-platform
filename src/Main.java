import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import datainput.Input;
import logicAndFunctionalities.Database;
import logicAndFunctionalities.SiteLogic;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();



        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);

        SiteLogic.removeInstance();
        Database.removeInstance();

        ArrayNode output = objectMapper.createArrayNode();
        SiteLogic ins = SiteLogic.getInstance();
        SiteLogic.getInstance().startSite(output, inputData);
        SiteLogic ins2 = SiteLogic.getInstance();


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
        System.out.println(args[0]);

        if(args[0].equals("/home/mihaela/IdeaProjects/proiect1/checker/resources/in/basic_2.json")){
            objectWriter.writeValue(new File("test_mai_multe.json"), output);
        }
        //Thread.sleep(300000);
    }
}
