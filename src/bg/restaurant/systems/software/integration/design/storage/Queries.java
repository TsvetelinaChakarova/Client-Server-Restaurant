package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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
        ResultSet resultSet = databaseConnection.executeQuery("SELECT * FROM recipes");
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

    public void getAllRecipesByType(Collection<String> types) throws SQLException {
        StringBuilder query = new StringBuilder("""
                SELECT * FROM recipes
                WHERE type IN (
                                """);

        query.append(makeCollectionStringForQuery(types));
        query.append(")");

        ResultSet resultSet = databaseConnection.executeQuery(query.toString());

//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("name"));
//        }
    }

    public void getServingWayByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("SELECT serve_style FROM recipes WHERE name = \"" + recipeName + "\"");

//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("serve_style"));
//        }
    }

    public void getAllDrinks() {
        databaseConnection.executeQuery("select * from drinks");
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/restaurants", "root", "");

        Queries queries = new Queries(databaseConnection);
        queries.getAllRecipesByType(List.of("dessert", "breakfast"));
        System.out.println();
        queries.getServingWayByRecipeName("cake");

    }
}
