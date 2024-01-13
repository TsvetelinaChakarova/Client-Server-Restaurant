package bg.restaurant.systems.software.integration.design.restaurant;

import java.sql.SQLException;
import java.util.Collection;

public interface Restaurant {

    String getAllergensByRecipeName(String recipeName) throws SQLException;

    String getRecipeByName(String recipeName) throws SQLException;

    String getAllRecipes() throws SQLException;

    String getAllRecipesByAllergens(Collection<String> allergens) throws SQLException;

    String getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException;

    String getAllRecipesByType(Collection<String> types) throws SQLException;

    String getServingWayByRecipeName(String recipeName) throws SQLException;

    String getPreparationTimeForRecipeByName(String recipeName) throws SQLException;

    String getAllDrinks() throws SQLException;

    String getAllDrinksByRecipeName(String recipeName) throws SQLException;

    String getIngredientsByRecipeName(String recipeName) throws SQLException;

    String getFileWithRecipe(String recipeName, String filePath) throws SQLException;
}