package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.Queries;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.Collection;

public class DefaultRestaurant extends RestaurantValidator implements Restaurant {
    private final Queries queries;
    private final Gson gson;
    private static final String NO_RESULT = "No result!";

    public DefaultRestaurant(Queries queries, Gson gson) {
        this.queries = queries;
        this.gson = gson;
    }

    @Override
    public String getAllRecipes() throws SQLException {
        var result = queries.getAllRecipes();

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllRecipesByAllergens(Collection<String> allergens) throws SQLException {
        var result = queries.getAllRecipesByAllergens(allergens);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException {
        var result = queries.getAllRecipesByIngredients(ingredients);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllRecipesByType(Collection<String> types) throws SQLException {
        var result = queries.getAllRecipesByType(types);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getServingWayByRecipeName(String recipeName) throws SQLException {
        var result = queries.getServingWayByRecipeName(recipeName);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getPreparationTimeForRecipeByName(String recipeName) throws SQLException {
        var result = queries.getPreparationTimeForRecipeByName(recipeName);

        return result == 0 ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllDrinks() throws SQLException {
        var result = queries.getAllDrinks();

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllDrinksByRecipeName(String recipeName) throws SQLException {
        var result = queries.getAllDrinksByRecipeName(recipeName);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllergensByRecipeName(String recipeName) throws SQLException {
        var result = queries.getAllergensByRecipeName(recipeName);

        return result == null ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getIngredientsByRecipeName(String recipeName) throws SQLException {
        var result = queries.getIngredientsByRecipeName(recipeName);

        return result == null ? NO_RESULT : gson.toJson(result);
    }
}