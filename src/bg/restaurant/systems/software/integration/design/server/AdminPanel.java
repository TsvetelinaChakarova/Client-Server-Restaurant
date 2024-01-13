package bg.restaurant.systems.software.integration.design.server;

import bg.restaurant.systems.software.integration.design.command.CommandExecutor;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;
import bg.restaurant.systems.software.integration.design.storage.DefaultRestaurant;

import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) throws InterruptedException {
        final int serverPortNumber = 7777;

        //ErrorLogs errorLogs = new ErrorLogs("src/bg/sofia/uni/fmi/mjt/bookmarks/repository/logs/logs.txt");

        Restaurant restaurant = new DefaultRestaurant();

        Server server = new Server(serverPortNumber, new CommandExecutor(restaurant));

        Scanner adminInput = new Scanner(System.in);
        String adminCommand;

        System.out.println("Hello admin, to start the server please enter (start) or to stop (stop) commands.");
        System.out.print("Please input command: ");
        while (true) {
            adminCommand = adminInput.nextLine();
            adminCommand = adminCommand.trim();

            if (adminCommand.equals("start")) {
                System.out.println("Starting server...");
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