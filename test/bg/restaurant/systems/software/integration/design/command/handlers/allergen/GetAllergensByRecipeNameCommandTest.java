package bg.restaurant.systems.software.integration.design.command.handlers.allergen;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

        String result =
            new GetAllergensByRecipeNameCommand(restaurant, new String[] {"--recipe_name", recipeName}).execute();

        assertEquals(expectedAllergens, result);
        verify(restaurant, times(1)).getAllergensByRecipeName(recipeName);
    }

    @Test
    public void testExecuteInvalidCommandTypeReturnsUnknownCommand() throws SQLException {
        String result =
            new GetAllergensByRecipeNameCommand(restaurant,
                new String[] {"invalidCommandType", "Some Recipe"}).execute();

        assertEquals("Unknown Command", result);
        verify(restaurant, never()).getAllergensByRecipeName(anyString());
    }

    @Test
    public void testExecuteValidRecipeNameWithNoAllergensReturnsNoAllergens() throws SQLException {
        String recipeName = "Vegan Salad";
        String expectedAllergens = "";
        when(restaurant.getAllergensByRecipeName(recipeName)).thenReturn(expectedAllergens);

        String result =
            new GetAllergensByRecipeNameCommand(restaurant, new String[] {"--recipe_name", recipeName})
                .execute();

        assertEquals(expectedAllergens, result);
        verify(restaurant, times(1)).getAllergensByRecipeName(recipeName);
    }

    @Test
    public void testExecuteValidRecipeNameWithInsufficientArguments() {
        assertThrowsExactly(IllegalArgumentException.class,
            () -> new GetAllergensByRecipeNameCommand(restaurant, new String[] {"--recipe_name"})
                .execute());
    }
}