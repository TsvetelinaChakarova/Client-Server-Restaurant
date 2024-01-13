package bg.restaurant.systems.software.integration.design.client;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Client {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 16384;
    private static final ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);

    private static void printRestaurantInfo() {
        System.out.println("""

            Welcome to the restaurant BECI!
            Please note that for each command quotes(" ") are necessary to execute properly!
            Here is the menu:""");
        System.out.println("------------------------------------------MENU------------------------------------------");
        System.out.println("Recipes");
        System.out.println("Get all recipes -> get recipes --all");
        System.out.println("Get recipe by name -> get recipe --recipe_name \"...\"");
        System.out.println("Get all recipes by recipe type -> get recipes --type [\"breakfast\", \"lunch\", ...]");
        System.out.println(
            "Get all recipes with certain ingredient -> get recipes --ingredients [\"salt\", \"pepper\", ...]");
        System.out.println(
            "Get all recipes with certain allergen -> get recipes --allergens [\"milk\", \"gluten\", ...]\n");
        System.out.println("Drinks");
        System.out.println("Get all drinks -> get drinks --all");
        System.out.println("Get suitable drinks for recipe -> get drinks --recipe_name \"...\"\n");
        System.out.println("Files");
        System.out.println("Get file with certain recipe -> get file --recipe_name \"...\" --path \"...\"\n");
        System.out.println("Allergens");
        System.out.println("Get allergens for certain recipe -> get allergens --recipe_name \"...\"\n");
        System.out.println("Ingredients");
        System.out.println("Get all ingredients for recipe -> get ingredients --recipe_name \"...\"\n");
        System.out.println("Preparation");
        System.out.println("Get preparation time for certain recipe -> get prep_time --recipe_name \"...\"\n");
        System.out.println("Serve Way");
        System.out.println("Get how recipe is served -> get serve_way --recipe_name \"...\"");
        System.out.println(
            "----------------------------------------------------------------------------------------\n");
        System.out.println("To disconnect from the restaurant please enter (disconnect).");
        System.out.println("Please enjoy your meal!\n");
    }

    private static void createFile(String reply, String[] messageParts) throws IOException {
        if (messageParts != null) {
            if (messageParts[4].equals("--path")) {
                String pathFromInput = messageParts[5].replaceAll("\"", "");
                Path filePath = Path.of(pathFromInput);
                File file = new File(pathFromInput);

                if (file.createNewFile()) {
                    System.out.println("The file created at " + pathFromInput + ".");
                } else {
                    System.out.println("The file already exists.");
                }

                if (Files.size(filePath) != 0) {
                    Files.writeString(filePath, System.lineSeparator(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                }
                Files.writeString(filePath, reply, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }

    public static void main(String[] args) {

        try (SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

            System.out.println("Connected to the server.");
            printRestaurantInfo();

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                if ("disconnect".equals(message)) {
                    break;
                }

                String[] messageParts = null;
                if (message.contains("get file --recipe_name")) {
                    messageParts = message.split(" ");

                    message = "get recipe --recipe_name " + messageParts[3];
                }
                //System.out.println("Sending message {" + message + "} to the server...");

                buffer.clear();
                buffer.put(message.getBytes());
                buffer.flip();
                socketChannel.write(buffer);

                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();

                byte[] byteArray = new byte[buffer.remaining()];
                buffer.get(byteArray);
                String reply = new String(byteArray, StandardCharsets.UTF_8);

                //String reply = new String(buffer.array(), 0, buffer.position(), "UTF-8"); // buffer drain

                System.out.println("The following items are available:\n" + reply + "\n");
                createFile(reply, messageParts);
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}