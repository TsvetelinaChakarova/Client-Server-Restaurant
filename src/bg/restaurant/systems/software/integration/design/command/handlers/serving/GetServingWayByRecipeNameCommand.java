package bg.restaurant.systems.software.integration.design.command.handlers.serving;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

public class GetServingWayByRecipeNameCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final Restaurant restaurant;
    private final String[] args;

    public GetServingWayByRecipeNameCommand(Restaurant restaurant, String[] args) {
        this.restaurant = restaurant;
        this.args = args;
    }

    @Override
    public String execute() {
        try {
            validateForSufficientNumberOfArguments(NUMBER_OF_COMMAND_ARGS, args);
            if (!areCommandTypeEqual(args[0], CommandType.RECIPE_NAME)) {
                return "Unknown Command";
            }

            return restaurant.getServingWayByRecipeName(args[1]).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
