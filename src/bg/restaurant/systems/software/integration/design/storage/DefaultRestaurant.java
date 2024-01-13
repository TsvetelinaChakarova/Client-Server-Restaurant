package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.Queries;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeAlreadyExistsException;
import bg.restaurant.systems.software.integration.design.storage.exceptions.RecipeNotFoundException;
import bg.restaurant.systems.software.integration.design.data.Recipe;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultRestaurant extends RestaurantValidator implements Restaurant {
    private final Set<Recipe> recipes;
    private final Queries queries;

    public DefaultRestaurant(Queries queries) {
        this.queries = queries;
        this.recipes = new HashSet<>();
    }

    public Queries getQueries() {
        return queries;
    }

    @Override
    public String getAllergensByRecipeName(String recipeName) throws SQLException {
        return queries.getAllergensByRecipeName(recipeName).toString();
    }
//
//    @Override
//    public void createRecipe(Recipe recipe) throws RecipeAlreadyExistsException {
//        validateRecipeForNull(recipe);
//        validateForExistingRecipe(recipe, recipes);
//        recipes.add(recipe);
//    }
//
//    @Override
//    public Collection<Recipe> getRecipes() {
//        return recipes;
//    }
//
//    @Override
//    public Collection<Recipe> getRecipesWithIngredient(String ingredientName) {
//        validateIngredientString(ingredientName);
//        return recipes.stream()
//            .filter(c -> c.ingredients().stream()
//                .anyMatch(i -> i.name().equalsIgnoreCase(ingredientName)))
//            .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Recipe getRecipe(String name) throws RecipeNotFoundException {
//        validateIngredientString(name);
//
//        return recipes.stream()
//            .filter(c -> c.name().equalsIgnoreCase(name))
//            .findFirst()
//            .orElseThrow(() -> new RecipeNotFoundException("There is no such recipe with this name!"));
//    }
}