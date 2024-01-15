package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.logger.ErrorLogger;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class CommandExecutorTest {

    @Mock
    private RestaurantAPI mockRestaurant;

    @Mock
    private ErrorLogger errorLogger;

    private CommandExecutor commandExecutor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commandExecutor = new CommandExecutor(mockRestaurant, errorLogger);
    }

    @Test
    public void testExecuteRecipes() throws SQLException {
        when(mockRestaurant.getAllRecipes()).thenReturn("All recipes");
        Command command = new Command("recipes", new String[] {"--all"});

        String result = commandExecutor.execute(command);

        assertEquals("All recipes", result);
    }

    @Test
    public void testExecuteRecipeName() throws SQLException {
        when(mockRestaurant.getRecipeByName("cake")).thenReturn("Cake");
        Command command = new Command("recipe", new String[] {"--recipe_name", "cake"});

        String result = commandExecutor.execute(command);

        assertEquals("Cake", result);
    }

    @Test
    public void testExecuteDrinks() throws SQLException {
        when(mockRestaurant.getAllDrinks()).thenReturn("All drinks");
        Command command = new Command("drinks", new String[] {"--all"});

        String result = commandExecutor.execute(command);

        assertEquals("All drinks", result);
    }

    @Test
    public void testExecuteIngredients() throws SQLException {
        when(mockRestaurant.getIngredientsByRecipeName("cookies")).thenReturn("Dough");
        Command command = new Command("ingredients", new String[] {"--recipe_name", "cookies"});

        String result = commandExecutor.execute(command);

        assertEquals("Dough", result);
    }

    @Test
    public void testExecuteAllergens() throws SQLException {
        when(mockRestaurant.getAllergensByRecipeName("cake")).thenReturn("Eggs");
        Command command = new Command("allergens", new String[] {"--recipe_name", "cake"});

        String result = commandExecutor.execute(command);

        assertEquals("Eggs", result);
    }

    @Test
    public void testExecutePreparationTime() throws SQLException {
        when(mockRestaurant.getPreparationTimeForRecipeByName("cake")).thenReturn("200 minutes");
        Command command = new Command("prep_time", new String[] {"--recipe_name", "cake"});

        String result = commandExecutor.execute(command);

        assertEquals("200 minutes", result);
    }

    @Test
    public void testExecuteServingWay() throws SQLException {
        when(mockRestaurant.getServingWayByRecipeName("cake")).thenReturn("cold");
        Command command = new Command("serve_way", new String[] {"--recipe_name", "cake"});

        String result = commandExecutor.execute(command);

        assertEquals("cold", result);
    }

    @Test
    public void testExecuteNullCommand() throws IOException {
        doThrow(IllegalArgumentException.class).when(errorLogger).appendLogs(any());
        assertThrowsExactly(IllegalArgumentException.class, () -> commandExecutor.execute(null));
    }

    @Test
    public void testExecuteWithNoSuchCommand() {
        assertEquals("Unknown Command",
            commandExecutor.execute(new Command("serveway", new String[] {"--recipeName", "cake"})));
    }

    @Test
    public void testThrowsExceptionWhenRestaurantNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new CommandExecutor(null, null));
    }

    @Test
    public void testExecuteExceptionReturn() {
        assertEquals("There was an error in the application, please try again!", commandExecutor.execute(null));
    }

    @Test
    public void testExecuteIOException() throws IOException {
        doThrow(IOException.class).when(errorLogger).appendLogs(any());
        assertEquals("There was an error in the application, please try again!", commandExecutor.execute(null));
    }
}
