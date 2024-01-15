package bg.restaurant.systems.software.integration.design.command.handlers.drink;

import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DrinkCommandExecutorTest {
    @Mock
    private RestaurantAPI mockRestaurant;

    private DrinkCommandExecutor drinkCommandExecutor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        drinkCommandExecutor = new DrinkCommandExecutor(mockRestaurant);
    }

    @Test
    public void testExecuteWithAll() throws SQLException {
        when(mockRestaurant.getAllDrinks()).thenReturn("All drinks");
        String[] arguments = {"--all"};

        String result = drinkCommandExecutor.execute(arguments);

        assertEquals("All drinks", result);
    }

    @Test
    public void testExecuteWithRecipeName() throws SQLException {
        when(mockRestaurant.getAllDrinksByRecipeName(any())).thenReturn("All drinks");

        String[] arguments = {"--recipe_name", " Spaghetti"};

        String result = drinkCommandExecutor.execute(arguments);

        assertEquals("All drinks", result);
    }

    @Test
    public void testExecuteNullCommand() {
        assertThrowsExactly(IllegalArgumentException.class, () -> drinkCommandExecutor.execute(null));
    }

    @Test
    public void testExecuteWithNoSuchCommand() throws SQLException {
        String[] arguments = {"--recipeName", " Spaghetti"};

        assertEquals("Unknown command", drinkCommandExecutor.execute(arguments));
    }
}
