package bg.restaurant.systems.software.integration.design.data.serve;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServeStyleTest {

    @Test
    public void testGetTypeString() {
        ServeStyle serveStyle = ServeStyle.HOT;

        String typeString = serveStyle.getTypeString();

        assertEquals("hot", typeString);
    }

    @Test
    public void testGetType_ExistingServeStyle() {
        String serveStyleString = "cold";

        ServeStyle serveStyle = ServeStyle.getType(serveStyleString);

        assertEquals(ServeStyle.COLD, serveStyle);
    }

    @Test
    public void testGetType_UnknownServeStyle() {
        String serveStyleString = "warm";

        ServeStyle serveStyle = ServeStyle.getType(serveStyleString);

        assertEquals(ServeStyle.UNKNOWN, serveStyle);
    }
}