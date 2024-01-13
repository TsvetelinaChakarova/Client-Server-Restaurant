package bg.restaurant.systems.software.integration.design.command;

import java.util.Arrays;

public enum CommandType {
    GET("get"),
    RECIPES("recipes"),
    RECIPE("recipe"),
    DRINKS("drinks"),
    INGREDIENTS("ingredients"),
    ALLERGENS("allergens"),
    PREPARATION_TIME("prep_time"),
    SERVE_WAY("serve_way"),
    FILE("file"),
    ALL("--all"),
    TYPE("--type"),
    LIST_INGREDIENTS("--ingredients"),
    RECIPE_NAME("--recipe_name"),
    LIST_ALLERGENS("--allergens"),
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