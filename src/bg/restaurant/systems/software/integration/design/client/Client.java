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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 16384;
    private static final ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
    private static final int FILE_COMMAND_ARGUMENTS_COUNT = 6;
    private static final int PATH_ARGUMENT_INDEX = 4;
    private static final int INPUT_PATH_INDEX = 5;
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SPACE_REGEX = " ";

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

    private static void createFile(String reply, List<String> messageParts) throws IOException {
        if (messageParts != null) {
            if (messageParts.size() == FILE_COMMAND_ARGUMENTS_COUNT &&
                messageParts.get(PATH_ARGUMENT_INDEX).equals("--path") &&
                !messageParts.get(INPUT_PATH_INDEX).isEmpty()) {

                String pathFromInput = messageParts.get(INPUT_PATH_INDEX).replaceAll("\"", "");
                Path filePath = Path.of(pathFromInput);
                File file = new File(pathFromInput);

                if (file.createNewFile()) {
                    System.out.println("The file created at " + pathFromInput + "!");
                } else {
                    System.out.println("The file was overwritten!");
                }

                if (Files.size(filePath) != 0) {
                    Files.writeString(filePath, System.lineSeparator(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                }
                Files.writeString(filePath, reply, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                System.out.println("""
                    Cannot create file because command is incorrect!
                    The command looks like this: get file --recipe_name "..." --path "..."
                    """);
            }
        }
    }

    private static List<String> getCommandArguments(String input) {
        List<String> tokens = new LinkedList<>();
        return getStrings(input, tokens);
    }

    public static List<String> getStrings(String input, List<String> tokens) {
        StringBuilder s = new StringBuilder();

        boolean insideQuote = false;

        for (char c : input.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote;
            }
            if (c == ' ' && !insideQuote) {
                tokens.add(s.toString().replace("\"", ""));
                s.delete(0, s.length());
            } else {
                s.append(c);
            }
        }
        tokens.add(s.toString().replace("\"", ""));

        return tokens;
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

                List<String> messageParts = null;
                if (message.contains("get file --recipe_name")) {
                    messageParts = getStrings(message, new LinkedList<>());

                    message = "get recipe --recipe_name " + '"' + messageParts.get(3) + '"';
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

                if (messageParts == null) {
                    System.out.println("The restaurant's response is:\n" + reply + "\n");
                }
                createFile(reply, messageParts);
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}