package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

public class GetAllRecipesByIngredientsCommand extends CommandsValidator implements CommandHandler {
//    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final Restaurant restaurant;
    private final String[] args;

    public GetAllRecipesByIngredientsCommand(Restaurant restaurant, String[] args) {
        this.restaurant = restaurant;
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
