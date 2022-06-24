package utility;

import java.io.*;

/**
 * @author Ringer
 */
public class JsonReader {

    public String readFile(String filePath){
        File file= new File(filePath);
        BufferedReader reader;
        try {
            reader= new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String jsonString="";
        String input;
        while (true) {
            try {
                if (!((input=reader.readLine())!=null)) break;
                jsonString+=input;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonString;
    }
}
