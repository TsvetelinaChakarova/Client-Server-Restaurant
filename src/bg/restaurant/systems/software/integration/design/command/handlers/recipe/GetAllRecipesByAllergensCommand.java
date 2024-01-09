package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetAllRecipesByAllergensCommand extends CommandsValidator implements CommandHandler {
//    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final CocktailStorage cocktailStorage;
    private final String[] args;

    public GetAllRecipesByAllergensCommand(CocktailStorage cocktailStorage, String[] args) {
        this.cocktailStorage = cocktailStorage;
        this.args = args;
    }

    @Override
    public String execute() {
        try {
            validateArgumentsLength(args);

            return args[0];
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
