package client;

import com.google.gson.JsonObject;

/**
 * @author Sarp Cagin Erdogan
 */
public class Task {
    String type;
    JsonObject task;
    Task(String type, JsonObject jsonObject){
        this.type=type;
        this.task=jsonObject;
    }

}
