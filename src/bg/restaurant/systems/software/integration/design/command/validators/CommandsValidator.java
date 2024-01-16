package bg.restaurant.systems.software.integration.design.command.validators;

import bg.restaurant.systems.software.integration.design.command.CommandType;

public abstract class CommandsValidator {
    private static final int MUST_HAVE_ONE_ARG = 1;

    protected void validateArgumentsLength(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The number of arguments must be at least one!");
        }
    }

    protected boolean areCommandTypeEqual(String currentType, CommandType neededType) {
        return CommandType.getCommandType(currentType).equals(neededType);
    }

    protected void validateForSufficientNumberOfArguments(int argsNumber, String[] args) {
        if (args.length != argsNumber) {
            throw new IllegalArgumentException("The number of arguments for this command must be " + argsNumber);
        }
    }
}
