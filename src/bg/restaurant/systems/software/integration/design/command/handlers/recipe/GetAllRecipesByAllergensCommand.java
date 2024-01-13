package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;
import bg.restaurant.systems.software.integration.design.command.validators.CommandsValidator;
import bg.restaurant.systems.software.integration.design.storage.Restaurant;

import java.util.Collection;
import java.util.List;

public class GetAllRecipesByAllergensCommand extends CommandsValidator implements CommandHandler {
    private final Restaurant restaurant;
    private final String[] args;

    public GetAllRecipesByAllergensCommand(Restaurant restaurant, String[] args) {
        this.restaurant = restaurant;
        this.args = args;
    }

    @Override
    public String execute() {
        try {
            validateArgumentsLength(args);

            return restaurant.getAllRecipesByAllergens(List.of(args)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
