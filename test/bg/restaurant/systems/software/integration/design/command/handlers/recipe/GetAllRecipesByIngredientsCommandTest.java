package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllRecipesByIngredientsCommandTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetAllRecipesByIngredientsCommand getAllRecipesByIngredientsCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllRecipesByIngredientsCommand = new GetAllRecipesByIngredientsCommand(mockRestaurant, new String[]{"ingredient1", "ingredient2"});
    }

    @Test
    public void testExecute() throws SQLException {
        // Arrange
        List<String> ingredients = List.of("ingredient1", "ingredient2");
        when(mockRestaurant.getAllRecipesByIngredients(List.of(new String[] {"ingredient1", "ingredient2"}))).thenReturn("expectedResult");

        // Act
        String result = getAllRecipesByIngredientsCommand.execute();

        // Assert
        assertEquals("expectedResult", result);
        verify(mockRestaurant, times(1)).getAllRecipesByIngredients(ingredients);
    }

    @Test
    public void testExecuteWithEmptyIngredients() throws SQLException {
        // Arrange
        getAllRecipesByIngredientsCommand = new GetAllRecipesByIngredientsCommand(mockRestaurant, new String[]{});

        // Act
        String result = getAllRecipesByIngredientsCommand.execute();

        // Assert
        assertEquals("", result);
        verify(mockRestaurant, never()).getAllRecipesByIngredients(anyList());
    }

    // Add more test methods as needed
}