package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllRecipesByIngredientsCommandTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetAllRecipesByIngredientsCommand getAllRecipesByIngredientsCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllRecipesByIngredientsCommand =
            new GetAllRecipesByIngredientsCommand(mockRestaurant, new String[] {"ingredient1", "ingredient2"});
    }

    @Test
    public void testExecute() throws SQLException {
        List<String> ingredients = List.of("ingredient1", "ingredient2");
        when(
            mockRestaurant.getAllRecipesByIngredients(List.of(new String[] {"ingredient1", "ingredient2"}))).thenReturn(
            "expectedResult");

        String result = getAllRecipesByIngredientsCommand.execute();

        assertEquals("expectedResult", result);
        verify(mockRestaurant, times(1)).getAllRecipesByIngredients(ingredients);
    }

    @Test
    public void testExecuteWithEmptyIngredients() throws SQLException {
        getAllRecipesByIngredientsCommand = new GetAllRecipesByIngredientsCommand(mockRestaurant, new String[] {});

        assertThrowsExactly(IllegalArgumentException.class, () -> getAllRecipesByIngredientsCommand.execute());
        verify(mockRestaurant, never()).getAllRecipesByIngredients(anyList());
    }
}