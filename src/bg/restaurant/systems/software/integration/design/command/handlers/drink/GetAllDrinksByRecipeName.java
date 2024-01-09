package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetAllDrinksByRecipeName extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final CocktailStorage cocktailStorage;
    private final String[] args;

    public GetAllDrinksByRecipeName(CocktailStorage cocktailStorage, String[] args) {
        this.cocktailStorage = cocktailStorage;
        this.args = args;
    }

    @Override
    public String execute() {
        try {
            validateArgumentsLength(args);

            return args[0];
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
