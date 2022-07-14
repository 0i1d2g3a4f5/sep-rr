package client_package.AI;

import client_package.Client;
import server_package.Server;

public class TestMain {
    public static void main(String[] args){
        ExampleEnum exampleEnum =ExampleEnum.HALLO;

        ExampleEnum exampleEnum1 = ExampleEnum.valueOf("by");
        Client.clientLogger.info(exampleEnum1);
    }
}
