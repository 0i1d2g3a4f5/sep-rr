package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class MessageValuesAccepted extends Message{
    public String name;
    public int figure;
    public MessageValuesAccepted(String name, int figure){
        super();
        type="ValuesAccepted";
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Figure", new JsonPrimitive(figure));
        content=jsonObject;

    }
    public MessageValuesAccepted(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        figure=content.get("Figure").getAsInt();
    }

}
