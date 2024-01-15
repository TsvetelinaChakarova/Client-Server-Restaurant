package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

public class GetAllDrinksByRecipeNameTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetAllDrinksByRecipeName getAllDrinksByRecipeName;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllDrinksByRecipeName = new GetAllDrinksByRecipeName(mockRestaurant, new String[]{"recipeName"});
    }

    @Test
    public void testExecute() throws SQLException {
        String recipeName = "recipeName";
        when(mockRestaurant.getAllDrinksByRecipeName(recipeName)).thenReturn("expectedResult");

        String result = getAllDrinksByRecipeName.execute();

        assertEquals("expectedResult", result);
        verify(mockRestaurant, times(1)).getAllDrinksByRecipeName(recipeName);
    }

    @Test
    public void testExecute_InvalidArgumentsLength() {
        getAllDrinksByRecipeName = new GetAllDrinksByRecipeName(mockRestaurant, new String[]{});

        assertThrowsExactly(IllegalArgumentException.class, () -> getAllDrinksByRecipeName.execute());
    }
}