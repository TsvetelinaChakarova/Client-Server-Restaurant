package bg.restaurant.systems.software.integration.design.data;

public record Ingredient(String name) {
    public Ingredient {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }
    }
}