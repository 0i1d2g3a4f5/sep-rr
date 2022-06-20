package newmessages;

import com.google.gson.JsonObject;

public class MessageFactory {

    public Message createMessage(MessageType messageType, JsonObject jsonObject){
        switch (messageType){

            case DEFAULT -> {

            }
            case HELLOCLIENT -> {
                return new MessageHelloClient(jsonObject);
            }
            case HELLOSERVER -> {
            }
            case WELCOME -> {
            }
            case PLAYERVALUES -> {
            }
            case ALIVE -> {
            }
            case ERROR -> {
            }
            case PLAYERADDED -> {
            }
            case SENDCHAT -> {
            }
            case RECEIVEDCHAT -> {
            }
            case SETSTATUS -> {
            }
            case PLAYERSTATUS -> {
            }
            case SELECTMAP -> {
            }
            case MAPSELECTED -> {
            }
            case ACTIVEPHASE -> {
            }
        }
        return null;
    }
}
