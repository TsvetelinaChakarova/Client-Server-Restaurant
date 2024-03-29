package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllDrinksCommandTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    private GetAllDrinksCommand getAllDrinksCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllDrinksCommand = new GetAllDrinksCommand(mockRestaurant);
    }

    @Test
    public void testExecute() throws SQLException {
        String expectedDrinks = "Drink1, Drink2, Drink3";
        when(mockRestaurant.getAllDrinks()).thenReturn(expectedDrinks);

        String result = getAllDrinksCommand.execute();

        assertEquals(expectedDrinks, result);
        verify(mockRestaurant, times(1)).getAllDrinks();
    }
}