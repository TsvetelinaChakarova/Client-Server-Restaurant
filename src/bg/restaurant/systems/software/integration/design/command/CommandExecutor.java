package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.handlers.drink.DrinkCommandExecutor;
import bg.restaurant.systems.software.integration.design.command.handlers.recipe.RecipeCommandExecutor;
import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

import java.nio.channels.SocketChannel;

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
            //case FILE -> new LoginCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
            //case ALLERGENS -> new CreateNewGroupCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
            //case PREPARATION_TIME -> new AddBookmarkCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
            //case SERVE_WAY -> new RemoveBookmarkCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
            default -> "Unknown command";
        };
    }
}