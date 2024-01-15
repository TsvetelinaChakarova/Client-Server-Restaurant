package bg.restaurant.systems.software.integration.design.data.drink;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DrinkTest {

    @Test
    public void testDrinkConstructor_ValidArguments_Success() {
        // Arrange
        String name = "Coca Cola";
        String type = "non-alcoholic";
        String serveStyle = "cold";

        Drink drink = new Drink(name, type, serveStyle);

        Assertions.assertEquals(name, drink.name());
        Assertions.assertEquals(type, drink.type());
        Assertions.assertEquals(serveStyle, drink.serveStyle());
    }

    @Test
    public void testDrinkConstructor_NullName_ThrowsIllegalArgumentException() {
        String name = null;
        String type = "non-alcoholic";
        String serveStyle = "cold";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_EmptyName_ThrowsIllegalArgumentException() {
        String name = "";
        String type = "non-alcoholic";
        String serveStyle = "cold";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_BlankName_ThrowsIllegalArgumentException() {
        String name = "   ";
        String type = "non-alcoholic";
        String serveStyle = "cold";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_NullType_ThrowsIllegalArgumentException() {
        String name = "Coca Cola";
        String type = null;
        String serveStyle = "cold";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_NullServeStyle_ThrowsIllegalArgumentException() {
        String name = "Coca Cola";
        String type = "non-alcoholic";
        String serveStyle = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkOf_ValidResultSet_Success() throws SQLException {
        ResultSet resultSet = createMockResultSet("Coca Cola", "non-alcoholic", "cold");

        Drink drink = Drink.of(resultSet);

        Assertions.assertEquals("Coca Cola", drink.name());
        Assertions.assertEquals("non-alcoholic", drink.type());
        Assertions.assertEquals("cold", drink.serveStyle());
    }

    private ResultSet createMockResultSet(String name, String type, String serveStyle) throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getString("type")).thenReturn(type);
        when(resultSet.getString("serve_style")).thenReturn(serveStyle);
        return resultSet;
    }
}