package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gamelogic.Position;
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
     * @param args
     */
    public static void main(String[] args) throws IOException {
        int dimension1 = 10;
        int dimension2 = 13;

        GameBoard board = new GameBoard(dimension1,dimension2);

        for(int y=0;y<dimension1;y++){
            for(int x=0;x<dimension2;x++){
                board.addField(y,x,new Empty());
            }
        }

        board.addField(0,0,new ConveyorBelt(GREEN,EAST));
        board.addField(0,2,new ConveyorBelt(GREEN,EAST));
        board.addField(0,4,new ConveyorBelt(BLUE,SOUTH));
        board.addField(0,6,new ConveyorBelt(BLUE,SOUTH));
        board.addField(0,12,new EnergySpace(1));
        board.addField(1,4,new ConveyorBelt(BLUE,SOUTH));
        for(int i =5;i<13;i++)
        board.addField(1,i,new ConveyorBelt(BLUE,WEST));
        board.addField(2,1,new Wall(new ArrayList<>() { {add(NORTH);}}));
        for(int i = 2;i<8;i++)
        board.addField(i,4,new ConveyorBelt(BLUE,SOUTH));
        board.addField(2,5,new EnergySpace(1));
        for(int i = 2;i<10;i++)
        board.addField(i,11,new ConveyorBelt(BLUE,NORTH));
        board.addField(2,12,new ConveyorBelt(BLUE,WEST));

        board.addField(3,6,new Wall(new ArrayList<>() { {add(NORTH);}}));
        board.addField(3,8,new Wall(new ArrayList<>() { {add(WEST);}}));
        board.addField(3,8,new Laser(EAST,1));
        board.addField(3,9,new Wall(new ArrayList<>() { {add(EAST);}}));

        board.addField(3,12, new Checkpoint(1));

        board.addField(4,2,new Wall(new ArrayList<>() { {add(EAST);}}));
        board.addField(4,6,new Wall(new ArrayList<>() { {add(SOUTH);}}));
        board.addField(4,6,new Laser(NORTH,1));

        board.addField(4,8,new EnergySpace(1));
        board.addField(5,2,new Wall(new ArrayList<>() { {add(EAST);}}));
        board.addField(5,7,new EnergySpace(1));
        board.addField(5,9,new Wall(new ArrayList<>() { {add(NORTH);}}));
        board.addField(5,9,new Laser(SOUTH,1));

        board.addField(6,6,new Wall(new ArrayList<>() { {add(WEST);}}));

        board.addField(6,7,new Wall(new ArrayList<>() { {add(WEST);}}));
        board.addField(6,7,new Laser(WEST,1));
        board.addField(6,9,new Wall(new ArrayList<>() { {add(SOUTH);}}));

        board.addField(7,1,new Wall(new ArrayList<>() { {add(SOUTH);}}));
        board.addField(7,3,new ConveyorBelt(BLUE,EAST));
        board.addField(7,10,new EnergySpace(1));

        board.addField(8,3,new ConveyorBelt(BLUE,EAST));

        board.addField(9,2,new ConveyorBelt(GREEN,EAST));
        board.addField(9,3,new EnergySpace(1));
        board.addField(9,10,new ConveyorBelt(BLUE,NORTH));
        board.addField(9,11,new ConveyorBelt(BLUE,NORTH));


        System.out.println("boardSize: "+board.boardMap.size());
        String before = board.toString();
        String filePath = "dizzy_highway.json";
        File file;
        MapSerializer serializer = new MapSerializer();
        //System.out.println(board);
        String json="";
        try {
            file = new File(filePath);
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));


           Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("gameMap",board.toJson());

            String fileContent =  gson.toJson(jsonObject);

            printWriter.println(fileContent);
            printWriter.flush();
            BufferedReader reader;
            try {
                reader= new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            String input;
            while (true) {
                try {
                    if (!((input=reader.readLine())!=null)) break;
                    json+=input;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        board=serializer.deserializeBoard(json);
        String after = board.toString();
        System.out.println(before.equals(after));
        System.out.println(before);
        System.out.println(after);

         */
    }
}
