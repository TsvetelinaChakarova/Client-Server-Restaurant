package bg.restaurant.systems.software.integration.design.command.handlers.ingredient;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetIngredientsByRecipeNameCommandTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetIngredientsByRecipeNameCommand command;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] args = {"--recipe_name", "Pizza"};
        command = new GetIngredientsByRecipeNameCommand(mockRestaurant, args);
    }

    @Test
    public void testExecuteValidCommandReturnsIngredients() throws SQLException {
        String expectedIngredients = "Flour, Tomato Sauce, Cheese";
        when(mockRestaurant.getIngredientsByRecipeName("Pizza")).thenReturn(expectedIngredients);

        String result = command.execute();

        assertEquals(expectedIngredients, result);
        verify(mockRestaurant, times(1)).getIngredientsByRecipeName("Pizza");
    }

    @Test
    public void testExecuteInvalidCommandReturnsUnknownCommand() throws SQLException {
        String[] args = {"invalid_command", "Pizza"};
        command = new GetIngredientsByRecipeNameCommand(mockRestaurant, args);

        String result = command.execute();

        assertEquals("Unknown Command", result);
        verify(mockRestaurant, never()).getIngredientsByRecipeName(anyString());
    }
}