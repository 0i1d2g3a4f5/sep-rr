package utility;

import java.io.*;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mark Ringer
 */
public class JsonReader {

    /**
     * reads a Json File at the filePath
     * @param filePath
     * @return
     */
    public String readFile(String filePath){

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String string = bufferedReader.lines().collect(Collectors.joining("\n"));

        return string;
    }
}
