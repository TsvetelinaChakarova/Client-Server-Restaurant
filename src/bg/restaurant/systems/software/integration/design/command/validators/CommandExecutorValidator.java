package bg.restaurant.systems.software.integration.design.command.validators;

public class CommandExecutorValidator {
    protected void validateArgumentsLength(String ... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("There are not enough arguments to fulfil the request!");
        }
    }


}
