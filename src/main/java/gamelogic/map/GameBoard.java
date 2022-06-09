package gamelogic.map;



import gamelogic.Position;
import gamelogic.game_elements.GameElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameBoard {
    int dimension1;
    int dimension2;
    public List<List<List<GameElement>>> boardMap;

    @Override
    public String toString() {
        return "GameBoard{" +
                "boardMap=" + boardMap +
                '}';
    }
/*
    public boolean addField(GameField field){
        boardMap.put(field.key,field);
        return true;
    }

 */
    public boolean addElement(Position position, GameElement gameElement){
        boardMap.get(position.getX()).get(position.getY()).add(gameElement);
        return true;
    }
    /*

    public GameField getField(int i, int j){
        return boardMap.get(i).get(j).get(0);
    }

     */
    /*
    public GameField getField(Position position){
        return boardMap.get(position);
    }

     */

    /*
    public ArrayList getRegistry(){
        ArrayList list = new ArrayList();
        boardMap.forEach((key,value)->{
            if(value.element!=null)
            list.add(value.element);
        });
        return list;
    }

     */
/*
    /**
     * @author Ringer
     * @param leftTop
     * @param rightBottom
     * @param isActive
     *//*
    public void changeActiveStatus(Position leftTop,Position rightBottom,boolean isActive){
        for (int i = leftTop.getY();i<=rightBottom.getY();i++){
            for (int j = leftTop.getX();j<=rightBottom.getX();j++){
                getField(i,j).isActive=isActive;
            }
        }
    }
*/



    GameBoard(){

    }






}
