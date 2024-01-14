package bg.restaurant.systems.software.integration.design.command.handlers.serving;

import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetServingWayByRecipeNameCommandTest {
    private RestaurantAPI restaurant;
    private String[] args;

    @BeforeEach
    void setUp() {
        restaurant = mock(RestaurantAPI.class);
        args = new String[2];
    }

    @Test
    void testExecuteValidCommandReturnsServingWay() throws SQLException {
        // Arrange
        String recipeName = "recipeName";
        args[0] = CommandType.RECIPE_NAME.getCommandTypeString();
        args[1] = recipeName;

        GetServingWayByRecipeNameCommand command = new GetServingWayByRecipeNameCommand(restaurant, args);
        String expectedServingWay = "Serving way for recipeName";

        // Act
        when(restaurant.getServingWayByRecipeName(recipeName)).thenReturn(expectedServingWay);
        String result = command.execute();

        // Assert
        assertEquals(expectedServingWay, result);
    }

    @Test
    void testExecuteInvalidCommandReturnsUnknownCommand() throws SQLException {
        // Arrange
        GetServingWayByRecipeNameCommand command = new GetServingWayByRecipeNameCommand(restaurant, args);
        args[0] = "InvalidCommand";
        String expectedMessage = "Unknown Command";

        // Act
        String result = command.execute();

        // Assert
        assertEquals(expectedMessage, result);
    }
}
