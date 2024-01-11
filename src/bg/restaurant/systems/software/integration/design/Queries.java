package bg.restaurant.systems.software.integration.design;

import bg.restaurant.systems.software.integration.design.data.Allergen;
import bg.restaurant.systems.software.integration.design.data.Drink;
import bg.restaurant.systems.software.integration.design.data.DrinkType;
import bg.restaurant.systems.software.integration.design.data.Ingredient;
import bg.restaurant.systems.software.integration.design.data.Recipe;
import bg.restaurant.systems.software.integration.design.data.ServeStyle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Queries {
    private final DatabaseConnection databaseConnection;

    public Queries(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private StringBuilder makeCollectionStringForQuery(Collection<String> elements) {
        StringBuilder elementsForQuery = new StringBuilder();

        int counter = 0;
        for (String element : elements) {
            elementsForQuery.append("\"").append(element).append("\"");
            if (counter != elements.size() - 1) {
                elementsForQuery.append(",");
            }
            counter++;
        }

        return elementsForQuery;
    }

    public void getAllRecipes() {
        ResultSet resultSet = databaseConnection.executeQuery(
                "SELECT * FROM recipes");
    }

    public void getAllRecipesByAllergens(Collection<String> allergens) {
        StringBuilder query = new StringBuilder("""
                SELECT * FROM recipes
                WHERE id IN (
                        SELECT recipe_id FROM allergens_for_recipe
                        WHERE allergen_id IN (
                                SELECT id FROM allergens
                                WHERE name IN (
                                                """);

        query.append(makeCollectionStringForQuery(allergens));
        query.append(")))");

        ResultSet resultSet = databaseConnection.executeQuery(query.toString());

//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("name"));
//        }
    }

    public void getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException {
        StringBuilder query = new StringBuilder("""
                SELECT * FROM recipes
                WHERE id IN (
                        SELECT recipe_id FROM ingredients_for_recipe
                        WHERE ingredient_id IN (
                                SELECT id FROM ingredients
                                WHERE name IN (
                                                """);

        query.append(makeCollectionStringForQuery(ingredients));
        query.append(")))");

        ResultSet resultSet = databaseConnection.executeQuery(query.toString());
    }

//    public List<Recipe> getAllRecipesByType(Collection<String> types) throws SQLException {
//        StringBuilder query = new StringBuilder("""
//                SELECT * FROM recipes
//                WHERE type IN (
//                                """);
//
//        query.append(makeCollectionStringForQuery(types));
//        query.append(")");
//
//        ResultSet resultSet = databaseConnection.executeQuery(query.toString());
//
//        List<Recipe> recipes = new ArrayList<>();
//        while (resultSet.next()) {
//            recipes.add(new Recipe(resultSet.getString("name"),
//                    DrinkType.getDrinkType(resultSet.getString("type")),
//                    ServeStyle.getType(resultSet.getString("serve_style"))));
//        }
//
//        return recipes;
//    }

    public ServeStyle getServingWayByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT serve_style FROM recipes
                WHERE name = \"""" +
                recipeName + "\"");

        ServeStyle serveStyle = null;
        while (resultSet.next()) {
            serveStyle = ServeStyle.getType(resultSet.getString("serve_style"));
        }

        return serveStyle;
    }

    public int getPreparationTimeForRecipeByName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT preparation_time FROM recipes
                WHERE name = \"""" +
                recipeName + "\"");

        int preparationTime = 0;
        while (resultSet.next()) {
            preparationTime = resultSet.getInt("preparation_time");
        }

        return preparationTime;
    }

    public List<Drink> getAllDrinks() throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("select * from drinks");

        List<Drink> drinks = new ArrayList<>();
        while (resultSet.next()) {
            drinks.add(Drink.of(resultSet));
        }

        return drinks;
    }

    public List<Drink> getAllDrinksByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM drinks
                WHERE id = (
                        SELECT drink_id FROM drinks_for_recipe
                        WHERE recipe_id = (
                            SELECT id FROM recipes
                            WHERE name = \"""" +
                recipeName + "\"))");

        List<Drink> drinks = new ArrayList<>();
        while (resultSet.next()) {
            drinks.add(Drink.of(resultSet));
        }

        return drinks;
    }

    public List<Allergen> getAllergensByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM allergens
                WHERE id IN (
                        SELECT allergen_id FROM allergens_for_recipe
                        WHERE recipe_id IN (
                                SELECT id FROM recipes
                                WHERE name = \"""" +
                recipeName + "\"))");

        List<Allergen> allergens = new ArrayList<>();
        while (resultSet.next()) {
            allergens.add(new Allergen(resultSet.getString("name")));
        }

        return allergens;
    }

    public List<Ingredient> getIngredientsByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM ingredients
                WHERE id IN (
                        SELECT ingredient_id FROM ingredients_for_recipe
                        WHERE recipe_id IN (
                                SELECT id FROM recipes
                                WHERE name = \"""" +
                recipeName + "\"))");

        List<Ingredient> ingredients = new ArrayList<>();
        while (resultSet.next()) {
            ingredients.add(new Ingredient(resultSet.getString("name")));
        }

        return ingredients;
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/restaurants", "root", "");

        Queries queries = new Queries(databaseConnection);
        System.out.println(queries.getServingWayByRecipeName("cake"));
        System.out.println(queries.getPreparationTimeForRecipeByName("cake"));
        System.out.println(queries.getIngredientsByRecipeName("cake"));
        System.out.println(queries.getAllergensByRecipeName("cake"));
        System.out.println(queries.getAllDrinksByRecipeName("cake"));
        System.out.println(queries.getAllDrinks());
    }
}
