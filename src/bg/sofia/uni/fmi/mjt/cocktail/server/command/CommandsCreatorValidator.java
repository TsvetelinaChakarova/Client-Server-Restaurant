package bg.sofia.uni.fmi.mjt.cocktail.server.command;

public class CommandsCreatorValidator {
    static void validateClientInput(String clientInput) {
        if (clientInput == null || clientInput.isEmpty() || clientInput.isBlank()) {
            throw new IllegalArgumentException("The client input cannot br null, empty or blank!");
        }
    }
}