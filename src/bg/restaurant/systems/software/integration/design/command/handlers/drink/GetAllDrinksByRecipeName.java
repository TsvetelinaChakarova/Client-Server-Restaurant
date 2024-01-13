package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.sql.SQLException;

public class GetAllDrinksByRecipeName extends CommandsValidator implements CommandHandler {
    private final RestaurantAPI restaurant;
    private final String[] args;

    public GetAllDrinksByRecipeName(RestaurantAPI restaurant, String[] args) {
        this.restaurant = restaurant;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateArgumentsLength(args);

        String recipeName = args[0];
        return restaurant.getAllDrinksByRecipeName(recipeName);
    }
}
