package bg.restaurant.systems.software.integration.design.data.allergen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllergenTest {

    @Test
    public void testAllergenConstructor_ValidName() {
        String validName = "Peanuts";

        Allergen allergen = new Allergen(validName);

        Assertions.assertEquals(validName, allergen.name());
    }

    @Test
    public void testAllergenConstructor_NullName() {
        String nullName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(nullName);
        });
    }

    @Test
    public void testAllergenConstructor_EmptyName() {
        String emptyName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(emptyName);
        });
    }

    @Test
    public void testAllergenConstructor_BlankName() {
        String blankName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(blankName);
        });
    }
}