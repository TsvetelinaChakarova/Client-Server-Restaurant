package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetAllRecipesCommand implements CommandHandler {
    private final CocktailStorage cocktailStorage;

    public GetAllRecipesCommand(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    @Override
    public String execute() {
        return "Recipes";
    }
}
