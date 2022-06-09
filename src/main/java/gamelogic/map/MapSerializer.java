package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gamelogic.Position;
import gamelogic.game_elements.*;

import java.io.IOException;
import java.util.ArrayList;

public class MapSerializer {

    /**
     * @author Ringer
     */
    class SerializableGameField{
        Position key;

        boolean isActive;
        boolean wallNorth;
        boolean wallSouth;
        boolean wallEast;
        boolean wallWest;
        LaserBeam laserBeam;

        Checkpoint checkpoint;
        ElementName elementName;
        String gameElementString;
    }

    /**
     * @author Ringer
     */
    class SerializableGameBoard{
        int dimension1;
        int dimension2;
        ArrayList<SerializableGameField> list;
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    //Gson gson = new Gson();

    /**
     * @author Ringer
     * @param board
     * @return
     */
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
        */
        return gson.toJson(sBoard);

    }

    /**
     * @author Ringer
     * @param element
     * @return
     */
    private String serializeElement(GameElement element){
        System.out.println(element);
        String output = gson.toJson(element);
        return output;
    }

    /**
     * @author Ringer
     * @param json
     * @return
     */
    public GameBoard deserializeBoard(String json){

        //GameBoard board = new GameBoard(dimension1, dimension2);
        SerializableGameBoard sBoard = gson.fromJson(json,SerializableGameBoard.class);
        /*
        for (SerializableGameField sField: sBoard.list) {
            Position key = sField.key;
            GameField field = new GameField(key);
            field.isActive = sField.isActive;
            field.setWallEast(sField.wallEast);
            field.setWallWest(sField.wallWest);
            field.setWallNorth(sField.wallNorth);
            field.setWallSouth(sField.wallSouth);
            field.elementName = sField.elementName;
            field.element = deserializeElement(sField.gameElementString, sField.elementName);
            field.laserBeam = sField.laserBeam;
            field.checkpoint = sField.checkpoint;
            board.addField(field);


        }

         */
        //System.out.println(board);
        return new GameBoard(1,2);
    }

    /**
     * @author Ringer
     * @param json
     * @param name
     * @return
     */
    private GameElement deserializeElement(String json, ElementName name){
        Class classname;
        switch (name){
            case GEAR -> classname = Gear.class;
            case PUSHPANEL -> classname = PushPanel.class;
            case ENERGYSPACE -> classname = EnergySpace.class;
            case CONVEYORBELT -> classname = ConveyorBelt.class;
            case LASER -> classname = Laser.class;
            case PIT -> classname = Pit.class;
            case EMPTY -> {
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
