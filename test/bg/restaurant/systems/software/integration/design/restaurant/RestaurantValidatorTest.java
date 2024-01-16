package bg.restaurant.systems.software.integration.design.restaurant;

import bg.restaurant.systems.software.integration.design.data.allergen.Allergen;
import bg.restaurant.systems.software.integration.design.data.drink.Drink;
import bg.restaurant.systems.software.integration.design.data.ingredient.Ingredient;
import bg.restaurant.systems.software.integration.design.data.recipe.Recipe;
import bg.restaurant.systems.software.integration.design.exceptions.RecipeAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class RestaurantValidatorTest {

    private RestaurantValidator restaurantValidator;
    private Set<Recipe> recipeSet;

    @BeforeEach
    public void setUp() {
        restaurantValidator = new RestaurantValidator() {};
        recipeSet = new HashSet<>();
    }

    @Test
    public void testValidateForExistingRecipeRecipeExistsThrowsException() {
        Recipe existingRecipe =
            new Recipe("Pizza", "main", "hot", Set.of(new Ingredient("tomato")), 30, Set.of(new Allergen("eggs")),
                Set.of(new Drink("coke", "fizzy", "cold")));
        recipeSet.add(existingRecipe);

        Assertions.assertThrows(RecipeAlreadyExistsException.class, () -> {
            restaurantValidator.validateForExistingRecipe(existingRecipe, recipeSet);
        });
    }

    @Test
    public void testValidateForExistingRecipeRecipeDoesNotExistNoExceptionThrown() {
        Recipe newRecipe =
            new Recipe("Pizza", "main", "hot", Set.of(new Ingredient("tomato")), 30, Set.of(new Allergen("eggs")),
                Set.of(new Drink("coke", "fizzy", "cold")));

        Assertions.assertDoesNotThrow(() -> {
            restaurantValidator.validateForExistingRecipe(newRecipe, recipeSet);
        });
    }

    @Test
    public void testValidateRecipeForNullNullRecipeThrowsException() {
        Recipe nullRecipe = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateRecipeForNull(nullRecipe);
        });
    }

    @Test
    public void testValidateRecipeForNullNonNullRecipeNoExceptionThrown() {
        Recipe nonNullRecipe =
            new Recipe("Burger", "main", "hot", Set.of(new Ingredient("meat")), 30, Set.of(new Allergen("eggs")),
                Set.of(new Drink("coke", "fizzy", "cold")));

        Assertions.assertDoesNotThrow(() -> {
            restaurantValidator.validateRecipeForNull(nonNullRecipe);
        });
    }

    @Test
    public void testValidateIngredientStringNullIngredientNameThrowsException() {
        String nullIngredientName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateIngredientString(nullIngredientName);
        });
    }

    @Test
    public void testValidateIngredientStringEmptyIngredientNameThrowsException() {
        String emptyIngredientName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateIngredientString(emptyIngredientName);
        });
    }

    @Test
    public void testValidateIngredientStringBlankIngredientNameThrowsException() {
        String blankIngredientName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateIngredientString(blankIngredientName);
        });
    }

    @Test
    public void testValidateIngredientStringValidIngredientNameNoExceptionThrown() {
        String validIngredientName = "Tomato";

        Assertions.assertDoesNotThrow(() -> {
            restaurantValidator.validateIngredientString(validIngredientName);
        });
    }
}