package client_package.AI;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.*;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class BasicAI extends AIClient{
    public BasicAI(boolean isBasic, AIController aiController){
        super(isBasic, aiController);
    }
    public void createSocket(String ip, int port){
        try {
            setSocket(new Socket(ip, port));
        } catch (IOException e) {
            //COULDNT CREATE
            throw new RuntimeException(e);
        }
    }
    @Override
    public void listen() {
        setIsListening(true);
        Thread thread = new Thread(basicListener);
        thread.setDaemon(true);
        thread.start();
    }
    @Override
    public void process(JsonObject jsonObject){
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        try {
            message.activateMessageInAIFrontend(this, isBasic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void createSocket(){}

}
