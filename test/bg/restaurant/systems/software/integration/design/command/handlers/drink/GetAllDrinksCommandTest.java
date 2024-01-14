package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // Arrange
        String expectedDrinks = "Drink1, Drink2, Drink3";
        when(mockRestaurant.getAllDrinks()).thenReturn(expectedDrinks);

        // Act
        String result = getAllDrinksCommand.execute();

        // Assert
        assertEquals(expectedDrinks, result);
        verify(mockRestaurant, times(1)).getAllDrinks();
    }

    // Add more test methods as needed
}