package gamelogic.map;



import gamelogic.Position;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
    int dimension1;
    int dimension2;
    public HashMap<Position,GameField> boardMap = new HashMap();

    @Override
    public String toString() {
        return "GameBoard{" +
                "boardMap=" + boardMap +
                '}';
    }

    public boolean addField(GameField field){
        boardMap.put(field.key,field);
        return true;
    }

    public GameField getField(int i, int j){
        return boardMap.get(new Position(i,j));
    }
    public GameField getField(Position position){
        return boardMap.get(position);
    }

    public ArrayList getRegistry(){
        ArrayList list = new ArrayList();
        boardMap.forEach((key,value)->{
            if(value.element!=null)
            list.add(value.element);
        });
        return list;
    }

    public void changeActiveStatus(Pair<Integer,Integer> leftTop,Pair<Integer,Integer> rightBottom,boolean isActive){
        for (int i = leftTop.getKey();i<=rightBottom.getKey();i++){
            for (int j = leftTop.getValue();j<=rightBottom.getValue();j++){
                boardMap.get(new Pair<>(i,j)).isActive=isActive;
            }
        }
    }



    GameBoard(){

    }






}
