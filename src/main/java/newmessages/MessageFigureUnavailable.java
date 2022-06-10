package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageFigureUnavailable extends Message{
    public int figure;
    public MessageFigureUnavailable(int i){
        super(i);
        type="FigureUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Figure", new JsonPrimitive(i));
        content = jsonObject;
    }
    public MessageFigureUnavailable(JsonObject jsonObject){
        super(jsonObject);
        figure=content.get("Figure").getAsInt();
    }

}
