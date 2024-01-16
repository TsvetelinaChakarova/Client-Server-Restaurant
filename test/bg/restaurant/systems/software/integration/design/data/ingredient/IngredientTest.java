package bg.restaurant.systems.software.integration.design.data.ingredient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void testIngredientCreation_ValidName() {
        String validName = "Tomato";
        Ingredient ingredient = new Ingredient(validName);

        Assertions.assertEquals(validName, ingredient.name());
    }

    @Test
    public void testIngredientCreation_NullName() {
        String nullName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(nullName);
        });
    }

    @Test
    public void testIngredientCreation_EmptyName() {
        String emptyName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(emptyName);
        });
    }

    @Test
    public void testIngredientCreation_BlankName() {
        String blankName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(blankName);
        });
    }
}