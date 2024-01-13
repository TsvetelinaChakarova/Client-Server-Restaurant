package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

public class GetAllDrinksCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 0;
    private final Restaurant restaurant;

    public GetAllDrinksCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String execute() {
        try {
            validateForExactlyNoArgs(NUMBER_OF_COMMAND_ARGS);

            return restaurant.getAllDrinks();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
