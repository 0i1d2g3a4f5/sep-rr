package messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * @author Sarp Cagin Erdogan
 * @author Mark Ringer
 */
public class Serializer {
    public Serializer(){

    }


    public Message deserialize(String inputString){
        Gson gson = new GsonBuilder().create();
        DataPackage dataPackage = gson.fromJson(inputString, DataPackage.class);
        int type = dataPackage.getPackageType();
        Message message;
        switch (type){
            case 0 -> {
                message = gson.fromJson(dataPackage.getPackageBody(), CommandMessage.class);
            }

            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return message;
    }
    public String serialize(Message message){
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);
        int type = 0;
        switch (message.getClass().getSimpleName()){

            case "CommandMessage"-> {
                type = 2;
            }
            default ->{

            }
        }
        DataPackage dataPackage = new DataPackage(type,jsonMessage);
        String jsonPackage = gson.toJson(dataPackage);
        return jsonPackage;
    }
}
