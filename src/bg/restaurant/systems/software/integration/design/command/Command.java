package bg.restaurant.systems.software.integration.design.command;

public record Command(String command, String[] arguments) {
    public Command {
        if (command == null || command.isEmpty() || command.isBlank()) {
            throw new IllegalArgumentException("The command cannot be null, empty or blank!");
        }

        if (arguments == null) {
            throw new IllegalArgumentException("The arguments cannot be null!");
        }

        for (String argument : arguments) {
            if (argument == null || argument.isEmpty() || argument.isBlank()) {
                throw new IllegalArgumentException("None of the arguments can be null!");
            }
        }
    }

    public static Command of(String command, String[] arguments) {
        return new Command(command, arguments);
    }
}