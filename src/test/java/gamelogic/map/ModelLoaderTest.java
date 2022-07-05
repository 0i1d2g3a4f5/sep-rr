package gamelogic.map;

import net.jqwik.api.Example;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ModelLoaderTest {

    @Example
    void loadMap() {
        ModelLoader modelLoader = new ModelLoader();
        try {
            modelLoader.loadMap("dizzy_highway");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       // System.out.println(modelLoader.mapJson);
    }

    @Example
    void readFile() {
    }
}