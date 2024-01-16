package bg.restaurant.systems.software.integration.design.data.drink;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrinkTypeTest {

    @Test
    public void getDrinkTypeTest() {
        String drinkTypeString = "coffee";

        DrinkType drinkType = DrinkType.getDrinkType(drinkTypeString);

        assertEquals(DrinkType.COFFEE, drinkType);
    }
}