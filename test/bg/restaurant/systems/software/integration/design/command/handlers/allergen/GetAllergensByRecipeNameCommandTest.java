package bg.restaurant.systems.software.integration.design.command.handlers.allergen;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;


public class GetAllergensByRecipeNameCommandTest {

    private RestaurantAPI restaurant;
    private GetAllergensByRecipeNameCommand command;

    @BeforeEach
    public void setUp() {
        restaurant = mock(RestaurantAPI.class);
        command = new GetAllergensByRecipeNameCommand(restaurant, new String[] {"--recipe_name"});
    }

    @Test
    public void testExecuteValidRecipeNameReturnsAllergens() throws SQLException {
        String recipeName = "Spaghetti Bolognese";
        String expectedAllergens = "Gluten, Dairy";
        when(restaurant.getAllergensByRecipeName(recipeName)).thenReturn(expectedAllergens);

        command.args = new String[]{"--recipe_name", recipeName};
        String result = command.execute();

        assertEquals(expectedAllergens, result);
        verify(restaurant, times(1)).getAllergensByRecipeName(recipeName);
    }

    @Test
    public void testExecuteInvalidCommandTypeReturnsUnknownCommand() throws SQLException {
        command.args = new String[]{"invalidCommandType", "Some Recipe"};

        String result = command.execute();

        assertEquals("Unknown Command", result);
        verify(restaurant, never()).getAllergensByRecipeName(anyString());
    }

    @Test
    public void testExecuteValidRecipeNameWithNoAllergensReturnsNoAllergens() throws SQLException {
        String recipeName = "Vegan Salad";
        String expectedAllergens = "";
        when(restaurant.getAllergensByRecipeName(recipeName)).thenReturn(expectedAllergens);

        command.args = new String[]{"--recipe_name", recipeName};
        String result = command.execute();

        assertEquals(expectedAllergens, result);
        verify(restaurant, times(1)).getAllergensByRecipeName(recipeName);
    }

    @Test
    public void testExecuteValidRecipeNameWithInsufficientArguments() {
        command.args = new String[]{"--recipe_name"};
        assertThrowsExactly(IllegalArgumentException.class, () -> command.execute());
    }
}