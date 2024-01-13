package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

public class GetAllRecipesCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 0;

    private final Restaurant restaurant;

    public GetAllRecipesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String execute() {
        try {
            validateForExactlyNoArgs(NUMBER_OF_COMMAND_ARGS);

            return "Recipes";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
