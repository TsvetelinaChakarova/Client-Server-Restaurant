package bg.restaurant.systems.software.integration.design.command.handlers.preparation;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetPreparationTimeForRecipeByNameCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final CocktailStorage cocktailStorage;
    private final String[] args;

    public GetPreparationTimeForRecipeByNameCommand(CocktailStorage cocktailStorage, String[] args) {
        this.cocktailStorage = cocktailStorage;
        this.args = args;
    }

    @Override
    public String execute() {
        validateForSufficientNumberOfArguments(NUMBER_OF_COMMAND_ARGS, args);
        validateThatContainsOneArgument(args);
        validateCommandType(args[0], CommandType.RECIPE_NAME);

        return args[1] + " | PREPARATION_TIME";
    }
}
