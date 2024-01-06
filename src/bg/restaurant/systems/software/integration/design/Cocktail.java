package bg.restaurant.systems.software.integration.design;

import java.util.Set;
public record Cocktail(String name, Set<Ingredient> ingredients) {
}
