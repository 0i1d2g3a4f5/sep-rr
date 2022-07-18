package client_application;

import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.map.GameBoard;
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
                if(clientApplication.stageBasicMap!=null) {
                    clientApplication.stageBasicMap.close();
                }
                clientApplication.launchBasicGame();

            }

            case TRIGGERFINISH -> {
                /*
                if(!Game.getInstance().getContinueGame()){
                    clientApplication.clientGameBasicController.triggerGameFinishedScene();
                }*/

            }
            case UPDATEGAMEBOARD -> {
                try {
                    clientApplication.clientGameBasicController.updateGameBoard(gridPaneFromGameBoard(Game.getInstance().getMap()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            case UPDATE_HANDCARDS -> {
                clientApplication.clientGameBasicController.updateHandCards(gridPaneFromCardList(clientApplication.getClient().getPlayer().getAvailableCardsOwn(), 1) );


            }
            case UPDATE_PROGCARDS -> {
                clientApplication.clientGameBasicController.updateProgrammingCards(gridPaneFromCardList(clientApplication.getClient().getPlayer().getRegisterCardsOwn(), 2));
            }
            case UPDATEOTHERSREGISTERS -> {
                System.out.println("TASK UPDATE OTHER");
                ArrayList<ArrayList<Boolean>> list = new ArrayList<>();
                for(int i=0; i<clientApplication.getClient().getPlayerList().size(); i++){
                    System.out.println("ADDED REGISTER OF PLAYER " + i);
                    list.add(clientApplication.getClient().getPlayerList().get(i).getPlayer().getRegisterCardsOther());
                }
                clientApplication.clientGameBasicController.updateOtherRegisters(gridPaneFromMultipleBoolLists(list, 3));
            }
            case STARTING_POINT_NOT_AVAILABLE -> {
                clientApplication.clientGameBasicController.setStartingText("This point was taken by another player :(");
                clientApplication.activateStartingPoint(true);
            }
            case STARTING_POINT_INVALID-> {
                clientApplication.clientGameBasicController.setStartingText("This point IS NOT A STARTING POINT YOU BLIND STUPID PIECE OF SHIT :(");
                clientApplication.activateStartingPoint(true);
            }
            case CHOOSE_STARTING_POINT -> {
                clientApplication.clientGameBasicController.setStartingText("Choose a starting point now :)");
                clientApplication.activateStartingPoint(true);
            }
            case ERROR -> {

            }
            case REBOOTDIRECTION ->{
                clientApplication.clientGameBasicController.startChoosingDirection();
            }

            case WIN -> {
                clientApplication.clientGameBasicController.startWinnerScene();
            }

            case LOSE -> {
                clientApplication.clientGameBasicController.startLoserScene();
            }

            default -> {

            }
        }
    }
    public ArrayList<Card> fromArrayToList(Card[] inp){
        ArrayList<Card> bla = new ArrayList<>();
        for(int i=0; i<inp.length; i++){
            bla.add(null);
            if(inp[i]!=null) {
                bla.set(i, inp[i]);
            }
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

    public GridPane gridPaneFromCardList(ArrayList<Card> cardArrayList, int type){
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        return javaFXGridHandler.gridPaneFromCards(cardArrayList, type);
    }

    public GridPane gridPaneFromMultipleBoolLists(ArrayList<ArrayList<Boolean>> boolArrayList, int type){
        GridPane result = new GridPane();
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        for(int i=0; i<boolArrayList.size(); i++){
            ArrayList<Boolean> temp = boolArrayList.get(i);
            try {
                result.add(javaFXGridHandler.gridPaneFromBooleanList(temp, type), i, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;

    }

}