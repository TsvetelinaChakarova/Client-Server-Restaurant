package bg.restaurant.systems.software.integration.design.data.allergen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllergenTest {

    @Test
    public void testAllergenConstructor_ValidName() {
        // Arrange
        String validName = "Peanuts";

        // Act
        Allergen allergen = new Allergen(validName);

        // Assert
        Assertions.assertEquals(validName, allergen.name());
    }

    @Test
    public void testAllergenConstructor_NullName() {
        // Arrange
        String nullName = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(nullName);
        });
    }

    @Test
    public void testAllergenConstructor_EmptyName() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(emptyName);
        });
    }

    @Test
    public void testAllergenConstructor_BlankName() {
        // Arrange
        String blankName = "   ";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(blankName);
        });
    }
}