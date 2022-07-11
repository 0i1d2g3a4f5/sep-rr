package client_package.AI;

import java.util.ArrayList;
import java.util.Scanner;

public class AIMain {
    public static final String IP = "sep21.dbs.ifi.lmu.de";
    public static  final int PORT = 52021;
    static ArrayList<AIController> AIControllers= new ArrayList();

    public static void main(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number of AIs :");
        int numberOfAIS = scanner.nextInt();
        scanner.close();

        for (int i = 0; i < numberOfAIS; i++) {
            AIController aiController = new AIController("Version 1.0","DesperateDrosseln", i);
            aiController.setDaemon(true);
            AIControllers.add(aiController);
            aiController.start();
        }

    }


}
