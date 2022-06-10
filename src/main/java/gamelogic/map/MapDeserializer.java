package gamelogic.map;

import com.google.gson.*;
import gamelogic.Position;
import gamelogic.game_elements.*;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class MapDeserializer {

    /**
     * @author Ringer
     */

    /**
     * @author Ringer
     */


    //Gson gson = new Gson();

    /*
    /**
     * @author Ringer
     * @param board
     * @return
     */
    /*
    public String serializeBoard(GameBoard board){

        SerializableGameBoard sBoard = new SerializableGameBoard();
        sBoard.dimension1= board.dimension1;
        sBoard.dimension2= board.dimension2;

        sBoard.list = new ArrayList();
        /*
        board.boardMap.forEach((key,value)->{
            GameField field = value;
            SerializableGameField sField = new SerializableGameField();
            sField.isActive = field.isActive;
            sField.key =field.key;
            sField.wallNorth = field.isWallNorth();
            sField.wallSouth = field.isWallSouth();
            sField.wallWest = field.isWallWest();
            sField.wallEast = field.isWallEast();
            sField.elementName = field.elementName;
            System.out.print("Key: "+key+" Element: ");
            sField.gameElementString = serializeElement(field.element);
            sField.laserBeam = field.laserBeam;
            sField.checkpoint = field.checkpoint;
            sBoard.list.add(sField);


        });

        return gson.toJson(sBoard);

    }
    */

/*
    /**
     * @author Ringer
     * @param element
     * @return
     */
    /*
    private String serializeElement(GameElement element){
        System.out.println(element);
        String output = gson.toJson(element);
        return output;
    }

     */







    /**
     * @author Ringer
     * @param json
     * @return
     */
    private GameElement deserializeElement(JsonObject json){
        return new Empty();
    }
}
