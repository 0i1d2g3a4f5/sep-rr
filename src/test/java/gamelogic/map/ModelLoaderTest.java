package gamelogic.map;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ModelLoaderTest {

    @Test
    void loadMap() {
        ModelLoader modelLoader = new ModelLoader();
        try {
            modelLoader.loadMap("DizzyHighway");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(modelLoader.mapJson);
    }

    @Test
    void readFile() {
    }
}