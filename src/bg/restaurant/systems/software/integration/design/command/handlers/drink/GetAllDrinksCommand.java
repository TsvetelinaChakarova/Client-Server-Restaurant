package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetAllDrinksCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 0;
    private final CocktailStorage cocktailStorage;

    public GetAllDrinksCommand(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    @Override
    public String execute() {
        try {
            validateForSufficientNumberOfArguments(NUMBER_OF_COMMAND_ARGS);

            return "Drinks";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
