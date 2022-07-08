package client_application;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.client_gamelogic.map.GameBoard;
import gamelogic.cards.CardName;
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
            case UPDATEGAMEBOARD -> {
                /*TaskJsonObject taskJsonObject = new TaskJsonObject(task);
                client_package.client_gamelogic.map.GameBoard gameBoard;
                try {
                    gameBoard = new client_package.client_gamelogic.map.GameBoard(taskJsonObject.jsonObject);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/
                clientApplication.clientGameBasicController.updateGameBoard(gridPaneFromGameBoard(this.clientApplication.basicClient.getGame().getMap()));
            }
            case UPDATE_HANDCARDS -> {
                //clientApplication.clientGameBasicController.updateHandCards(gridPaneFromCardList(this.clientApplication.basicClient.getPlayer().getHandCards()));
                // TODO want to load handcard list for player from getHandCards() - do we need to setHandCards() somewhere?

            }
            case UPDATE_PROGCARDS -> {
                //clientApplication.clientGameBasicController.updateProgrammingCards(gridPaneFromCardList(this.clientApplication.basicClient.getPlayer().getRegisterCards()));
                // TODO how to get register cards? bzw. do we need to get them?
                //clientApplication.clientGameBasicController.updateOwnRegister(gridPaneFromCardList(task));
            }
            case UPDATEOTHERSREGISTERS -> {
                clientApplication.clientGameBasicController.updateOtherRegisters(gridPaneFromMultipleCardLists(task));
            }
            case ERROR -> {

            }
            default -> {

            }
        }
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

    public GridPane gridPaneFromTask(Task task){
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
        return javaFXGridHandler.gridPaneFromCards(cardArrayList);
    }

    public GridPane gridPaneFromMultipleCardLists(Task task){
        TaskJsonArray2Levels taskJsonArray2Levels = new TaskJsonArray2Levels(task);
        CardFactory cardFactory = new CardFactory();
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        GridPane result = new GridPane();
        for(int i=0; i<taskJsonArray2Levels.stringContent.size(); i++){
            ArrayList<Card> cardArrayList = new ArrayList<>();
            for(int j=0; j<taskJsonArray2Levels.stringContent.get(i).size(); j++){
                try {
                    cardArrayList.add(cardFactory.createCard(CardName.parseCardName(taskJsonArray2Levels.stringContent.get(i).get(j))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            result.add(javaFXGridHandler.gridPaneFromCards(cardArrayList), i, 0);
        }
        return result;
    }

}