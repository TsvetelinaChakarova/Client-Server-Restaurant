package bg.sofia.uni.fmi.mjt.cocktail.server.command;

import java.util.ArrayList;
import java.util.List;

public class CommandCreator extends CommandsCreatorValidator {
    private static List<String> getCommandArguments(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean insideQuote = false;

        for (char c : input.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote;
            }
            if (c == ' ' && !insideQuote) { //when space is not inside quote split
                tokens.add(sb.toString().replace("\"", "")); //token is ready, lets add it to list
                sb.delete(0, sb.length()); //and reset StringBuilder`s content
            } else {
                sb.append(c); //else add character to token
            }
        }
        //let's not forget about last token that doesn't have space after it
        tokens.add(sb.toString().replace("\"", ""));

        return tokens;
    }

    public static Command newCommand(String clientInput) {
        validateClientInput(clientInput);

        List<String> tokens = CommandCreator.getCommandArguments(clientInput);
        String[] args = tokens.subList(1, tokens.size()).toArray(new String[0]);

        return new Command(tokens.get(0), args);
    }
}