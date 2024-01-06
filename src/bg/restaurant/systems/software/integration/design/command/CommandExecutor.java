package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.storage.CocktailStorage;

import java.nio.channels.SocketChannel;

public class CommandExecutor {
    private final CocktailStorage cocktailStorage;

    public CommandExecutor(CocktailStorage cocktailStorage) {
        this.cocktailStorage = cocktailStorage;
    }

    public String execute(Command cmd, SocketChannel clientChannel) {
        CommandType commandType = CommandType.getCommandType(cmd.command());

//        return switch (commandType) {
//            case REGISTER -> new RegisterCommand(cmd.arguments(), cocktailStorage).execute();
//            case LOGIN -> new LoginCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case LOGOUT -> new LogoutCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case NEW_GROUP -> new CreateNewGroupCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case ADD_TO -> new AddBookmarkCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case REMOVE_FROM -> new RemoveBookmarkCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case LIST -> new ListBookmarksCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case SEARCH -> new SearchBookmarksCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case CLEANUP -> new CleanupCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case IMPORT_FROM_CHROME ->
//                new ImportFromChromeCommand(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            case GET_CHROME_BOOKMARKS ->
//                new GetChromeBookmarks(cmd.arguments(), clientChannel, cocktailStorage).execute();
//            default -> "Unknown command";
//        };
        return null;
    }
}