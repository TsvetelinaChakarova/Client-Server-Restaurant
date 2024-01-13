package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.Queries;
import bg.restaurant.systems.software.integration.design.data.Drink;
import bg.restaurant.systems.software.integration.design.data.Recipe;
import bg.restaurant.systems.software.integration.design.data.ServeStyle;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public Set<Recipe> getAllRecipes() throws SQLException {
        return queries.getAllRecipes();
    }

    @Override
    public Set<Recipe> getAllRecipesByAllergens(Collection<String> allergens) throws SQLException {
        return queries.getAllRecipesByAllergens(allergens);
    }

    @Override
    public Set<Recipe> getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException {
        return queries.getAllRecipesByIngredients(ingredients);
    }

    @Override
    public Set<Recipe> getAllRecipesByType(Collection<String> types) throws SQLException {
        return queries.getAllRecipesByType(types);
    }

    @Override
    public ServeStyle getServingWayByRecipeName(String recipeName) throws SQLException {
        return queries.getServingWayByRecipeName(recipeName);
    }

    @Override
    public int getPreparationTimeForRecipeByName(String recipeName) throws SQLException {
        return queries.getPreparationTimeForRecipeByName(recipeName);
    }

    @Override
    public Set<Drink> getAllDrinks() throws SQLException {
        return queries.getAllDrinks();
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