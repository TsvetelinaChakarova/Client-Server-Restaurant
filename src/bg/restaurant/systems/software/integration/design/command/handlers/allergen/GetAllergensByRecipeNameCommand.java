package bg.restaurant.systems.software.integration.design.command.handlers.allergen;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.sql.SQLException;

public class GetAllergensByRecipeNameCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final RestaurantAPI restaurant;
    private final String[] args;

    public GetAllergensByRecipeNameCommand(RestaurantAPI restaurant, String[] args) {
        this.restaurant = restaurant;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateForSufficientNumberOfArguments(NUMBER_OF_COMMAND_ARGS, args);

        String commandExtension = args[0];
        if (!areCommandTypeEqual(commandExtension, CommandType.RECIPE_NAME)) {
            return "Unknown Command";
        }

        String recipeName = args[1];
        return restaurant.getAllergensByRecipeName(recipeName);
    }
}
