package bg.restaurant.systems.software.integration.design.server;

import bg.restaurant.systems.software.integration.design.DatabaseConnection;
import bg.restaurant.systems.software.integration.design.Queries;
import bg.restaurant.systems.software.integration.design.command.CommandExecutor;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;
import bg.restaurant.systems.software.integration.design.storage.DefaultRestaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) throws InterruptedException {
        final int serverPortNumber = 7777;

        //ErrorLogs errorLogs = new ErrorLogs("src/bg/sofia/uni/fmi/mjt/bookmarks/repository/logs/logs.txt");

        DatabaseConnection databaseConnection = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/restaurants", "root", "");

        Queries queries = new Queries(databaseConnection);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Restaurant restaurant = new DefaultRestaurant(queries, gson);

        Server server = new Server(serverPortNumber, new CommandExecutor(restaurant));

        Scanner adminInput = new Scanner(System.in);
        String adminCommand;

        System.out.println("\nHello admin, to start the server please enter (start) or to stop (stop) commands.");
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