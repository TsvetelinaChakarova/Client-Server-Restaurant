package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;

public class GetAllRecipesByIngredientsCommand extends CommandsValidator implements CommandHandler {
    private final String[] args;

    public GetAllRecipesByIngredientsCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        validateArgumentsLength(args);

        return args[0];
    }
}
