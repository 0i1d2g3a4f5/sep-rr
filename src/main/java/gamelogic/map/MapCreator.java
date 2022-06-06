package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gamelogic.Color;
import gamelogic.Position;
import gamelogic.game_elements.*;


import java.io.*;

import static gamelogic.Color.*;
import static gamelogic.Direction.*;


public class MapCreator {

    /**
     * @author Ringer
     * @param args
     */
    public static void main(String[] args) {
        int dimension1 = 10;
        int dimension2 = 13;

        GameBoard board = new GameBoard();

        for(int i=0;i<dimension1;i++){
            for(int j=0;j<dimension2;j++){
                GameField field = new GameField(new Position(i,j));
                System.out.println(field.key);
                System.out.println(board.getField(i,j));
                board.addField(field);
            }
        }

        board.getField(0,2).addElement(new ConveyorBelt(GREEN,EAST));
        board.getField(0,4).addElement(new ConveyorBelt(BLUE,SOUTH));
        board.getField(0,6).addElement(new ConveyorBelt(BLUE,SOUTH));
        board.getField(0,12).addElement(new EnergySpace());
        board.getField(1,4).addElement(new ConveyorBelt(BLUE,SOUTH));
        for(int i =5;i<13;i++)
        board.getField(1,i).addElement(new ConveyorBelt(BLUE,WEST));
        board.getField(2,1).editWall(NORTH,true);
        for(int i = 2;i<8;i++)
        board.getField(i,4).addElement(new ConveyorBelt(BLUE,SOUTH));
        board.getField(2,5).addElement(new EnergySpace());
        for(int i = 2;i<10;i++)
        board.getField(i,11).addElement(new ConveyorBelt(BLUE,NORTH));
        board.getField(2,12).addElement(new ConveyorBelt(BLUE,WEST));

        board.getField(3,6).setLaserBeam(NORTH,1);
        board.getField(3,6).editWall(NORTH,true);
        board.getField(3,8).editWall(WEST,true);
        board.getField(3,8).addElement(new BoardLaser(EAST,1));
        board.getField(3,9).editWall(EAST,true);
        board.getField(3,9).setLaserBeam(EAST,1);
        board.getField(3,12).setCheckpoint(1);

        board.getField(4,2).editWall(EAST,true);
        board.getField(4,6).editWall(SOUTH,true);
        board.getField(4,6).addElement(new BoardLaser(NORTH,1));

        board.getField(4,8).addElement(new EnergySpace());
        board.getField(5,2).editWall(EAST,true);
        board.getField(5,7).addElement(new EnergySpace());
        board.getField(5,9).editWall(NORTH,true);
        board.getField(5,9).addElement(new BoardLaser(SOUTH,1));

        board.getField(6,6).editWall(WEST,true);
        board.getField(6,6).setLaserBeam(WEST,1);
        board.getField(6,7).editWall(WEST,true);
        board.getField(6,7).addElement(new BoardLaser(WEST,1));
        board.getField(6,9).editWall(SOUTH,true);
        board.getField(6,6).setLaserBeam(SOUTH,1);

        board.getField(7,1).editWall(SOUTH,true);
        board.getField(7,3).addElement(new ConveyorBelt(BLUE,EAST));
        board.getField(7,10).addElement(new EnergySpace());

        board.getField(8,3).addElement(new ConveyorBelt(BLUE,EAST));

        board.getField(9,2).addElement(new ConveyorBelt(GREEN,EAST));
        board.getField(9,3).addElement(new EnergySpace());
        board.getField(9,10).addElement(new ConveyorBelt(BLUE,NORTH));
        board.getField(9,11).addElement(new ConveyorBelt(BLUE,NORTH));


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
            String fileContent = serializer.serializeBoard(board);

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
        board=serializer.deserializeBoard(json);
        String after = board.toString();
        System.out.println(before.equals(after));
        System.out.println(before);
        System.out.println(after);
    }
}
