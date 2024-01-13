package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.sql.SQLException;

public class GetAllRecipesCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 0;

    private final RestaurantAPI restaurant;

    public GetAllRecipesCommand(RestaurantAPI restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String execute() throws SQLException {
        validateForExactlyNoArgs(NUMBER_OF_COMMAND_ARGS);

        return restaurant.getAllRecipes();
    }
}
