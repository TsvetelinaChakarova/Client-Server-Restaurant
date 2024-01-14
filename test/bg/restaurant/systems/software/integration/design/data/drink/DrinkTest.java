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

        // Act
        Drink drink = new Drink(name, type, serveStyle);

        // Assert
        Assertions.assertEquals(name, drink.name());
        Assertions.assertEquals(type, drink.type());
        Assertions.assertEquals(serveStyle, drink.serveStyle());
    }

    @Test
    public void testDrinkConstructor_NullName_ThrowsIllegalArgumentException() {
        // Arrange
        String name = null;
        String type = "non-alcoholic";
        String serveStyle = "cold";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_EmptyName_ThrowsIllegalArgumentException() {
        // Arrange
        String name = "";
        String type = "non-alcoholic";
        String serveStyle = "cold";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_BlankName_ThrowsIllegalArgumentException() {
        // Arrange
        String name = "   ";
        String type = "non-alcoholic";
        String serveStyle = "cold";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_NullType_ThrowsIllegalArgumentException() {
        // Arrange
        String name = "Coca Cola";
        String type = null;
        String serveStyle = "cold";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkConstructor_NullServeStyle_ThrowsIllegalArgumentException() {
        // Arrange
        String name = "Coca Cola";
        String type = "non-alcoholic";
        String serveStyle = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Drink(name, type, serveStyle);
        });
    }

    @Test
    public void testDrinkOf_ValidResultSet_Success() throws SQLException {
        // Arrange
        ResultSet resultSet = createMockResultSet("Coca Cola", "non-alcoholic", "cold");

        // Act
        Drink drink = Drink.of(resultSet);

        // Assert
        Assertions.assertEquals("Coca Cola", drink.name());
        Assertions.assertEquals("non-alcoholic", drink.type());
        Assertions.assertEquals("cold", drink.serveStyle());
    }

    // Helper method to create a mock ResultSet
    private ResultSet createMockResultSet(String name, String type, String serveStyle) throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getString("type")).thenReturn(type);
        when(resultSet.getString("serve_style")).thenReturn(serveStyle);
        return resultSet;
    }
}