package bg.restaurant.systems.software.integration.design.data.drink;

import bg.restaurant.systems.software.integration.design.data.serve.ServeStyle;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Drink(String name, String type, String serveStyle) {
    public Drink {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }

        if (type == null) {
            throw new IllegalArgumentException("The type cannot be null!");
        }

        if (serveStyle == null) {
            throw new IllegalArgumentException("The serveStyle cannot be null!");
        }
    }

    public static Drink of(ResultSet resultSet) throws SQLException {
        return new Drink(resultSet.getString("name"),
            DrinkType.getDrinkType(resultSet.getString("type")).getDrinkTypeString(),
            ServeStyle.getType(resultSet.getString("serve_style")).getTypeString());
    }
}