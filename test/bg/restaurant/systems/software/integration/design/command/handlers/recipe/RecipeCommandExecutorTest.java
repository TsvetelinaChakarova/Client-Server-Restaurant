package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RecipeCommandExecutorTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private RecipeCommandExecutor recipeCommandExecutor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeCommandExecutor = new RecipeCommandExecutor(mockRestaurant);
    }

    @Test
    public void testExecuteWithAll() throws SQLException {
        when(mockRestaurant.getAllRecipes()).thenReturn("All recipes");
        String[] arguments = {"--all"};

        String result = recipeCommandExecutor.execute(arguments);

        assertEquals("All recipes", result);
    }

    @Test
    public void testExecuteWithType() throws SQLException {
        when(mockRestaurant.getAllRecipesByType(any())).thenReturn("All recipes");

        String[] arguments = {"--type", "breakfast"};

        String result = recipeCommandExecutor.execute(arguments);

        assertEquals("All recipes", result);
    }

    @Test
    public void testExecuteWithIngredients() throws SQLException {
        when(mockRestaurant.getAllRecipesByIngredients(any())).thenReturn("All recipes");

        String[] arguments = {"--ingredients", "salt", "pepper"};

        String result = recipeCommandExecutor.execute(arguments);

        assertEquals("All recipes", result);
    }

    @Test
    public void testExecuteWithAllergens() throws SQLException {
        when(mockRestaurant.getAllRecipesByAllergens(any())).thenReturn("All recipes");

        String[] arguments = {"--allergens", "milk", "gluten"};

        String result = recipeCommandExecutor.execute(arguments);

        assertEquals("All recipes", result);
    }

    @Test
    public void testExecuteNullCommand() {
        assertThrowsExactly(IllegalArgumentException.class, () -> recipeCommandExecutor.execute(null));
    }

    @Test
    public void testExecuteWithNoSuchCommand() throws SQLException {
        String[] arguments = {"--recipeName", " Spaghetti"};

        assertEquals("Unknown command", recipeCommandExecutor.execute(arguments));
    }
}