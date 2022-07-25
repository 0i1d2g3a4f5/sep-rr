package client_package.sentient;

import java.util.Scanner;

public class SentientApplication {
    public void start(){
        SentientController sentientController = new SentientController();
        boolean manual = this.getInfo();
        if(manual){
            sentientController.start("DD AI", "DesperateDrosseln");
        }
        else{
            sentientController.start("DD AI", "localhost" , 1234, "DesperateDrosseln");
            //sentientController.start("DisabledDegenerate", "sep21.dbs.ifi.lmu.de" , 52020, "DesperateDrosseln");

        }
    }
    public boolean getInfo(){
        System.out.println("Type m for manual and a for auto.");
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        if(a.equals("a")) {
            return false;
        }
        else if(a.equals("m")){
            return true;
        }
        else{
            System.out.println("Invalid input. Please type a valid answer.");
            return getInfo();
        }
    }
}
