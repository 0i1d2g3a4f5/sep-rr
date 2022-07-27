package utility;

import java.io.*;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ringer
 */
public class JsonReader {

    public String readFile(String filePath){


        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String string = bufferedReader.lines().collect(Collectors.joining("\n"));

         /*

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

          */


        return string;
    }
}
