package bg.restaurant.systems.software.integration.design.data.ingredient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void testIngredientCreationValidName() {
        String validName = "Tomato";
        Ingredient ingredient = new Ingredient(validName);

        Assertions.assertEquals(validName, ingredient.name());
    }

    @Test
    public void testIngredientCreationNullName() {
        String nullName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(nullName);
        });
    }

    @Test
    public void testIngredientCreationEmptyName() {
        String emptyName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(emptyName);
        });
    }

    @Test
    public void testIngredientCreationBlankName() {
        String blankName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(blankName);
        });
    }
}