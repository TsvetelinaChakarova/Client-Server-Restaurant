package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.validators.CommandsCreatorValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bg.restaurant.systems.software.integration.design.client.Client.getStrings;

public class CommandCreator extends CommandsCreatorValidator {
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SPACE_REGEX = " ";
    private static final String UNKNOWN_COMMAND = "Unknown";

    private static List<String> getCommandArguments(String input) {
        input = input.replaceAll(WHITESPACE_REGEX, SPACE_REGEX).strip();

        List<String> tokens = new ArrayList<>();
        return getStrings(input, tokens);
    }

    public static Command newCommand(String clientInput) {
        clientInput = validateClientInput(clientInput);

        clientInput = clientInput
            .replaceAll("\\[", "")
            .replaceAll("]", "")
            .replaceAll(",", "");

        List<String> tokens = CommandCreator.getCommandArguments(clientInput);
        String[] args = tokens.subList(1, tokens.size()).toArray(new String[0]);

        if (validateGet(tokens.get(0)).equals(UNKNOWN_COMMAND)) {
            return new Command("Unknown", new String[] {"Command"});
        }

        return new Command(args[0], Arrays.copyOfRange(args, 1, args.length));
    }
}
