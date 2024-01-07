package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;

public class GetAllRecipesByTypeCommand implements CommandHandler {
    private final String[] args;

    public GetAllRecipesByTypeCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        return args[0];
    }
}
