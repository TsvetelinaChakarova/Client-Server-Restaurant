package bg.restaurant.systems.software.integration.design.data.ingredient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void testIngredientCreation_ValidName() {
        // Arrange
        String validName = "Tomato";

        // Act
        Ingredient ingredient = new Ingredient(validName);

        // Assert
        Assertions.assertEquals(validName, ingredient.name());
    }

    @Test
    public void testIngredientCreation_NullName() {
        // Arrange
        String nullName = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(nullName);
        });
    }

    @Test
    public void testIngredientCreation_EmptyName() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(emptyName);
        });
    }

    @Test
    public void testIngredientCreation_BlankName() {
        // Arrange
        String blankName = "   ";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(blankName);
        });
    }
}