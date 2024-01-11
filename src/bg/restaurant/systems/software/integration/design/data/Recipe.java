package bg.restaurant.systems.software.integration.design.data;

import java.util.Set;

public record Recipe(String name, RecipeType type, ServeStyle serveStyle,
                     Set<Ingredient> ingredients, int preparationTime,
                     Set<Allergen> allergens, Set<Drink> suitableDrinks) {

    public Recipe {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }

        if (type == null) {
            throw new IllegalArgumentException("The type cannot be null!");
        }

        if (serveStyle == null ) {
            throw new IllegalArgumentException("The serveStyle cannot be null!");
        }

        if (ingredients == null || ingredients.isEmpty() ) {
            throw new IllegalArgumentException("The ingredients cannot be null or empty!");
        }

        if (preparationTime <= 0) {
            throw new IllegalArgumentException("The preparationTime cannot be zero or less than zero!");
        }
    }
}
