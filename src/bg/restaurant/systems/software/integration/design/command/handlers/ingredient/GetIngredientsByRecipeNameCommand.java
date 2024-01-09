package bg.restaurant.systems.software.integration.design.command.handlers.ingredient;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetIngredientsByRecipeNameCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final CocktailStorage cocktailStorage;
    private final String[] args;

    public GetIngredientsByRecipeNameCommand(CocktailStorage cocktailStorage, String[] args) {
        this.cocktailStorage = cocktailStorage;
        this.args = args;
    }

    @Override
    public String execute() {
        try {
            validateForSufficientNumberOfArguments(NUMBER_OF_COMMAND_ARGS, args);
            if (!areCommandTypeEqual(args[0], CommandType.RECIPE_NAME)) {
                return "Unknown Command";
            }

            return args[1] + " | Ingredients";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
