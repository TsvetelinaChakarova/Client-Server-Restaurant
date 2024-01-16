package bg.restaurant.systems.software.integration.design.data.allergen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllergenTest {

    @Test
    public void testAllergenConstructorValidName() {
        String validName = "Peanuts";

        Allergen allergen = new Allergen(validName);

        Assertions.assertEquals(validName, allergen.name());
    }

    @Test
    public void testAllergenConstructorNullName() {
        String nullName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(nullName);
        });
    }

    @Test
    public void testAllergenConstructorEmptyName() {
        String emptyName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(emptyName);
        });
    }

    @Test
    public void testAllergenConstructorBlankName() {
        String blankName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Allergen(blankName);
        });
    }
}