package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gamelogic.Game;
import gamelogic.Position;
import net.jqwik.api.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Example
    void testToString() {
    }

    @Example
    void addField() {
        /*
        GameBoard board = new GameBoard();
        GameField field = new GameField(new Position(1,2));
        board.addElement(new Position(1,1),field);
        assertEquals(board.getField(1,2),field);

         */
    }

    @Example
    void getField() {
    }

    @Example
    void getRegistry() throws IOException, InterruptedException {
        ArrayList list = new ArrayList();
        Game game = Game.getInstance();
        //game.setup();
        /*
        game.board.boardMap.forEach((key,value)->{
            if(value.element!=null){
                list.add(value.element);
            }
        });
        assertEquals(list,game.board.getRegistry());

         */
    }

    @Example
    void changeActiveStatus() {
    }

    @Example
    void testToString1() {
    }

    @Example
    void addElement() {
    }

    @Example
    void testAddElement() {
    }

    @Example
    void testGetRegistry() {
    }

    @Example
    void getGameField() {
    }

    @Example
    void fromJson() {
    }


    @Example
    void testFromJson() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ModelLoader modelLoader = new ModelLoader();
        System.out.println("setup");

        String before = modelLoader.readFile("dizzy_highway");
        System.out.println("Modelloader");
        JsonObject jsonObject = gson.fromJson(before,JsonObject.class);
        GameBoard gameBoard = new GameBoard(jsonObject);
        System.out.println("created");
        String after = gson.toJson(gameBoard.toJson());
        System.out.println("toString");
        String filePath = "src/main/resources/MapModels/"+"dizzy_highway"+".json";
        File file;
        //System.out.println(board);
        String json="";

        file = new File(filePath);
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        printWriter.write(after);
        printWriter.flush();
        assertEquals(10,gameBoard.dimensionY);
        assertEquals(10,gameBoard.getColumn(0).size());
        before =before.replaceAll("\n","");
        after = after.replaceAll("\n","");
        assertEquals(before.replaceAll(" ",""),after.replaceAll(" ",""));

    }

    @Example
    void toJson() {
    }
}