package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeAlreadyExistsException;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeNotFoundException;
import bg.restaurant.systems.software.integration.design.data.Recipe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultRestaurant extends RestaurantValidator implements Restaurant {
    private final Set<Recipe> recipes;

    public DefaultRestaurant() {
        this.recipes = new HashSet<>();
    }

    @Override
    public void createRecipe(Recipe recipe) throws RecipeAlreadyExistsException {
        validateRecipeForNull(recipe);
        validateForExistingRecipe(recipe, recipes);
        recipes.add(recipe);
    }

    @Override
    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public Collection<Recipe> getRecipesWithIngredient(String ingredientName) {
        validateIngredientString(ingredientName);
        return recipes.stream()
            .filter(c -> c.ingredients().stream()
                .anyMatch(i -> i.name().equalsIgnoreCase(ingredientName)))
            .collect(Collectors.toSet());
    }

    @Override
    public Recipe getRecipe(String name) throws RecipeNotFoundException {
        validateIngredientString(name);

        return recipes.stream()
            .filter(c -> c.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new RecipeNotFoundException("There is no such recipe with this name!"));
    }
}