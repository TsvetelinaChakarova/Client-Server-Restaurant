package bg.restaurant.systems.software.integration.design;

import java.util.Set;
public record Recipe(String name, Set<Ingredient> ingredients) {
}
