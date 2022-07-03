package client_package.basicClient;
import client_application.ClientApplication;
import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.*;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @author Sarp Cagin Erdogan
 */
public class BasicClient extends Client {


    public BasicClient(ClientApplication clientApplication){
        super(clientApplication, true);
    }
    public BasicClient(int id, String name, int figure){
        super();
        setIsBasic(true);
        setId(id);
        setName(name);
        setFigure(figure);
        setIsForList(true);
        setIsReady(false);
        setIsListening(false);
    }
    public void startClient(String ip, int port, String groupname){

        Runnable socketCreation = new Runnable() {
            @Override
            public void run() {
                try {
                    setSocket(new Socket(ip, port));
                    setGroup(groupname);
                    socketCreationSuccessful();
                } catch (UnknownHostException e) {
                    clientApplication.addAndExecuteTask(new Task(TaskType.FAILEDSOCKET, new TaskContent()));
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    clientApplication.addAndExecuteTask(new Task(TaskType.FAILEDSOCKET, new TaskContent()));
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(socketCreation);
        thread.setDaemon(true);
        thread.start();



    }


    void socketCreationSuccessful(){
        listen();
        sendSelf(new MessageHelloServer(group, false, "Version 1.0"));
    }
    @Override
    public void process(JsonObject jsonObject){
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        try {
            message.activateMessageInFrontend(this, isBasic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void listen(){
        setIsListening(true);
        Thread thread = new Thread(basicListener);
        thread.setDaemon(true);
        thread.start();

    }


}
