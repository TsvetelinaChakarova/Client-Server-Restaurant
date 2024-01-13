package bg.restaurant.systems.software.integration.design.data;

import java.util.Arrays;

public enum RecipeType {
    SOUP("soup"),
    MAIN_COURSE("main-course"),
    DESERT("dessert"),
    BREAKFAST("breakfast"),
    UNKNOWN("unknown");

    private final String recipeType;

    RecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getRecipeTypeString() {
        return recipeType;
    }

    public static RecipeType getRecipeType(String recipeType) {
        return Arrays.stream(values())
            .filter(c -> c.recipeType.equalsIgnoreCase(recipeType))
            .findFirst()
            .orElse(UNKNOWN);
    }
}