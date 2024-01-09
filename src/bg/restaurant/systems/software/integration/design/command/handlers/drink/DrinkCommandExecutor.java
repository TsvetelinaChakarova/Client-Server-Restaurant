package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

import java.util.Arrays;

public class DrinkCommandExecutor extends CommandExecutorValidator {
    private final Restaurant restaurant;

    public DrinkCommandExecutor(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String execute(String... arguments) {
        validateArgumentsLength(arguments);
        CommandType commandType = CommandType.getCommandType(arguments[0]);

        return switch (commandType) {
            case ALL -> new GetAllDrinksCommand(restaurant).execute();
            case RECIPE_NAME ->
                new GetAllDrinksByRecipeName(restaurant,
                    Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            default -> "Unknown command";
        };
    }
}
