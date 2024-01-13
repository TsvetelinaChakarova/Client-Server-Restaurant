package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.sql.SQLException;
import java.util.Arrays;

public class RecipeCommandExecutor extends CommandExecutorValidator {
    private final RestaurantAPI restaurant;

    public RecipeCommandExecutor(RestaurantAPI restaurant) {
        this.restaurant = restaurant;
    }

    public String execute(String[] arguments) throws SQLException {
        validateArgumentsLength(arguments);
        CommandType commandType = CommandType.getCommandType(arguments[0]);

        return switch (commandType) {
            case ALL -> new GetAllRecipesCommand(restaurant).execute();
            case TYPE -> new GetAllRecipesByTypeCommand(restaurant,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            case LIST_INGREDIENTS -> new GetAllRecipesByIngredientsCommand(restaurant,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            case LIST_ALLERGENS -> new GetAllRecipesByAllergensCommand(restaurant,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            default -> "Unknown command";
        };
    }
}
