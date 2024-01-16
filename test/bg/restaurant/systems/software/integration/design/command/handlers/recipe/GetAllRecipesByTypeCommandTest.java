package bg.restaurant.systems.software.integration.design.command.handlers.recipe;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllRecipesByTypeCommandTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetAllRecipesByTypeCommand getAllRecipesByTypeCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllRecipesByTypeCommand = new GetAllRecipesByTypeCommand(mockRestaurant, new String[]{"type1", "type2"});
    }

    @Test
    public void testExecute() throws SQLException {
        List<String> expectedRecipes = List.of("recipe1", "recipe2");
        when(mockRestaurant.getAllRecipesByType(List.of("type1", "type2"))).thenReturn(String.valueOf(expectedRecipes));

        String result = getAllRecipesByTypeCommand.execute();

        assertEquals(expectedRecipes.toString(), result);
        verify(mockRestaurant, times(1)).getAllRecipesByType(List.of("type1", "type2"));
    }

}