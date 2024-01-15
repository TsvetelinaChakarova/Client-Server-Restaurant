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
    private String[] args;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        args = new String[2];
    }

    @Test
    public void testExecuteValidRecipeNameReturnsRecipe() throws SQLException {
        getRecipeByNameCommand = new GetRecipeByNameCommand(mockRestaurant, args);
        args[0] = "--recipe_name";
        String recipeName = "Pasta Carbonara";
        String expectedRecipe = "Recipe details for Pasta Carbonara";

        args[1] = recipeName;

        when(mockRestaurant.getRecipeByName(recipeName)).thenReturn(expectedRecipe);

        String result = getRecipeByNameCommand.execute();

        assertEquals(expectedRecipe, result);
    }

    @Test
    public void testExecuteUnknownCommandReturnsUnknownCommandMessage() throws SQLException {
        getRecipeByNameCommand = new GetRecipeByNameCommand(mockRestaurant, new String[0]);

        String[] args = {"INVALIDCOMMAND", "Recipe Name"};
        GetRecipeByNameCommand invalidCommand = new GetRecipeByNameCommand(mockRestaurant, args);
        String expectedMessage = "Unknown Command";

        String result = invalidCommand.execute();

        assertEquals(expectedMessage, result);
    }
}