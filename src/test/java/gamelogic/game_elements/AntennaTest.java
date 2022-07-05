package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gamelogic.Direction;
import net.jqwik.api.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AntennaTest {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();



    @Example
    void fromJson() throws IOException {
        /*

        ElementFactory elementFactory = new ElementFactory();
        String jsonString = "{\n" +
                "  \"type\": \"Antenna\",\n" +
                "  \"isOnBoard\": \"Start A\",\n" +
                "  \"orientations\": [\n" +
                "    \"\"\n" +
                "  ]\n" +
                "}";
        JsonObject jsonObject = gson.fromJson(jsonString,JsonObject.class);
        GameElement antenna = elementFactory.createElement(jsonObject);
        assertInstanceOf(Antenna.class,antenna);

        System.out.println(antenna.toString());

        */

    }

    @Example
    void toJson() {

        Antenna antenna = new Antenna(Direction.EAST);
        antenna.isOnBoard = "Start A";

        String jsonString = gson.toJson(antenna.toJson());

        assertEquals("{\n" +
                "  \"type\": \"Antenna\",\n" +
                "  \"isOnBoard\": \"Start A\",\n" +
                "  \"orientations\": [\n" +
                "    \"EAST\"\n" +
                "  ]\n" +
                "}",jsonString);


    }
}