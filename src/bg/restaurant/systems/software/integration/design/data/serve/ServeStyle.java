package bg.restaurant.systems.software.integration.design.data.serve;

import java.util.Arrays;

public enum ServeStyle {
    HOT("hot"),
    COLD("cold"),
    UNKNOWN("unknown");

    private final String serveStyle;

    ServeStyle(String serveStyle) {
        this.serveStyle = serveStyle;
    }

    public String getTypeString() {
        return serveStyle;
    }

    public static ServeStyle getType(String serveStyle) {
        return Arrays.stream(values())
            .filter(c -> c.serveStyle.equalsIgnoreCase(serveStyle))
            .findFirst()
            .orElse(UNKNOWN);
    }
}