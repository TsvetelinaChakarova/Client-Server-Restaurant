package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.sql.SQLException;

public class GetAllDrinksCommand extends CommandsValidator implements CommandHandler {
    private final RestaurantAPI restaurant;

    public GetAllDrinksCommand(RestaurantAPI restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String execute() throws SQLException {
        return restaurant.getAllDrinks();
    }
}
