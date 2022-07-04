package client_application;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TaskJsonArray2Levels extends TaskContent{
    public JsonArray jsonArray;
    public ArrayList<ArrayList<String>> stringContent;
    public TaskJsonArray2Levels(JsonArray jsonArray){
        super(jsonArray);
        this.jsonArray=jsonArray;
        this.stringContent=stringListFromJson(jsonArray);
    }
    public TaskJsonArray2Levels(Task task){
        super(task);
        jsonArray=content.get("1").getAsJsonArray();
        this.stringContent=stringListFromJson(jsonArray);
    }
    public ArrayList<ArrayList<String>>  stringListFromJson(JsonArray jsonArray){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i=0; i<jsonArray.size(); i++){
            JsonArray temp = jsonArray.get(i).getAsJsonArray();
            ArrayList<String> temp2 = new ArrayList<>();
            for(int j=0; j<temp.size(); j++){
                temp2.add(temp.get(j).getAsString());
            }
            result.add(temp2);
        }
        return result;
    }
}
