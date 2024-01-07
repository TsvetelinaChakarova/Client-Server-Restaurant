package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

import java.util.Arrays;

public class DrinkCommandExecutor extends CommandExecutorValidator {
    private final CocktailStorage cocktailStorage;

    public DrinkCommandExecutor(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    public String execute(String... arguments) {
        validateArgumentsLength(arguments);
        CommandType commandType = CommandType.getCommandType(arguments[0]);

        return switch (commandType) {
            case ALL -> new GetAllDrinksCommand(cocktailStorage).execute();
            case RECIPE_NAME ->
                new GetAllDrinksByRecipeName(Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            default -> "Unknown command";
        };
    }
}
