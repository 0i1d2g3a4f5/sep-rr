package client_package.client_gamelogic.map;

import gamelogic.map.ModelLoader;
import net.jqwik.api.Example;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ModelLoaderTest {
    @Example
    void loadMap() {
        System.out.println("testing");
        gamelogic.map.ModelLoader modelLoader = new ModelLoader();
        System.out.println("created loader");
        try {
            System.out.println("loading");
            modelLoader.loadMap("dizzy_highway");
            System.out.println("loaded");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(modelLoader.mapJson);
    }

}