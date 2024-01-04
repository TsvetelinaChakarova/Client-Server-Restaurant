package bg.sofia.uni.fmi.mjt.cocktail.server.storage;

import bg.sofia.uni.fmi.mjt.cocktail.server.Cocktail;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCocktailStorage extends CocktailStorageValidator implements CocktailStorage {
    private final Set<Cocktail> cocktails;

    public DefaultCocktailStorage() {
        this.cocktails = new HashSet<>();
    }

    @Override
    public void createCocktail(Cocktail cocktail) throws CocktailAlreadyExistsException {
        validateCocktailForNull(cocktail);
        validateForExistingCocktail(cocktail, cocktails);
        cocktails.add(cocktail);
    }

    @Override
    public Collection<Cocktail> getCocktails() {
        return cocktails;
    }

    @Override
    public Collection<Cocktail> getCocktailsWithIngredient(String ingredientName) {
        validateIngredientString(ingredientName);
        return cocktails.stream()
            .filter(c -> c.ingredients().stream()
                .anyMatch(i -> i.name().equalsIgnoreCase(ingredientName)))
            .collect(Collectors.toSet());
    }

    @Override
    public Cocktail getCocktail(String name) throws CocktailNotFoundException {
        validateIngredientString(name);

        return cocktails.stream()
            .filter(c -> c.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new CocktailNotFoundException("There is no such cocktail with this name!"));
    }
}