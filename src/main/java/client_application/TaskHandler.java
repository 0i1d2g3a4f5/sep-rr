package client_application;

import gamelogic.map.GameBoard;
import gamelogic.map.MapCreator;
import sarpLovesJavaFX.FXMLGridsAreTheBest;

import java.io.IOException;

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
                TaskString1 taskString1 = new TaskString1(task);
                switch (taskString1.string1){
                    case "Dizzy Highway" -> {
                        try {
                            GameBoard gameBoard = MapCreator.op();
                            clientApplication.launchMapView(FXMLGridsAreTheBest.fromMap(gameBoard));
                            clientApplication.launchBasicGame();
                            clientApplication.stageBasicMap.close();
                            clientApplication.stageBasicLobby.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            case ERROR -> {

            }
            default -> {

            }
        }
    }
}