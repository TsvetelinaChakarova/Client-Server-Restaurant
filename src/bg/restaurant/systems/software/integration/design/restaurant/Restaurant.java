package bg.restaurant.systems.software.integration.design.restaurant;

import bg.restaurant.systems.software.integration.design.storage.Queries;
import bg.restaurant.systems.software.integration.design.data.recipe.Recipe;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Restaurant extends RestaurantValidator implements RestaurantAPI {
    private final Queries queries;
    private final Gson gson;
    private static final String NO_RESULT = "No result!";

    public Restaurant(Queries queries, Gson gson) {
        this.queries = queries;
        this.gson = gson;
    }

    @Override
    public String getRecipeByName(String recipeName) throws SQLException {
        var result = queries.getRecipeByName(recipeName);

        if (result == null || result.isEmpty()) {
            return NO_RESULT;
        }

        Iterator<Recipe> iterator = result.iterator();

        return gson.toJson(iterator.next());
    }

    @Override
    public String getAllRecipes() throws SQLException {
        var result = queries.getAllRecipes();

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllRecipesByAllergens(Collection<String> allergens) throws SQLException {
        var result = queries.getAllRecipesByAllergens(allergens);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException {
        var result = queries.getAllRecipesByIngredients(ingredients);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllRecipesByType(Collection<String> types) throws SQLException {
        var result = queries.getAllRecipesByType(types);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getServingWayByRecipeName(String recipeName) throws SQLException {
        var result = queries.getServingWayByRecipeName(recipeName);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getPreparationTimeForRecipeByName(String recipeName) throws SQLException {
        var result = queries.getPreparationTimeForRecipeByName(recipeName);

        return result == 0 ? NO_RESULT : gson.toJson(result + " minutes");
    }

    @Override
    public String getAllDrinks() throws SQLException {
        var result = queries.getAllDrinks();

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllDrinksByRecipeName(String recipeName) throws SQLException {
        var result = queries.getAllDrinksByRecipeName(recipeName);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllergensByRecipeName(String recipeName) throws SQLException {
        var result = queries.getAllergensByRecipeName(recipeName);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getIngredientsByRecipeName(String recipeName) throws SQLException {
        var result = queries.getIngredientsByRecipeName(recipeName);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getFileWithRecipe(String recipeName, String filePath) throws SQLException {
        String result = getRecipeByName(recipeName);


        return "a";
    }
}