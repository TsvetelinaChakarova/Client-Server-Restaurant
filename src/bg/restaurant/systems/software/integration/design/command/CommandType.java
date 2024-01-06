package bg.restaurant.systems.software.integration.design.command;

import java.util.Arrays;

public enum CommandType {
    REGISTER("register"),
    LOGIN("login"),
    LOGOUT("logout"),
    NEW_GROUP("new-group"),
    ADD_TO("add-to"),
    SHORTEN("--shorten"),
    REMOVE_FROM("remove-from"),
    LIST("list"),
    GROUP_NAME("--group-name"),
    SEARCH("search"),
    TAGS("--tags"),
    TITLE("--title"),
    CLEANUP("cleanup"),
    IMPORT_FROM_CHROME("import-from-chrome"),
    GET_CHROME_BOOKMARKS("get-chrome-bookmarks"),
    UNKNOWN("unknown");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandTypeString() {
        return commandType;
    }

    public static CommandType getCommandType(String commandType) {
        return Arrays.stream(values())
            .filter(c -> c.commandType.equalsIgnoreCase(commandType))
            .findFirst()
            .orElse(UNKNOWN);
    }
}