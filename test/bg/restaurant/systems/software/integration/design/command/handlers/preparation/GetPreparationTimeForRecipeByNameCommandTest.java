package bg.restaurant.systems.software.integration.design.command.handlers.preparation;


import bg.restaurant.systems.software.integration.design.command.CommandType;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetPreparationTimeForRecipeByNameCommandTest {

    private RestaurantAPI restaurant;
    private String[] args;

    @BeforeEach
    public void setUp() {
        restaurant = mock(RestaurantAPI.class);
        args = new String[2];
    }

    @Test
    public void testExecuteValidRecipeNameReturnsPreparationTime() throws SQLException {
        GetPreparationTimeForRecipeByNameCommand command = new GetPreparationTimeForRecipeByNameCommand(restaurant, args);
        String recipeName = "Spaghetti";
        args[0] = CommandType.RECIPE_NAME.getCommandTypeString();
        args[1] = recipeName;

        int expectedPreparationTime = 30;

        when(restaurant.getPreparationTimeForRecipeByName(recipeName)).thenReturn(String.valueOf(expectedPreparationTime));
        String result = command.execute();

        assertEquals(String.valueOf(expectedPreparationTime), result);
        verify(restaurant, times(1)).getPreparationTimeForRecipeByName(recipeName);
    }

    @Test
    public void testExecuteUnknownCommandReturnsUnknownCommandMessage() throws SQLException {
        String[] args = {"invalid", "recipeName"};
        GetPreparationTimeForRecipeByNameCommand command = new GetPreparationTimeForRecipeByNameCommand(restaurant, args);

        String result = command.execute();

        assertEquals("Unknown Command", result);
        verify(restaurant, never()).getPreparationTimeForRecipeByName(anyString());
    }
}
