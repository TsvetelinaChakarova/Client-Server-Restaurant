package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.sql.SQLException;
import java.util.List;

public class GetAllRecipesByIngredientsCommand extends CommandsValidator implements CommandHandler {
    private final RestaurantAPI restaurant;
    private final String[] args;

    public GetAllRecipesByIngredientsCommand(RestaurantAPI restaurant, String[] args) {
        this.restaurant = restaurant;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateArgumentsLength(args);

        return restaurant.getAllRecipesByIngredients(List.of(args));
    }
}
