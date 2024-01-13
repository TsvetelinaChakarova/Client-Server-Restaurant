package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.data.Drink;
import bg.restaurant.systems.software.integration.design.data.Recipe;
import bg.restaurant.systems.software.integration.design.data.ServeStyle;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeAlreadyExistsException;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeNotFoundException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

public interface Restaurant {

    String getAllergensByRecipeName(String recipeName) throws SQLException;

    Set<Recipe> getAllRecipes() throws SQLException;

    Set<Recipe> getAllRecipesByAllergens(Collection<String> allergens) throws SQLException;

    Set<Recipe> getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException;

    Set<Recipe> getAllRecipesByType(Collection<String> types) throws SQLException;

    ServeStyle getServingWayByRecipeName(String recipeName) throws SQLException;

    int getPreparationTimeForRecipeByName(String recipeName) throws SQLException;

    Set<Drink> getAllDrinks() throws SQLException;

//    /**
//     * Creates a new recipe recipe
//     *
//     * @param recipe recipe
//     * @throws RecipeAlreadyExistsException if the same recipe already exists
//     */
//    void createRecipe(Recipe recipe) throws RecipeAlreadyExistsException;
//
//    /**
//     * Retrieves all recipe recipes
//     *
//     * @return all recipe recipes from the storage, in undefined order.
//     * If there are no recipes in the storage, returns an empty collection.
//     */
//    Collection<Recipe> getRecipes();
//
//    /**
//     * Retrieves all recipe recipes with given ingredient
//     *
//     * @param ingredientName name of the ingredient (case-insensitive)
//     * @return all recipe recipes with given ingredient from the storage, in undefined order.
//     * If there are no recipes in the storage with the given ingredient, returns an empty collection.
//     */
//    Collection<Recipe> getRecipesWithIngredient(String ingredientName);
//
//    /**
//     * Retrieves a recipe recipe with the given name
//     *
//     * @param name recipe name (case-insensitive)
//     * @return recipe with the given name
//     * @throws RecipeNotFoundException if a recipe with the given name does not exist in the storage
//     */
//    Recipe getRecipe(String name) throws RecipeNotFoundException;

}