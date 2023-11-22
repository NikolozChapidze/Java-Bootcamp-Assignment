package dev.omedia.abstractFactory;

import java.util.Scanner;

public class Demo {
    private static Application config() {
        Application application;
        F1Factory f1Factory;

        Scanner scanner = new Scanner(System.in);
        String f1Team = scanner.nextLine();

        if(f1Team.equals("Mercedes")){
            f1Factory = new MercedesFactory();
        }else{
            f1Factory = new RedBullFactory();
        }
        application = new Application(f1Factory);

        return application;
    }

    public static void main(String[] args) {
        Application application = config();
        application.win();
    }


}
