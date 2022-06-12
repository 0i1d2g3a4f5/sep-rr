package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {
    Client client;
    Server server;
    @BeforeEach
    void before(){
        client = new Client();
    }

    @Test
    void checkName() {
        client.checkName("dfsgezetzewf",4);

    }

    @Test
    void checkFigure() {
    }
}