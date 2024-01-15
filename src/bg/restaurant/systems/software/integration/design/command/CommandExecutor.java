package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.handlers.allergen.GetAllergensByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.drink.DrinkCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.ingredient.GetIngredientsByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.preparation.GetPreparationTimeForRecipeByNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.recipe.GetRecipeByNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.recipe.RecipeCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.serving.GetServingWayByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.restaurant.systems.software.integration.design.logger.ErrorLogger;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;

import java.io.IOException;

public class CommandExecutor extends CommandExecutorValidator {
    private final RestaurantAPI restaurant;
    private final ErrorLogger errorLogger;

    public CommandExecutor(RestaurantAPI restaurant, ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
        validateConstructorParameter(restaurant);

        this.restaurant = restaurant;
    }

    public String execute(Command cmd) {
        try {
            validateCommand(cmd);

            CommandType commandType = CommandType.getCommandType(cmd.command());

            return switch (commandType) {
                case RECIPES -> new RecipeCommandExecutor(restaurant).execute(cmd.arguments());
                case RECIPE -> new GetRecipeByNameCommand(restaurant, cmd.arguments()).execute();
                case DRINKS -> new DrinkCommandExecutor(restaurant).execute(cmd.arguments());
                case INGREDIENTS -> new GetIngredientsByRecipeNameCommand(restaurant, cmd.arguments()).execute();
                case ALLERGENS -> new GetAllergensByRecipeNameCommand(restaurant, cmd.arguments()).execute();
                case PREPARATION_TIME ->
                    new GetPreparationTimeForRecipeByNameCommand(restaurant, cmd.arguments()).execute();
                case SERVE_WAY -> new GetServingWayByRecipeNameCommand(restaurant, cmd.arguments()).execute();
                default -> "Unknown Command";
            };
        } catch (Exception e) {
            try {
                errorLogger.appendLogs(e);
            } catch (IOException ex) {
                return "There was an error in the application, please try again!";
            }
            // logs e.getMessage();
            return "There was an error in the application, please try again!";
        }
    }
}