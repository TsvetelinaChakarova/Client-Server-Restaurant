package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.handlers.allergen.GetAllergensByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.drink.DrinkCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.file.GetFileWithRecipeCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.preparation.GetPreparationTimeForRecipeByNameCommand;
import bg.restaurant.systems.software.integration.design.command.handlers.recipe.RecipeCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.serving.GetServingWayByRecipeNameCommand;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class CommandExecutor {
    private final CocktailStorage cocktailStorage;

    public CommandExecutor(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    public String execute(Command cmd) {
        CommandType commandType = CommandType.getCommandType(cmd.command());

        return switch (commandType) {
            case RECIPES -> new RecipeCommandExecutor(cocktailStorage).execute(cmd.arguments());
            case DRINKS -> new DrinkCommandExecutor(cocktailStorage).execute(cmd.arguments());
            case FILE -> new GetFileWithRecipeCommand(cocktailStorage, cmd.arguments()).execute();
            case ALLERGENS -> new GetAllergensByRecipeNameCommand(cocktailStorage, cmd.arguments()).execute();
            case PREPARATION_TIME ->
                new GetPreparationTimeForRecipeByNameCommand(cocktailStorage, cmd.arguments()).execute();
            case SERVE_WAY -> new GetServingWayByRecipeNameCommand(cocktailStorage, cmd.arguments()).execute();
            default -> "Unknown command";
        };
    }
}