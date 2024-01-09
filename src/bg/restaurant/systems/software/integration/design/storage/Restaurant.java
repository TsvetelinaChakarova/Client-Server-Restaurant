package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.Recipe;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeAlreadyExistsException;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeNotFoundException;

import java.util.Collection;

public interface Restaurant {

    /**
     * Creates a new recipe recipe
     *
     * @param recipe recipe
     * @throws RecipeAlreadyExistsException if the same recipe already exists
     */
    void createRecipe(Recipe recipe) throws RecipeAlreadyExistsException;

    /**
     * Retrieves all recipe recipes
     *
     * @return all recipe recipes from the storage, in undefined order.
     * If there are no recipes in the storage, returns an empty collection.
     */
    Collection<Recipe> getRecipes();

    /**
     * Retrieves all recipe recipes with given ingredient
     *
     * @param ingredientName name of the ingredient (case-insensitive)
     * @return all recipe recipes with given ingredient from the storage, in undefined order.
     * If there are no recipes in the storage with the given ingredient, returns an empty collection.
     */
    Collection<Recipe> getRecipesWithIngredient(String ingredientName);

    /**
     * Retrieves a recipe recipe with the given name
     *
     * @param name recipe name (case-insensitive)
     * @return recipe with the given name
     * @throws RecipeNotFoundException if a recipe with the given name does not exist in the storage
     */
    Recipe getRecipe(String name) throws RecipeNotFoundException;

}