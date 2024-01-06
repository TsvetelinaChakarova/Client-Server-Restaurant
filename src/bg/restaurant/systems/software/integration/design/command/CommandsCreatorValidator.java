package bg.restaurant.systems.software.integration.design.command;

public class CommandsCreatorValidator {
    static String validateClientInput(String clientInput) {
        if (clientInput == null || clientInput.isEmpty() || clientInput.isBlank()) {
            return "Unknown";
            //throw new IllegalArgumentException("The client input cannot be null, empty or blank!");
        }
        return clientInput;
    }
}