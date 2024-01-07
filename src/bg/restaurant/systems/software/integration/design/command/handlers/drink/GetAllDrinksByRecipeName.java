package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.command.handlers.CommandHandler;

public class GetAllDrinksByRecipeName implements CommandHandler {
    private final String[] args;

    public GetAllDrinksByRecipeName(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        return args[0];
    }
}
