package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gamelogic.Game;
import gamelogic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void testToString() {
    }

    @Test
    void addField() {
        /*
        GameBoard board = new GameBoard();
        GameField field = new GameField(new Position(1,2));
        board.addElement(new Position(1,1),field);
        assertEquals(board.getField(1,2),field);

         */
    }

    @Test
    void getField() {
    }

    @Test
    void getRegistry() throws IOException {
        ArrayList list = new ArrayList();
        Game game = Game.getInstance();
        game.setup();
        /*
        game.board.boardMap.forEach((key,value)->{
            if(value.element!=null){
                list.add(value.element);
            }
        });
        assertEquals(list,game.board.getRegistry());

         */
    }

    @Test
    void changeActiveStatus() {
    }

    @Test
    void testToString1() {
    }

    @Test
    void addElement() {
    }

    @Test
    void testAddElement() {
    }

    @Test
    void testGetRegistry() {
    }

    @Test
    void getGameField() {
    }

    @Test
    void fromJson() {
    }


    @Test
    void testFromJson() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ModelLoader modelLoader = new ModelLoader();
        String before = modelLoader.readFile("DizzyHighway");
        JsonObject jsonObject = gson.fromJson(before,JsonObject.class);
        GameBoard gameBoard = GameBoard.fromJson(jsonObject);
        String after = gson.toJson(gameBoard.toJson());
        String filePath = "src/main/resources/MapModels/"+"DizzyHighway"+".json";
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

    @Test
    void toJson() {
    }
}