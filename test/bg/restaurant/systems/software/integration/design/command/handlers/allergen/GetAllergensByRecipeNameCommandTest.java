package bg.restaurant.systems.software.integration.design.command.handlers.allergen;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class GetAllergensByRecipeNameCommandTest {

    private RestaurantAPI restaurant;
    private GetAllergensByRecipeNameCommand command;

    @BeforeEach
    public void setUp() {
        restaurant = mock(RestaurantAPI.class);
        command = new GetAllergensByRecipeNameCommand(restaurant, new String[0]);
    }

    @Test
    public void testExecute_ValidRecipeName_ReturnsAllergens() throws SQLException {
        // Arrange
        String recipeName = "Spaghetti Bolognese";
        String expectedAllergens = "Gluten, Dairy";
        when(restaurant.getAllergensByRecipeName(recipeName)).thenReturn(expectedAllergens);

        // Act
        String result = command.execute();

        // Assert
        assertEquals(expectedAllergens, result);
        verify(restaurant, times(1)).getAllergensByRecipeName(recipeName);
    }

//    тук се кара за args
//    @Test
//    public void testExecute_InvalidCommandType_ReturnsUnknownCommand() throws SQLException {
//        // Arrange
//        command.args = new String[]{"invalidCommandType", "Some Recipe"};
//
//        // Act
//        String result = command.execute();
//
//        // Assert
//        assertEquals("Unknown Command", result);
//        verify(restaurant, never()).getAllergensByRecipeName(anyString());
//    }

    @Test
    public void testExecute_ValidRecipeNameWithNoAllergens_ReturnsNoAllergens() throws SQLException {
        // Arrange
        String recipeName = "Vegan Salad";
        String expectedAllergens = "";
        when(restaurant.getAllergensByRecipeName(recipeName)).thenReturn(expectedAllergens);

        // Act
        command.args = new String[]{"recipeName", recipeName};
        String result = command.execute();

        // Assert
        assertEquals(expectedAllergens, result);
        verify(restaurant, times(1)).getAllergensByRecipeName(recipeName);
    }

    // Add more test cases to cover other scenarios

}

