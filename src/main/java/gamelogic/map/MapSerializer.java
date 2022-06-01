package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gamelogic.Position;
import gamelogic.game_elements.*;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class MapSerializer {

    class SerializableGameField{
        Position key;

        boolean isActive;
        boolean wallTop;
        boolean wallBottom;
        boolean wallRight;
        boolean wallLeft;
        LaserBeam laserBeam;

        Checkpoint checkpoint;
        ElementName elementName;
        String gameElementString;
    }
    class SerializableGameBoard{
        int dimension1;
        int dimension2;
        ArrayList<SerializableGameField> list;
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    //Gson gson = new Gson();

    public String serializeBoard(GameBoard board){

        SerializableGameBoard sBoard = new SerializableGameBoard();
        sBoard.dimension1= board.dimension1;
        sBoard.dimension2= board.dimension2;

        sBoard.list = new ArrayList();
        board.boardMap.forEach((key,value)->{
            GameField field = value;
            SerializableGameField sField = new SerializableGameField();
            sField.isActive = field.isActive;
            sField.key =field.key;
            sField.wallTop = field.wallNorth;
            sField.wallBottom = field.wallSouth;
            sField.wallLeft = field.wallWest;
            sField.wallRight = field.wallEast;
            sField.elementName = field.elementName;
            System.out.print("Key: "+key+" Element: ");
            sField.gameElementString = serializeElement(field.element);
            sField.laserBeam = field.laserBeam;
            sField.checkpoint = field.checkpoint;
            sBoard.list.add(sField);
        });
        return gson.toJson(sBoard);

    }
    private String serializeElement(GameElement element){
        System.out.println(element);
        String output = gson.toJson(element);
        return output;
    }

    public GameBoard deserializeBoard(String json){
        GameBoard board = new GameBoard();
        SerializableGameBoard sBoard = gson.fromJson(json,SerializableGameBoard.class);
        for (SerializableGameField sField: sBoard.list) {
            Position key = sField.key;
            GameField field = new GameField(key);
            field.isActive = sField.isActive;
            field.wallEast = sField.wallRight;
            field.wallWest = sField.wallLeft;
            field.wallNorth = sField.wallTop;
            field.wallSouth = sField.wallBottom;
            field.elementName = sField.elementName;
            field.element = deserializeElement(sField.gameElementString, sField.elementName);
            field.laserBeam = sField.laserBeam;
            field.checkpoint = sField.checkpoint;
            board.add(field);
        }
        System.out.println(board);
        return board;
    }
    private GameElement deserializeElement(String json, ElementName name){
        Class classname;
        switch (name){
            case GEAR -> classname = Gear.class;
            case PUSHPANEL -> classname = PushPanel.class;
            case ENERGYSPACE -> classname = EnergySpace.class;
            case CONVEYORBELT -> classname = ConveyorBelt.class;
            case BOARDLASER -> classname = BoardLaser.class;
            case PIT -> classname = Pit.class;
            case NO_ELEMENT -> {
                return null;
            }
            default -> {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        GameElement element = (GameElement) gson.fromJson(json,classname);
        return element;
    }
}
