package basicClient;
import clientApplication.ClientApplication;
import clientApplication.Task;
import clientApplication.TaskContent;
import clientApplication.TaskType;
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
public class BasicClient {
    public List<BasicClient> playerList;
    ClientApplication clientApplication;
    public String group;
    public String name = "";
    int figure;
    public int id;
    Socket socket;
    public boolean isListening, isReady, isForList;
    MessageProcessor messageProcessor;
    public boolean idExists(int inp){
        for(BasicClient temp : playerList){
            if(temp.id==inp){
                return true;
            }
        }
        return false;
    }
    public BasicClient(ClientApplication clientApplication){
        this.clientApplication=clientApplication;
        messageProcessor=new MessageProcessor(this);
        isListening=false;
        playerList = new ArrayList<>();
        this.isForList=false;
        this.isReady=false;
    }
    public BasicClient(int id, String name, int figure){
        this.id=id;
        this.name=name;
        this.figure=figure;
        this.isForList=true;
        this.isReady=false;
    }
    public void startClient(String ip, int port, String groupname){

        Runnable socketCreation = new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, port);
                    group=groupname;
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
    public BasicClient clientFromId(int inp){
        for(BasicClient basicClient : this.playerList){
            if(basicClient.id==inp){
                return basicClient;
            }
        }
        return null;
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
                        Message message = new Message(jsonObject);
                        System.out.println("RECEIVED :: " + message.toString());
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
    public void sendSelf(Message message){

        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
            System.out.println("SENT :: " + message.toString());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
