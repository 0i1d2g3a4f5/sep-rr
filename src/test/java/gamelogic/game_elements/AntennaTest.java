package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gamelogic.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AntennaTest {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    void fromJson() throws IOException {
        String jsonString = "{\n" +
                "  \"type\": \"Antenna\",\n" +
                "  \"isOnBoard\": \"Start A\",\n" +
                "  \"orientations\": [\n" +
                "    \"right\"\n" +
                "  ]\n" +
                "}";
        JsonObject jsonObject = gson.fromJson(jsonString,JsonObject.class);
        Antenna antenna = Antenna.fromJson(jsonObject);
        System.out.println(antenna.toString());

    }

    @Test
    void toJson() {
        Antenna antenna = new Antenna(Direction.EAST);
        antenna.isOnBoard = "Start A";

        String jsonString = gson.toJson(antenna.toJson());

        assertEquals("{\n" +
                "  \"type\": \"Antenna\",\n" +
                "  \"isOnBoard\": \"Start A\",\n" +
                "  \"orientations\": [\n" +
                "    \"right\"\n" +
                "  ]\n" +
                "}",jsonString);
    }
}