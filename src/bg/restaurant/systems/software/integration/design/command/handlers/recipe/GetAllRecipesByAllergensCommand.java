package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;

public class GetAllRecipesByAllergensCommand implements CommandHandler {
    private final String[] args;

    public GetAllRecipesByAllergensCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        return args[0];
    }
}
