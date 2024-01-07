package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

import java.util.Arrays;

public class RecipeCommandExecutor extends CommandExecutorValidator {
    private final CocktailStorage cocktailStorage;

    public RecipeCommandExecutor(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    public String execute(String... arguments) {
        validateArgumentsLength(arguments);
        CommandType commandType = CommandType.getCommandType(arguments[0]);

        return switch (commandType) {
            case ALL -> new GetAllRecipesCommand(cocktailStorage).execute();
            case TYPE ->
                new GetAllRecipesByTypeCommand(Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            case INGREDIENTS ->
                new GetAllRecipesByIngredientsCommand(Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            case LIST_ALLERGENS ->
                new GetAllRecipesByAllergensCommand(Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            default -> "Unknown command";
        };
    }
}
