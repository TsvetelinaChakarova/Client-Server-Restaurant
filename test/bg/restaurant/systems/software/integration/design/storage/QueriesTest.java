package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.data.allergen.Allergen;
import bg.restaurant.systems.software.integration.design.data.drink.Drink;
import bg.restaurant.systems.software.integration.design.data.ingredient.Ingredient;
import bg.restaurant.systems.software.integration.design.data.recipe.Recipe;
import bg.restaurant.systems.software.integration.design.data.serve.ServeStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueriesTest {
    @Mock
    private DatabaseConnection databaseConnection;
    private final Recipe recipe = new Recipe("cake", "breakfast", "cold",
        Set.of(new Ingredient("sugar"), new Ingredient("flour")), 10,
        Set.of(new Allergen("eggs")),
        Set.of(new Drink("coffee", "coffee", "hot")));

    private final Drink drink = new Drink("coffee", "coffee", "hot");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecipeByName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(recipeName);

        Queries queries = mock(Queries.class);
        when(queries.getRecipeByName(recipeName)).thenReturn(Set.of(recipe));

        Set<Recipe> recipes = queries.getRecipeByName(recipeName);

        assertEquals(1, recipes.size());
        Recipe recipe = recipes.iterator().next();
        assertEquals(recipeName, recipe.name());
    }

    @Test
    void testGetAllRecipes() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn("cake");

        Queries queries = mock(Queries.class);
        when(queries.getAllRecipes()).thenReturn(Set.of(recipe));
        Set<Recipe> recipes = queries.getAllRecipes();

        assertEquals(1, recipes.size());
        Recipe recipe = recipes.iterator().next();
        assertEquals("cake", recipe.name());
    }

    @Test
    void testGetAllRecipesByAllergens() throws SQLException {
        Collection<String> allergens = Set.of("eggs");
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn("cake");

        Queries queries = mock(Queries.class);
        when(queries.getAllRecipesByAllergens(allergens)).thenReturn(Set.of(recipe));
        Set<Recipe> recipes = queries.getAllRecipesByAllergens(allergens);

        assertEquals(1, recipes.size());
        Recipe recipe = recipes.iterator().next();
        assertEquals("cake", recipe.name());
    }

    @Test
    void testGetAllRecipesByIngredients() throws SQLException {
        Collection<String> ingredients = Set.of("flour", "sugar");
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn("cake");

        Queries queries = mock(Queries.class);
        when(queries.getAllRecipesByIngredients(ingredients)).thenReturn(Set.of(recipe));
        Set<Recipe> recipes = queries.getAllRecipesByIngredients(ingredients);

        assertEquals(1, recipes.size());
        Recipe recipe = recipes.iterator().next();
        assertEquals("cake", recipe.name());
    }

    @Test
    void testGetAllRecipesByType() throws SQLException {
        Collection<String> types = Set.of("breakfast");
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn("cake");

        Queries queries = mock(Queries.class);
        when(queries.getAllRecipesByType(types)).thenReturn(Set.of(recipe));
        Set<Recipe> recipes = queries.getAllRecipesByType(types);

        assertEquals(1, recipes.size());
        Recipe recipe = recipes.iterator().next();
        assertEquals("cake", recipe.name());
    }

    @Test
    void testGetServingWayByRecipeName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn(ServeStyle.HOT.getTypeString());

        Queries queries = new Queries(databaseConnection);
        ServeStyle serveStyle = queries.getServingWayByRecipeName(recipeName);

        assertEquals(ServeStyle.HOT, serveStyle);
    }

    @Test
    void testGetPreparationTimeForRecipeByName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(anyString())).thenReturn(60);

        Queries queries = new Queries(databaseConnection);
        int preparationTime = queries.getPreparationTimeForRecipeByName(recipeName);

        assertEquals(60, preparationTime);
    }

    @Test
    void testGetAllDrinksByRecipeName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);

        Queries queries = mock(Queries.class);
        when(queries.getAllDrinksByRecipeName(recipeName)).thenReturn(new HashSet<>());
        Set<Drink> drinks = queries.getAllDrinksByRecipeName(recipeName);

        assertEquals(0, drinks.size());
    }

    @Test
    void testGetAllergensByRecipeName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn("gluten");

        Queries queries = new Queries(databaseConnection);
        Set<Allergen> allergens = queries.getAllergensByRecipeName(recipeName);

        assertEquals(1, allergens.size());
        Allergen allergen = allergens.iterator().next();
        assertEquals("gluten", allergen.name());
    }

    @Test
    void testGetIngredientsByRecipeName() throws SQLException {
        String recipeName = "cake";
        ResultSet resultSet = mock(ResultSet.class);
        when(databaseConnection.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(anyString())).thenReturn("flour");

        Queries queries = new Queries(databaseConnection);
        Set<Ingredient> ingredients = queries.getIngredientsByRecipeName(recipeName);

        assertEquals(1, ingredients.size());
        Ingredient ingredient = ingredients.iterator().next();
        assertEquals("flour", ingredient.name());
    }
}