package bg.restaurant.systems.software.integration.design.data.serve;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServeStyleTest {

    @Test
    public void testGetTypeString() {
        // Arrange
        ServeStyle serveStyle = ServeStyle.HOT;

        // Act
        String typeString = serveStyle.getTypeString();

        // Assert
        assertEquals("hot", typeString);
    }

    @Test
    public void testGetType_ExistingServeStyle() {
        // Arrange
        String serveStyleString = "cold";

        // Act
        ServeStyle serveStyle = ServeStyle.getType(serveStyleString);

        // Assert
        assertEquals(ServeStyle.COLD, serveStyle);
    }

    @Test
    public void testGetType_UnknownServeStyle() {
        // Arrange
        String serveStyleString = "warm";

        // Act
        ServeStyle serveStyle = ServeStyle.getType(serveStyleString);

        // Assert
        assertEquals(ServeStyle.UNKNOWN, serveStyle);
    }
}