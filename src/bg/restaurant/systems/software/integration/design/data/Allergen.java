package bg.restaurant.systems.software.integration.design.data;

public record Allergen(String name) {
    public Allergen {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }
    }
}