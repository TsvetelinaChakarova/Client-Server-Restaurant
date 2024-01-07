package bg.restaurant.systems.software.integration.design.command;

import java.util.Arrays;

public enum CommandType {
    GET("get"),
    RECIPES("recipes"),
    ALL("--all"),
    TYPE("--type"),
    INGREDIENTS("--ingredients"),
    FILE("file"),
    RECIPE_NAME("--recipe_name"),
    DRINKS("drinks"),
    ALLERGENS("allergens"),
    LIST_ALLERGENS("--allergens"),
    PREPARATION_TIME("prep_time"),
    SERVE_WAY("serve_way"),
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