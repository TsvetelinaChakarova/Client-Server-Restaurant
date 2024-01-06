package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.Cocktail;
import bg.restaurant.systems.software.integration.design.storage.exceptions.CocktailAlreadyExistsException;

import java.util.Set;

public abstract class CocktailStorageValidator {
    void validateForExistingCocktail(Cocktail cocktail, Set<Cocktail> cocktailSet) throws
        CocktailAlreadyExistsException {
        if (cocktailSet.contains(cocktail)) {
            throw new CocktailAlreadyExistsException("The cocktail you want to add is already existing!");
        }
    }

    void validateCocktailForNull(Cocktail cocktail) {
        if (cocktail == null) {
            throw new IllegalArgumentException("The cocktail cannot be null!");
        }
    }

    void validateIngredientString(String ingredientName) {
        if (ingredientName == null || ingredientName.isEmpty() || ingredientName.isBlank()) {
            throw new IllegalArgumentException("The ingredient name cannot be null, empty or blank!");
        }
    }
}