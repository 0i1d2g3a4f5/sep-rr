package client_application;

import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.map.GameBoard;
import gamelogic.Game;
import gamelogic.Player;
import javafx.scene.layout.GridPane;
import sarpLovesJavaFX.JavaFXGridHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sarp Cagin Erdogan
 */
public class TaskHandler {
    ClientApplication clientApplication;
    public TaskHandler(ClientApplication clientApplication){
        this.clientApplication=clientApplication;
    }
    public void handleTask(Task task){
        switch (task.taskType){
            case FAILEDSOCKET -> {
                clientApplication.clientStartBasicController.failedReset();
            }
            case GOTID -> {
                TaskInt1 taskInt1 = new TaskInt1(task);
                clientApplication.basicClient.setId(taskInt1.int1);
                clientApplication.clientStartBasicController.createdSocket();
            }
            case CHATMESSAGE -> {
                TaskString1 taskString1 = new TaskString1(task);
                clientApplication.clientChatBasicController.appendChat(taskString1.string1);
            }
            case FIGURETAKEN -> {
                clientApplication.clientNameBasicController.activate();
                clientApplication.clientNameBasicController.setFeedback("Figure already taken.");
            }
            case LAUNCHLOBBY -> {
                clientApplication.launchBasicLobby();
            }
            case UPDATEREADYBUTTON -> {
                TaskBoolean taskBoolean = new TaskBoolean(task);
                if(taskBoolean.bool){
                    clientApplication.clientLobbyBasicController.activateNot();
                }
                else {
                    clientApplication.clientLobbyBasicController.activateReady();
                }
            }
            case UPDATELOBBYLIST -> {
                if(clientApplication.lobbyActive)
                    clientApplication.clientLobbyBasicController.updateList();
            }
            case AVAILABLEMAPS -> {
                TaskJsonArray taskJsonArray = new TaskJsonArray(task);
                clientApplication.launchBasicMap();
                clientApplication.clientMapBasicController.initializeMaps(taskJsonArray.jsonArray);

            }
            case TRIGGERSTART -> {
                clientApplication.stageBasicLobby.close();
                if(clientApplication.stageSelection!=null) {
                    clientApplication.stageSelection.close();
                }
                clientApplication.launchBasicGame();

            }

            case TRIGGERFINISH -> {
                if(!Game.getInstance().getContinueGame()){
                    clientApplication.clientGameBasicController.triggerGameFinishedScene();
                }

            }
            case UPDATEGAMEBOARD -> {
                try {
                    clientApplication.clientGameBasicController.updateGameBoard(gridPaneFromGameBoard(Game.getInstance().getMap()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            case UPDATE_HANDCARDS -> {
                clientApplication.clientGameBasicController.updateHandCards(gridPaneFromCardList(clientApplication.getClient().getPlayer().getHandCards(), true));


            }
            case UPDATE_PROGCARDS -> {
                ArrayList<Card> bla = fromArrayToList(clientApplication.getClient().getPlayer().getRegisterCards());
                clientApplication.clientGameBasicController.updateProgrammingCards(gridPaneFromCardList(bla, true));
            }
            case UPDATEOTHERSREGISTERS -> {
                ArrayList<ArrayList<Card>> res = new ArrayList<>();
                for(CPlayer cPlayer : clientApplication.getClient().getGame().getPlayerList()){
                    res.add(fromArrayToList(cPlayer.getRegisterCards()));
                }
                clientApplication.clientGameBasicController.updateOtherRegisters(gridPaneFromMultipleCardLists(res, false));
            }
            case ERROR -> {

            }
            default -> {

            }
        }
    }
    public ArrayList<Card> fromArrayToList(Card[] inp){
        ArrayList<Card> bla = new ArrayList<>();
        for(int i=0; i<inp.length; i++){
            bla.add(inp[i]);
        }
        return bla;
    }
    public GridPane gridPaneFromGameBoard(GameBoard gameBoard){
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        return javaFXGridHandler.gridPaneFromGameBoard(gameBoard);
    }

    /*
    public ListView<String> listViewFromCardList(Task task) {
        TaskJsonArray taskJsonArray = new TaskJsonArray(task);
        ArrayList<Card> cardArrayList = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        for(int i = 0; i<taskJsonArray.jsonArray.size(); i++){
            try {
                cardArrayList.add(cardFactory.createCard(CardName.parseCardName(taskJsonArray.jsonArray.get(i).getAsString())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        return javaFXGridHandler.listViewFromCards(cardArrayList);
    }
    */

    public GridPane gridPaneFromCardList(ArrayList<Card> cardArrayList, boolean isVisible){
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        return javaFXGridHandler.gridPaneFromCards(cardArrayList, isVisible);
    }

    public GridPane gridPaneFromMultipleCardLists(ArrayList<ArrayList<Card>> cardArrayList, boolean isVisible){
        GridPane result = new GridPane();
        for(ArrayList<Card> temp : cardArrayList){
            GridPane tempp = gridPaneFromCardList(temp, isVisible);
            result.getChildren().add(tempp);
        }
        return result;

    }

}