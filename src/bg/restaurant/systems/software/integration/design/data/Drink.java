package bg.restaurant.systems.software.integration.design.data;

public record Drink(String name, DrinkType type, ServeStyle serveStyle) {
    public Drink {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }

        if (type == null) {
            throw new IllegalArgumentException("The type cannot be null!");
        }

        if (serveStyle == null) {
            throw new IllegalArgumentException("The serveStyle cannot be null!");
        }
    }
}