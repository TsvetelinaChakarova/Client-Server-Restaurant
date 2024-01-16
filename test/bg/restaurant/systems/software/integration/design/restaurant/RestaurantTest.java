package bg.restaurant.systems.software.integration.design.restaurant;

import bg.restaurant.systems.software.integration.design.data.allergen.Allergen;
import bg.restaurant.systems.software.integration.design.data.drink.Drink;
import bg.restaurant.systems.software.integration.design.data.ingredient.Ingredient;
import bg.restaurant.systems.software.integration.design.data.recipe.Recipe;
import bg.restaurant.systems.software.integration.design.data.serve.ServeStyle;
import bg.restaurant.systems.software.integration.design.storage.DatabaseConnection;
import bg.restaurant.systems.software.integration.design.storage.Queries;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantTest {
    private Queries queries;
    private Restaurant restaurant;
    private DatabaseConnection databaseConnection;
    private Gson gson;

    private final Recipe recipe = new Recipe("cake", "breakfast", "cold",
        Set.of(new Ingredient("sugar")), 10,
        Set.of(new Allergen("eggs")),
        Set.of(new Drink("coffee", "coffee", "hot")));

    private final Drink drink = new Drink("coffee", "coffee", "hot");
    private final ServeStyle serveStyle = ServeStyle.COLD;

    @BeforeEach
    public void setUp() {
        databaseConnection = mock(DatabaseConnection.class);
        queries = mock(Queries.class);
        gson = new Gson();
        restaurant = new Restaurant(queries, gson);
    }

    @Test
    public void testGetRecipeByName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);
        when(queries.getRecipeByName(recipeName)).thenReturn(Set.of(recipe));

        String result = restaurant.getRecipeByName(recipeName);
        assertEquals(gson.toJson(recipe), result);
    }

    @Test
    public void testGetRecipeByNameWithNoRecipe() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(null);
        when(queries.getRecipeByName(recipeName)).thenReturn(null);

        String result = restaurant.getRecipeByName(recipeName);
        assertEquals("No result!", result);
    }

    @Test
    public void testGetAllRecipes() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        when(queries.getAllRecipes()).thenReturn(new HashSet<>(List.of(recipe)));

        String result = restaurant.getAllRecipes();
        assertEquals(gson.toJson(List.of(recipe)), result);
    }

    @Test
    public void testGetAllRecipesByAllergens() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        when(queries.getAllRecipesByAllergens(List.of("eggs"))).thenReturn(Set.of(recipe));

        String result = restaurant.getAllRecipesByAllergens(List.of("eggs"));
        assertEquals(gson.toJson(List.of(recipe)), result);
    }

    @Test
    public void testGetAllRecipesByIngredients() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        when(queries.getAllRecipesByIngredients(List.of("sugar"))).thenReturn(Set.of(recipe));

        String result = restaurant.getAllRecipesByIngredients(List.of("sugar"));
        assertEquals(gson.toJson(List.of(recipe)), result);
    }

    @Test
    public void testGetAllRecipesByType() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        when(queries.getAllRecipesByType(List.of("breakfast"))).thenReturn(Set.of(recipe));

        String result = restaurant.getAllRecipesByType(List.of("breakfast"));
        assertEquals(gson.toJson(List.of(recipe)), result);
    }

    @Test
    public void testGetServingWayByRecipeName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        when(queries.getServingWayByRecipeName(recipeName)).thenReturn(ServeStyle.COLD);

        String result = restaurant.getServingWayByRecipeName(recipeName);
        assertEquals(gson.toJson(ServeStyle.COLD.getTypeString()), result);
    }

    @Test
    public void testGetPreparationTimeForRecipeByName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        when(queries.getPreparationTimeForRecipeByName(recipeName)).thenReturn(recipe.preparationTime());

        String result = restaurant.getPreparationTimeForRecipeByName(recipeName);
        assertEquals(gson.toJson(recipe.preparationTime() + " minutes"), result);
    }

    @Test
    public void testGetAllDrinks() throws SQLException {
        String drinkName = "coffee";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(drinkName);

        when(queries.getAllDrinks()).thenReturn(Set.of(drink));

        String result = restaurant.getAllDrinks();
        assertEquals(gson.toJson(List.of(drink)), result);
    }

    @Test
    public void testGetAllDrinksByRecipeName() throws SQLException {
        String drinkName = "coffee";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(drinkName);

        when(queries.getAllDrinksByRecipeName(recipe.name())).thenReturn(Set.of(drink));

        String result = restaurant.getAllDrinksByRecipeName(recipe.name());
        assertEquals(gson.toJson(List.of(drink)), result);
    }

    @Test
    public void testGetAllergensByRecipeName() throws SQLException {
        String drinkName = "coffee";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(drinkName);

        when(queries.getAllergensByRecipeName(recipe.name())).thenReturn(
            Set.of(recipe.allergens().toArray(new Allergen[0])));

        String result = restaurant.getAllergensByRecipeName(recipe.name());
        assertEquals(gson.toJson(recipe.allergens()), result);
    }

    @Test
    public void testGetIngredientsByRecipeName() throws SQLException {
        String drinkName = "coffee";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(drinkName);

        when(queries.getIngredientsByRecipeName(recipe.name())).thenReturn(
            Set.of(recipe.ingredients().toArray(new Ingredient[0])));

        String result = restaurant.getIngredientsByRecipeName(recipe.name());
        assertEquals(gson.toJson(recipe.ingredients()), result);
    }
}