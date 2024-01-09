package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;

public class GetAllRecipesByTypeCommand extends CommandsValidator implements CommandHandler {
    private final String[] args;

    public GetAllRecipesByTypeCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        validateArgumentsLength(args);

        return args[0];
    }
}
