package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Direction;


import java.io.IOException;

public class Laser extends GameElement  {
    Direction direction;

    private int count;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Laser{" +
                "direction=" + direction +
                ", count=" + count +
                '}';
    }

    /**
     * @author Ringer
     * @param count
     * @param direction
     */
    public Laser(Direction direction, int count ) throws IOException {
        this.direction = direction;
        orientations.add(direction);
        if(count>3||count<1)
            throw new IOException("This Laser doesn't exist");
        this.count = count;
        type = ElementName.LASER;

    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public Laser(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
        Laser laser = new Laser(direction,jsonObject.get("count").getAsInt());


    }

}
