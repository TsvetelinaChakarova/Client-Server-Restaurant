package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.handlers.allergen.GetAllergensByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.drink.DrinkCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.file.GetFileWithRecipeCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.ingredient.GetIngredientsByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.preparation.GetPreparationTimeForRecipeByNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.recipe.RecipeCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.serving.GetServingWayByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

public class CommandExecutor extends CommandExecutorValidator {
    private final Restaurant restaurant;

    public CommandExecutor(Restaurant restaurant) {
        validateConstructorParameter(restaurant);

        this.restaurant = restaurant;
    }

    public String execute(Command cmd) {
        try {
            validateCommand(cmd);

            CommandType commandType = CommandType.getCommandType(cmd.command());

            return switch (commandType) {
                case RECIPES -> new RecipeCommandExecutor(restaurant).execute(cmd.arguments());
                case DRINKS -> new DrinkCommandExecutor(restaurant).execute(cmd.arguments());
                case FILE -> new GetFileWithRecipeCommand(restaurant, cmd.arguments()).execute();
                case INGREDIENTS -> new GetIngredientsByRecipeNameCommand(restaurant, cmd.arguments()).execute();
                case ALLERGENS -> new GetAllergensByRecipeNameCommand(restaurant, cmd.arguments()).execute();
                case PREPARATION_TIME ->
                    new GetPreparationTimeForRecipeByNameCommand(restaurant, cmd.arguments()).execute();
                case SERVE_WAY -> new GetServingWayByRecipeNameCommand(restaurant, cmd.arguments()).execute();
                default -> "Unknown command";
            };
        } catch (Exception e) {
            // logs e.getMessage();
            return "There was an error in the application, please try again!";
        }
    }
}