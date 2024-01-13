package bg.restaurant.systems.software.integration.design.command.validators;

public abstract class CommandsCreatorValidator {
    private static final String UNKNOWN_COMMAND = "Unknown";
    private static final String GET_COMMAND = "get";

    protected static String validateClientInput(String clientInput) {
        if (clientInput == null || clientInput.isEmpty() || clientInput.isBlank()) {
            return UNKNOWN_COMMAND;
        }
        return clientInput;
    }

    protected static String validateGet(String token) {
        if (!token.equals(GET_COMMAND)) {
            return UNKNOWN_COMMAND;
        }

        return token;
    }
}