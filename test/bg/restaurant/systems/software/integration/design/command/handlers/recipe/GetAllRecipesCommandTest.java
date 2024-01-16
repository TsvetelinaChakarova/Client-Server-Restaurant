package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllRecipesCommandTest {

    private GetAllRecipesCommand getAllRecipesCommand;

    @Mock
    private RestaurantAPI mockRestaurant;

    @BeforeEach
    public void setUp() {
        mockRestaurant = mock(RestaurantAPI.class);
        getAllRecipesCommand = new GetAllRecipesCommand(mockRestaurant);
    }

    @Test
    public void testExecute() throws SQLException {
        String expectedOutput = "Mocked recipe data"; // Replace with your expected output

        when(mockRestaurant.getAllRecipes()).thenReturn(expectedOutput);

        String actualOutput = getAllRecipesCommand.execute();

        assertEquals(expectedOutput, actualOutput);
    }
}