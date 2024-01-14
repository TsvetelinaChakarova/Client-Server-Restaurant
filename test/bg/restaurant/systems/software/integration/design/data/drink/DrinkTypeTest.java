package bg.restaurant.systems.software.integration.design.data.drink;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrinkTypeTest {

    @Test
    public void getDrinkTypeTest() {
        // Arrange
        String drinkTypeString = "coffee";

        // Act
        DrinkType drinkType = DrinkType.getDrinkType(drinkTypeString);

        // Assert
        assertEquals(DrinkType.COFFEE, drinkType);
    }
}