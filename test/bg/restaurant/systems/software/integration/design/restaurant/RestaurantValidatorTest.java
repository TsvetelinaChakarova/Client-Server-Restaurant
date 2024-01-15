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
        restaurantValidator = new RestaurantValidator();
        recipeSet = new HashSet<>();
    }

    @Test
    public void testValidateForExistingRecipe_RecipeExists_ThrowsException() {
        Recipe existingRecipe = new Recipe("Pizza","main", "hot", Set.of(new Ingredient("tomato")), 30, Set.of(new Allergen("eggs")),Set.of(new Drink("coke", "fizzy", "cold")));
        recipeSet.add(existingRecipe);

        Assertions.assertThrows(RecipeAlreadyExistsException.class, () -> {
            restaurantValidator.validateForExistingRecipe(existingRecipe, recipeSet);
        });
    }

    @Test
    public void testValidateForExistingRecipe_RecipeDoesNotExist_NoExceptionThrown() {
        Recipe newRecipe = new Recipe("Pizza","main", "hot", Set.of(new Ingredient("tomato")), 30, Set.of(new Allergen("eggs")),Set.of(new Drink("coke", "fizzy", "cold")));

        Assertions.assertDoesNotThrow(() -> {
            restaurantValidator.validateForExistingRecipe(newRecipe, recipeSet);
        });
    }

    @Test
    public void testValidateRecipeForNull_NullRecipe_ThrowsException() {
        Recipe nullRecipe = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateRecipeForNull(nullRecipe);
        });
    }

    @Test
    public void testValidateRecipeForNull_NonNullRecipe_NoExceptionThrown() {
        Recipe nonNullRecipe = new Recipe("Burger","main", "hot", Set.of(new Ingredient("meat")), 30, Set.of(new Allergen("eggs")),Set.of(new Drink("coke", "fizzy", "cold")));

        Assertions.assertDoesNotThrow(() -> {
            restaurantValidator.validateRecipeForNull(nonNullRecipe);
        });
    }

    @Test
    public void testValidateIngredientString_NullIngredientName_ThrowsException() {
        String nullIngredientName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateIngredientString(nullIngredientName);
        });
    }

    @Test
    public void testValidateIngredientString_EmptyIngredientName_ThrowsException() {
        String emptyIngredientName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateIngredientString(emptyIngredientName);
        });
    }

    @Test
    public void testValidateIngredientString_BlankIngredientName_ThrowsException() {
        String blankIngredientName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantValidator.validateIngredientString(blankIngredientName);
        });
    }

    @Test
    public void testValidateIngredientString_ValidIngredientName_NoExceptionThrown() {
        String validIngredientName = "Tomato";

        Assertions.assertDoesNotThrow(() -> {
            restaurantValidator.validateIngredientString(validIngredientName);
        });
    }
}