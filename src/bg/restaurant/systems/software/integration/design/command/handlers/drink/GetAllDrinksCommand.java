package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

public class GetAllDrinksCommand implements CommandHandler {
    private final CocktailStorage cocktailStorage;

    public GetAllDrinksCommand(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    @Override
    public String execute() {
        return "Drinks";
    }
}
