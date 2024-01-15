package bg.restaurant.systems.software.integration.design.restaurant;

import bg.restaurant.systems.software.integration.design.storage.DatabaseConnection;
import bg.restaurant.systems.software.integration.design.storage.Queries;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RestaurantTest {

    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        DatabaseConnection databaseConnection = new DatabaseConnection(
            "jdbc:mysql://localhost:3306/restaurants", "root", "");

        Queries queries = new Queries(databaseConnection);
        Gson gson = new Gson();
        restaurant = new Restaurant(queries, gson);
    }

    @Test
    public void testGetRecipeByName() throws SQLException {
        // Arrange
        String recipeName = "Recipe 1";

        // Act
        String result = restaurant.getRecipeByName(recipeName);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllRecipes() throws SQLException {
        // Act
        String result = restaurant.getAllRecipes();

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllRecipesByAllergens() throws SQLException {
        // Arrange
        List<String> allergens = Arrays.asList("Allergen 1", "Allergen 2");

        // Act
        String result = restaurant.getAllRecipesByAllergens(allergens);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllRecipesByIngredients() throws SQLException {
        // Arrange
        List<String> ingredients = Arrays.asList("Ingredient 1", "Ingredient 2");

        // Act
        String result = restaurant.getAllRecipesByIngredients(ingredients);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllRecipesByType() throws SQLException {
        // Arrange
        List<String> types = Arrays.asList("Type 1", "Type 2");

        // Act
        String result = restaurant.getAllRecipesByType(types);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetServingWayByRecipeName() throws SQLException {
        // Arrange
        String recipeName = "Recipe 1";

        // Act
        String result = restaurant.getServingWayByRecipeName(recipeName);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetPreparationTimeForRecipeByName() throws SQLException {
        // Arrange
        String recipeName = "Recipe 1";

        // Act
        String result = restaurant.getPreparationTimeForRecipeByName(recipeName);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllDrinks() throws SQLException {
        // Act
        String result = restaurant.getAllDrinks();

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllDrinksByRecipeName() throws SQLException {
        // Arrange
        String recipeName = "Recipe 1";

        // Act
        String result = restaurant.getAllDrinksByRecipeName(recipeName);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetAllergensByRecipeName() throws SQLException {
        // Arrange
        String recipeName = "Recipe 1";

        // Act
        String result = restaurant.getAllergensByRecipeName(recipeName);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetIngredientsByRecipeName() throws SQLException {
        String recipeName = "Recipe 1";

        String result = restaurant.getIngredientsByRecipeName(recipeName);

    }
}