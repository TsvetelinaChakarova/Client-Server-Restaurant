package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;

public class GetAllRecipesByAllergensCommand extends CommandsValidator implements CommandHandler {
    private final String[] args;

    public GetAllRecipesByAllergensCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        validateArgumentsLength(args);

        return args[0];
    }
}
