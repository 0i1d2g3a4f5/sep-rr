package client_package.basicClient;
import client_application.ClientApplication;
import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import messages.*;
import utility.GlobalParameters;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicClient extends Client {
    public BasicClient(ClientApplication clientApplication){
        super(clientApplication);
    }
    public BasicClient(int id, String name, int figure){
        super();
        setId(id);
        setName(name);
        setFigure(figure);
        setIsForList(true);
        setIsReady(false);
        setIsReady(false);
        setIsListening(false);
    }

    /**
     * starts new client
     * @param ip
     * @param port
     * @param groupName
     */
    public void startClient(String ip, int port, String groupName){
        Runnable socketCreation = new Runnable() {
            @Override
            public void run() {
                try {
                    setSocket(new Socket(ip, port));
                    setGroup(groupName);
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
        sendSelf(new MessageHelloServer(group, false, GlobalParameters.PROTOCOL_VERSION));
    }

    /**
     * processes incoming messages
     *
     * @param jsonObject
     * @param client
     */
    @Override
    public void process(JsonObject jsonObject, Client client){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
                Message message = new MessageFactory().createMessage(messageType, jsonObject);
                try {
                    message.activateMessageInFrontend(client);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClientNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void sendSelf(Message temp) {
        toSendList.add(temp);
        handleToBeSent();
    }
    public void handleToBeSent(){
        while(toSendList.size()>0){
            Message message = toSendList.get(0);
            String string = message.toJSON().toString();
            string+="\n";
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(getSocket().getOutputStream())));
                bufferedWriter.write(string);
                bufferedWriter.flush();
                Client.clientLogger.info("Sent message: " + string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            toSendList.remove(0);
        }
    }

    @Override
    public void listen() {
        setIsListening(true);
        Thread listenerThread = new Thread(new Runnable(){
            @Override
            public void run() {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (isListening) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        while(bufferedReader.ready()) {
                            String received = bufferedReader.readLine();
                            receivedMessages.add(received);
                            Client.clientLogger.info("Received message: " + received);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    handleReceivedMessages();


                }
            }
        });
        listenerThread.start();

    }
    public void handleReceivedMessages(){
        while (receivedMessages.size()>0){
            String string = receivedMessages.get(0);
            JsonObject jsonObject = new Gson().fromJson(string, JsonObject.class);
            process(jsonObject, this);
            receivedMessages.remove(0);
        }
    }
}
