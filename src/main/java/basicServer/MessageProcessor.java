package basicServer;

import com.google.gson.JsonObject;
import newmessages.*;

public class MessageProcessor {
    Client client;
    public MessageProcessor(Client client){
        this.client=client;
    }
    public void process(JsonObject jsonObject){
        Message message = new Message(jsonObject);
        switch (message.messageType){
            case HELLOSERVER -> {
                MessageHelloServer messageHelloServer = new MessageHelloServer(jsonObject);
                if(!messageHelloServer.protocol.equals("Version 0.1")){
                    client.sendSelf(new MessageError("ERROR :: False communication protocol."));
                }
                else{
                    client.isAI= messageHelloServer.isAI;
                    client.group= messageHelloServer.group;
                    client.sendSelf(new MessageWelcome(client.id));
                }
            }
            case PLAYERVALUES -> {
                MessagePlayerValues messagePlayerValues = new MessagePlayerValues(jsonObject);
                client.checkValues(messagePlayerValues.name, messagePlayerValues.figure);

            }
            case SENDCHAT -> {
                MessageSendChat messageSendChat = new MessageSendChat(jsonObject);
                if(messageSendChat.to==-1){
                    client.sendAll(new MessageReceivedChat(messageSendChat.message, client.id, false));
                }
                else{
                    if(client.server.clientFromId(messageSendChat.to)!=null) {
                        client.sendSingle(client.server.clientFromId(messageSendChat.to), new MessageReceivedChat(messageSendChat.message, client.id, true));
                    }
                    else{
                        client.sendSelf(new MessageError("ERROR :: Invalid private message recepient."));
                    }
                }
            }
            case SETSTATUS -> {
                MessageSetStatus messageSetStatus = new MessageSetStatus(jsonObject);
                client.isReady=messageSetStatus.ready;
                client.sendAll(new MessagePlayerStatus(client.id, client.isReady));
                if(client.isReady){
                    client.server.readyList.add(client);
                    client.server.checkReady();
                }
                else{
                    client.server.readyList.remove(client);
                }
            }
            case DEFAULT -> {
            }
            default -> {
            }
        };


    }
}
