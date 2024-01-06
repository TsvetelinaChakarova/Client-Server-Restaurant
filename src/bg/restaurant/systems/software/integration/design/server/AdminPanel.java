package bg.restaurant.systems.software.integration.design.server;

import bg.restaurant.systems.software.integration.design.command.CommandExecutor;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;
import bg.restaurant.systems.software.integration.design.storage.DefaultCocktailStorage;

import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) throws InterruptedException {
        final int serverPortNumber = 7777;

        //ErrorLogs errorLogs = new ErrorLogs("src/bg/sofia/uni/fmi/mjt/bookmarks/repository/logs/logs.txt");

        CocktailStorage cocktailStorage = new DefaultCocktailStorage();

        Server server = new Server(serverPortNumber, new CommandExecutor(cocktailStorage));

        Scanner adminInput = new Scanner(System.in);
        String adminCommand;

        System.out.print("Please input command: ");
        while (true) {
            adminCommand = adminInput.nextLine();

            if (adminCommand.equals("start")) {
                System.out.println("Starting server");
                System.out.println("Listening for client requests");
                server.start();
            }
            if (adminCommand.equals("stop")) {
                if (server.isAlive()) {
                    System.out.println("Stopping server...");
                    server.shutdown();
                }
                break;
            }
        }

        adminInput.close();
        server.join();
    }
}