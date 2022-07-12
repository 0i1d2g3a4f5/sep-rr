package client_package.AI;

import server_package.Server;

public class TestMain {
    public static void main(String[] args){
        ExampleEnum exampleEnum =ExampleEnum.HALLO;

        ExampleEnum exampleEnum1 = ExampleEnum.valueOf("by");
        System.out.println(exampleEnum1);
        Server.serverLogger.info(exampleEnum1);
    }
}
