package bg.restaurant.systems.software.integration.design.restaurant;

import bg.restaurant.systems.software.integration.design.data.recipe.Recipe;
import bg.restaurant.systems.software.integration.design.exceptions.RecipeAlreadyExistsException;

import java.util.Set;

public class RestaurantValidator {
    void validateForExistingRecipe(Recipe recipe, Set<Recipe> recipeSet) throws
            RecipeAlreadyExistsException {
        if (recipeSet.contains(recipe)) {
            throw new RecipeAlreadyExistsException("The recipe you want to add is already existing!");
        }
    }

    void validateRecipeForNull(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("The recipe cannot be null!");
        }
    }

    void validateIngredientString(String ingredientName) {
        if (ingredientName == null || ingredientName.isEmpty() || ingredientName.isBlank()) {
            throw new IllegalArgumentException("The ingredient name cannot be null, empty or blank!");
        }
    }
}