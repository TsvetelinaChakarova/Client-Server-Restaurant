package bg.restaurant.systems.software.integration.design.command;

import java.util.ArrayList;
import java.util.List;

public class CommandCreator extends CommandsCreatorValidator {
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SPACE_REGEX = " ";

    private static List<String> getCommandArguments(String input) {
        input = input.replaceAll(WHITESPACE_REGEX, SPACE_REGEX).strip();

        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean insideQuote = false;

        for (char c : input.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote;
            }
            if (c == ' ' && !insideQuote) {
                tokens.add(sb.toString().replace("\"", ""));
                sb.delete(0, sb.length());
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().replace("\"", ""));

        return tokens;
    }

    public static Command newCommand(String clientInput) {
        clientInput = validateClientInput(clientInput);

        List<String> tokens = CommandCreator.getCommandArguments(clientInput);
        String[] args = tokens.subList(1, tokens.size()).toArray(new String[0]);

        return new Command(tokens.get(0), args);
    }
}
