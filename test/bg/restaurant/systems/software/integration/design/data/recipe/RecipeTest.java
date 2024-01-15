package bg.restaurant.systems.software.integration.design.data.recipe;

import bg.restaurant.systems.software.integration.design.data.allergen.Allergen;
import bg.restaurant.systems.software.integration.design.data.drink.Drink;
import bg.restaurant.systems.software.integration.design.data.ingredient.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


public class RecipeTest {

    @Test
    public void testEmptyName() {
        // Arrange
        String name = "";
        String type = "Italian";
        String serveStyle = "Plate";
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Pasta"));
        ingredients.add(new Ingredient("Bacon"));
        int preparationTime = 30;
        Set<Allergen> allergens = new HashSet<>();
        allergens.add(new Allergen("Gluten"));
        Set<Drink> suitableDrinks = new HashSet<>();
        suitableDrinks.add(new Drink("Red Wine", "alcohol", "cold"));

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Recipe(name, type, serveStyle, ingredients, preparationTime, allergens, suitableDrinks);
        });
    }

    @Test
    public void testNullType() {
        // Arrange
        String name = "Pasta Carbonara";
        String type = null;
        String serveStyle = "Plate";
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Pasta"));
        ingredients.add(new Ingredient("Bacon"));
        int preparationTime = 30;
        Set<Allergen> allergens = new HashSet<>();
        allergens.add(new Allergen("Gluten"));
        Set<Drink> suitableDrinks = new HashSet<>();
        suitableDrinks.add(new Drink("Red Wine", "alcohol", "cold"));

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Recipe(name, type, serveStyle, ingredients, preparationTime, allergens, suitableDrinks);
        });
    }

    @Test
    public void testNullServeStyle() {
        // Arrange
        String name = "Pasta Carbonara";
        String type = "Italian";
        String serveStyle = null;
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Pasta"));
        ingredients.add(new Ingredient("Bacon"));
        int preparationTime = 30;
        Set<Allergen> allergens = new HashSet<>();
        allergens.add(new Allergen("Gluten"));
        Set<Drink> suitableDrinks = new HashSet<>();
        suitableDrinks.add(new Drink("Red Wine", "alcohol", "cold"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Recipe(name, type, serveStyle, ingredients, preparationTime, allergens, suitableDrinks);
        });
    }

    @Test
    public void testEmptyIngredients() {
        String name = "Pasta Carbonara";
        String type = "Italian";
        String serveStyle = "Plate";
        Set<Ingredient> ingredients = new HashSet<>();
        int preparationTime = 30;
        Set<Allergen> allergens = new HashSet<>();
        allergens.add(new Allergen("Gluten"));
        Set<Drink> suitableDrinks = new HashSet<>();
        suitableDrinks.add(new Drink("Red Wine", "alcohol", "cold"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Recipe(name, type, serveStyle, ingredients, preparationTime, allergens, suitableDrinks);
        });
    }

    @Test
    public void testNegativePreparationTime() {
        String name = "Pasta Carbonara";
        String type = "Italian";
        String serveStyle = "Plate";
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Pasta"));
        ingredients.add(new Ingredient("Bacon"));
        int preparationTime = -30;
        Set<Allergen> allergens = new HashSet<>();
        allergens.add(new Allergen("Gluten"));
        Set<Drink> suitableDrinks = new HashSet<>();
        suitableDrinks.add(new Drink("Red Wine", "alcohol", "cold"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Recipe(name, type, serveStyle, ingredients, preparationTime, allergens, suitableDrinks);
        });
    }
}