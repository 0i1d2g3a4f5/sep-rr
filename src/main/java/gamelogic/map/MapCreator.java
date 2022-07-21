package gamelogic.map;

import gamelogic.game_elements.*;


import java.io.*;
import java.util.ArrayList;

import static gamelogic.Color.*;
import static gamelogic.Direction.*;

/**
 * @author Ringer
 */
public class MapCreator {

    /**
     * @author Ringer
     */
    public static GameBoard op() throws IOException {
        int dimension1 = 10;
        int dimension2 = 13;

        GameBoard board = new GameBoard(dimension1,dimension2);

        for(int y=0;y<dimension1;y++){
            for(int x=0;x<dimension2;x++){
                board.addElement(y,x,new Empty());
            }
        }

        //board.addField(0,0,new ConveyorBelt(GREEN,EAST));
        board.addElement(0,2,new ConveyorBelt(GREEN, RIGHT));
        board.addElement(0,4,new ConveyorBelt(BLUE, BOTTOM));
        board.addElement(0,6,new ConveyorBelt(BLUE, BOTTOM));
        board.addElement(0,12,new EnergySpace(1));
        board.addElement(1,4,new ConveyorBelt(BLUE, BOTTOM));
        for(int i =5;i<13;i++)
        board.addElement(1,i,new ConveyorBelt(BLUE, LEFT));
        board.addElement(2,1,new Wall(new ArrayList<>() { {add(TOP);}}));
        for(int i = 2;i<8;i++)
        board.addElement(i,4,new ConveyorBelt(BLUE, BOTTOM));
        board.addElement(2,5,new EnergySpace(1));
        for(int i = 2;i<10;i++)
        board.addElement(i,11,new ConveyorBelt(BLUE, TOP));
        board.addElement(2,12,new ConveyorBelt(BLUE, LEFT));

        board.addElement(3,6,new Wall(new ArrayList<>() { {add(TOP);}}));
        board.addElement(3,8,new Wall(new ArrayList<>() { {add(LEFT);}}));
        board.addElement(3,8,new Laser(RIGHT,1));
        board.addElement(3,9,new Wall(new ArrayList<>() { {add(RIGHT);}}));

        board.addElement(3,12, new Checkpoint(1));

        board.addElement(4,2,new Wall(new ArrayList<>() { {add(RIGHT);}}));
        board.addElement(4,6,new Wall(new ArrayList<>() { {add(BOTTOM);}}));
        board.addElement(4,6,new Laser(TOP,1));

        board.addElement(4,8,new EnergySpace(1));
        board.addElement(5,2,new Wall(new ArrayList<>() { {add(RIGHT);}}));
        board.addElement(5,7,new EnergySpace(1));
        board.addElement(5,9,new Wall(new ArrayList<>() { {add(TOP);}}));
        board.addElement(5,9,new Laser(BOTTOM,1));

        board.addElement(6,6,new Wall(new ArrayList<>() { {add(LEFT);}}));

        board.addElement(6,7,new Wall(new ArrayList<>() { {add(LEFT);}}));
        board.addElement(6,7,new Laser(LEFT,1));
        board.addElement(6,9,new Wall(new ArrayList<>() { {add(BOTTOM);}}));

        board.addElement(7,1,new Wall(new ArrayList<>() { {add(BOTTOM);}}));
        board.addElement(7,3,new ConveyorBelt(BLUE, RIGHT));
        board.addElement(7,10,new EnergySpace(1));

        board.addElement(8,3,new ConveyorBelt(BLUE, RIGHT));

        board.addElement(9,2,new ConveyorBelt(GREEN, RIGHT));
        board.addElement(9,3,new EnergySpace(1));
        board.addElement(9,10,new ConveyorBelt(BLUE, TOP));
        board.addElement(9,11,new ConveyorBelt(BLUE, TOP));


        String before = board.toString();
        String filePath = "dizzy_highway.json";
        File file;
        String json="";

        try {
            file = new File(filePath);

            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            String input;
            while (true) {
                try {
                    if (!((input = reader.readLine()) != null)) break;
                    json += input;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }




        /*
        MapDeserializer deserializer = new MapDeserializer();
        board=deserializer.deserializeMap(deserializer.gson.fromJson(json,JsonObject.class));
        String after = board.toString();
        System.out.println(before.equals(after));
        System.out.println(before);
        System.out.println(after);

         */
            return board;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
