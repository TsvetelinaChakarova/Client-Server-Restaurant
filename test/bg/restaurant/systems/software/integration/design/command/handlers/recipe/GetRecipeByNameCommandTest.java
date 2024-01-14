package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetRecipeByNameCommandTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetRecipeByNameCommand getRecipeByNameCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getRecipeByNameCommand = new GetRecipeByNameCommand(mockRestaurant, new String[0]);
    }

    @Test
    public void testExecute_ValidRecipeName_ReturnsRecipe() throws SQLException {
        // Arrange
        String recipeName = "Pasta Carbonara";
        String expectedRecipe = "Recipe details for Pasta Carbonara";

        when(mockRestaurant.getRecipeByName(recipeName)).thenReturn(expectedRecipe);

        // Act
        String result = getRecipeByNameCommand.execute();

        // Assert
        assertEquals(expectedRecipe, result);
    }

    @Test
    public void testExecute_UnknownCommand_ReturnsUnknownCommandMessage() throws SQLException {
        // Arrange
        String[] args = {"INVALID_COMMAND", "Recipe Name"};
        GetRecipeByNameCommand invalidCommand = new GetRecipeByNameCommand(mockRestaurant, args);
        String expectedMessage = "Unknown Command";

        // Act
        String result = invalidCommand.execute();

        // Assert
        assertEquals(expectedMessage, result);
    }

    // Add more test methods as needed
}