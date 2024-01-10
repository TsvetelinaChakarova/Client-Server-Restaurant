package bg.restaurant.systems.software.integration.design.data;

import java.util.Set;
public record Recipe(String name, RecipeType  type, ServeStyle serve_style,
                     Set<Ingredient> ingredients, int preparation_time,
                     Set<Allergen> allergens, Set<Drink> suitable_drinks ) {

    public Recipe {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }

        if (type == null) {
            throw new IllegalArgumentException("The type cannot be null!");
        }

        if (serve_style == null ) {
            throw new IllegalArgumentException("The serve_style cannot be null!");
        }

        if (ingredients == null || ingredients.isEmpty() ) {
            throw new IllegalArgumentException("The ingredients cannot be null or empty!");
        }

        if (preparation_time <= 0) {
            throw new IllegalArgumentException("The preparation_time cannot be zero or less than zero!");
        }
    }

}
