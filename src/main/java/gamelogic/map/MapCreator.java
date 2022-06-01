package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.game_elements.BoardLaser;
import gamelogic.game_elements.ConveyorBelt;
import gamelogic.game_elements.Gear;
import javafx.util.Pair;

import java.io.*;

import static gamelogic.Direction.*;
import static java.awt.Color.BLUE;

public class MapCreator {

    public static void main(String[] args) {
        int dimension1 = 12;
        int dimension2 = 12;

        GameBoard board = new GameBoard();

        for(int i=0;i<dimension1;i++){
            for(int j=0;j<dimension2;j++){
                GameField field = new GameField(new Position(i,j));
                System.out.println(field.key);
                System.out.println(board.getField(i,j));
                board.add(field);
            }
        }

        System.out.println(board.boardMap.get(new Position(0,0)));
        board.getField(4,5).addElement(new BoardLaser(2,NORTH));

        /*
        board.boardMap.get(new Position(11,11)).addElement(new BoardLaser(3, NORTH));
        board.boardMap.get(new Position(2,3)).addElement(new Gear(false));
        board.getField(2,2).addElement(new ConveyorBelt(BLUE,NORTH));

         */
        //board.changeActiveStatus(new Pair<>(0,0),new Pair<>(4,5),true);

        System.out.println("boardSize: "+board.boardMap.size());
        String before = board.toString();
        String filePath = "map1.json";
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
