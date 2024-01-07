package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;

public class GetAllRecipesByIngredientsCommand implements CommandHandler {
    private final String[] args;

    public GetAllRecipesByIngredientsCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        return args[0];
    }
}
