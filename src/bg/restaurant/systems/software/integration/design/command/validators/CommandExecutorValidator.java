package bg.restaurant.systems.software.integration.design.command.validators;

import bg.restaurant.systems.software.integration.design.command.Command;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

public class CommandExecutorValidator {
    protected void validateArgumentsLength(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("There are not enough arguments to fulfil the request!");
        }
    }

    protected void validateConstructorParameter(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("The restaurant cannot be null!");
        }
    }

    protected void validateCommand(Command cmd) {
        if (cmd == null) {
            throw new IllegalArgumentException("The command cannot be null!");
        }
    }
}
