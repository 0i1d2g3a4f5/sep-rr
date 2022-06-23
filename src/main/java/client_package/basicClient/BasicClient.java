package client_package.basicClient;
import client_application.ClientApplication;
import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.Message;
import newmessages.MessageHelloServer;

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
        super(true);
        setId(id);
        setName(name);
        setFigure(figure);
        setIsForList(true);
        setIsReady(false);
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

    Runnable listener = new Runnable() {
        @Override
        public void run() {
            while(isListening){
                try {

                    TimeUnit.MILLISECONDS.sleep(100);
                    if (socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();
                        System.out.println("RECEIVED :: " + jsonObject);
                        messageProcessor.process(jsonObject);
                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };
    void socketCreationSuccessful(){
        listen();
        sendSelf(new MessageHelloServer(group, false, "Version 0.1"));
    }
    void listen(){
        isListening=true;
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();

    }


}
