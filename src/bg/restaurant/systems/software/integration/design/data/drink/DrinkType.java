package bg.restaurant.systems.software.integration.design.data.drink;

import java.util.Arrays;

public enum DrinkType {
    ALCOHOL("alcohol"),
    COFFEE("coffee"),
    ALCOHOL_FREE("non-alcoholic"),
    UNKNOWN("unknown");

    private final String drinkType;

    DrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getDrinkTypeString() {
        return drinkType;
    }

    public static DrinkType getDrinkType(String drinkType) {
        return Arrays.stream(values())
            .filter(c -> c.drinkType.equalsIgnoreCase(drinkType))
            .findFirst()
            .orElse(UNKNOWN);
    }
}