package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetAllRecipesCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 0;

    private final CocktailStorage cocktailStorage;

    public GetAllRecipesCommand(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    @Override
    public String execute() {
        try {
            validateForSufficientNumberOfArguments(0);

            return "Recipes";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
