package client_application;

import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.map.GameBoard;
import gamelogic.Position;
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

    /**
     * handles task enums by realizing changes in client application
     *
     * @param task
     */
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
            }
            case UPDATEFULLGAMEBOARD -> {
                try {
                    while (clientApplication.clientGameBasicController==null){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    clientApplication.clientGameBasicController.updateGameBoard(gridPaneFromGameBoard(Game.getInstance().getMap(), clientApplication.basicClient.getFigure()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case UPDATEGAMEBOARDPARTS -> {
                ArrayList<Position> tomorrowPower = clientApplication.getClient().getHighSlumber();
                JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
                while (tomorrowPower.size()>0){
                    Position opportunity = tomorrowPower.get(0);
                    javaFXGridHandler.handleGameElementEnum(clientApplication.clientGameBasicController.getGameBoardContent(), opportunity.getX(), opportunity.getY(), clientApplication.getClient().getGame().getMap(), clientApplication.getClient().getFigure() );
                    tomorrowPower.remove(0);
                }
            }
            case UPDATE_HANDCARDS -> {
                clientApplication.clientGameBasicController.updateHandCards(gridPaneFromCardList(clientApplication.getClient().getPlayer().getAvailableCardsOwn(), 1) );
            }
            case UPDATE_PROGCARDS -> {
                clientApplication.clientGameBasicController.updateProgrammingCards(gridPaneFromCardList(clientApplication.getClient().getPlayer().getRegisterCardsOwn(), 2));
            }
            case UPDATEOTHERSREGISTERS -> {
                ArrayList<ArrayList<Boolean>> list = new ArrayList<>();
                ArrayList<String> names = new ArrayList<>();
                ArrayList<Integer> figures = new ArrayList<>();
                for(int i=0; i<clientApplication.getClient().getPlayerList().size(); i++){
                    names.add(clientApplication.getClient().getPlayerList().get(i).getName());
                    figures.add(clientApplication.getClient().getPlayerList().get(i).getFigure());
                    list.add(clientApplication.getClient().getPlayerList().get(i).getPlayer().getRegisterCardsOther());
                }
                clientApplication.clientGameBasicController.updateOtherRegisters(gridPaneFromMultipleBoolLists(list, names, figures, 3));
            }
            case STARTING_POINT_NOT_AVAILABLE -> {
                clientApplication.clientGameBasicController.setTextStartPos("This point is already taken by another player");
                clientApplication.activateStartingPoint(true);
            }
            case STARTING_POINT_INVALID-> {
                clientApplication.clientGameBasicController.setTextStartPos("The point you have chosen is not a starting code - try again :)");
                clientApplication.activateStartingPoint(true);
            }
            case CHOOSE_STARTING_POINT -> {
                clientApplication.clientGameBasicController.setTextStartPos("Please select a starting position by clicking on a starting point");
                clientApplication.activateStartingPoint(true);
                clientApplication.clientGameBasicController.setStartingChooseVisualActive(true);
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

    public GridPane gridPaneFromGameBoard(GameBoard gameBoard, int ownFigure){
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        return javaFXGridHandler.gridPaneFromGameBoard(gameBoard, ownFigure);
    }

    public GridPane gridPaneFromCardList(ArrayList<Card> cardArrayList, int type){
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        return javaFXGridHandler.gridPaneFromCards(cardArrayList, type);
    }

    public GridPane gridPaneFromMultipleBoolLists(ArrayList<ArrayList<Boolean>> boolArrayList, ArrayList<String> names, ArrayList<Integer> figures, int type){
        GridPane result = new GridPane();
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        for(int i=0; i<boolArrayList.size(); i++){
            ArrayList<Boolean> temp = boolArrayList.get(i);
            try {
                result.add(javaFXGridHandler.gridPaneFromBooleanList(temp, names.get(i), figures.get(i), type), i, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}