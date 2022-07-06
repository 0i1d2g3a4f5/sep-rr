package aexperimental;

import com.google.gson.JsonObject;
import gamelogic.game_elements.Gear;

import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args){
        Gear gear = new Gear(Gear.GearDirection.CLOCKWISE);
        System.out.println("before");

        JsonObject jsonObject = gear.toJson();
        System.out.println("after");

        System.out.println(jsonObject);
    }
}
