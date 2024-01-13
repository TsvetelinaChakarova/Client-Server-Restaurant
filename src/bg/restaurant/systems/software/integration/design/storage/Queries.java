package bg.restaurant.systems.software.integration.design.storage;

import bg.restaurant.systems.software.integration.design.data.allergen.Allergen;
import bg.restaurant.systems.software.integration.design.data.drink.Drink;
import bg.restaurant.systems.software.integration.design.data.ingredient.Ingredient;
import bg.restaurant.systems.software.integration.design.data.recipe.Recipe;
import bg.restaurant.systems.software.integration.design.data.recipe.RecipeType;
import bg.restaurant.systems.software.integration.design.data.serve.ServeStyle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Queries {
    private final DatabaseConnection databaseConnection;
    private static final String TYPE = "type";
    private static final String SERVER_STYLE = "serve_style";
    private static final String PREPARATION_TIME = "preparation_time";

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

    private Set<Recipe> getRecipesForQueries(ResultSet resultSet) throws SQLException {
        Set<String> recipeNames = new HashSet<>();
        while (resultSet.next()) {
            recipeNames.add(resultSet.getString("name"));
        }
        resultSet.close();

        Set<Recipe> recipes = new HashSet<>();
        for (String recipeName : recipeNames) {
            resultSet = databaseConnection.executeQuery("""
                    SELECT * FROM recipes
                    WHERE name = \"""" +
                    recipeName + "\"");

            resultSet.next();

            RecipeType type = RecipeType.getRecipeType(resultSet.getString(TYPE));
            ServeStyle serveStyle = ServeStyle.getType(resultSet.getString(SERVER_STYLE));
            int preparationTime = resultSet.getInt(PREPARATION_TIME);

            recipes.add(new Recipe(recipeName,
                    type.getRecipeTypeString(),
                    serveStyle.getTypeString(),
                    getIngredientsByRecipeName(recipeName),
                    preparationTime,
                    getAllergensByRecipeName(recipeName),
                    getAllDrinksByRecipeName(recipeName)));
        }

        resultSet.close();
        return recipes;
    }

    public Set<Recipe> getRecipeByName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT * FROM recipes
                WHERE name = \"""" +
                recipeName + "\"");

        return getRecipesForQueries(resultSet);
    }

    public Set<Recipe> getAllRecipes() throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery(
                "SELECT * FROM recipes");

        return getRecipesForQueries(resultSet);
    }

    public Set<Recipe> getAllRecipesByAllergens(Collection<String> allergens) throws SQLException {
        String query = """
                SELECT * FROM recipes
                WHERE id IN (
                        SELECT recipe_id FROM allergens_for_recipe
                        WHERE allergen_id IN (
                                SELECT id FROM allergens
                                WHERE name IN (
                                                """ + makeCollectionStringForQuery(allergens) +
                ")))";

        ResultSet resultSet = databaseConnection.executeQuery(query);

        return getRecipesForQueries(resultSet);
    }

    public Set<Recipe> getAllRecipesByIngredients(Collection<String> ingredients) throws SQLException {
        String query = """
                SELECT * FROM recipes
                WHERE id IN (
                        SELECT recipe_id FROM ingredients_for_recipe
                        WHERE ingredient_id IN (
                                SELECT id FROM ingredients
                                WHERE name IN (
                                                """ + makeCollectionStringForQuery(ingredients) +
                ")))";

        ResultSet resultSet = databaseConnection.executeQuery(String.valueOf(query));

        return getRecipesForQueries(resultSet);
    }

    public Set<Recipe> getAllRecipesByType(Collection<String> types) throws SQLException {
        String query = """
                SELECT * FROM recipes
                WHERE type IN (
                                """ + makeCollectionStringForQuery(types) +
                ")";

        ResultSet resultSet = databaseConnection.executeQuery(query);

        return getRecipesForQueries(resultSet);
    }

    public ServeStyle getServingWayByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT serve_style FROM recipes
                WHERE name = \"""" +
                recipeName + "\"");

        ServeStyle serveStyle = null;
        while (resultSet.next()) {
            serveStyle = ServeStyle.getType(resultSet.getString(SERVER_STYLE));
        }

        resultSet.close();
        return serveStyle;
    }

    public int getPreparationTimeForRecipeByName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT preparation_time FROM recipes
                WHERE name = \"""" +
                recipeName + "\"");

        int preparationTime = 0;
        while (resultSet.next()) {
            preparationTime = resultSet.getInt(PREPARATION_TIME);
        }

        resultSet.close();
        return preparationTime;
    }

    public Set<Drink> getAllDrinks() throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("select * from drinks");

        Set<Drink> drinks = new HashSet<>();
        while (resultSet.next()) {
            drinks.add(Drink.of(resultSet));
        }

        resultSet.close();
        return drinks;
    }

    public Set<Drink> getAllDrinksByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM drinks
                WHERE id = (
                        SELECT drink_id FROM drinks_for_recipe
                        WHERE recipe_id = (
                            SELECT id FROM recipes
                            WHERE name = \"""" +
                recipeName + "\"))");

        Set<Drink> drinks = new HashSet<>();
        while (resultSet.next()) {
            drinks.add(Drink.of(resultSet));
        }

        resultSet.close();
        return drinks;
    }

    public Set<Allergen> getAllergensByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM allergens
                WHERE id IN (
                        SELECT allergen_id FROM allergens_for_recipe
                        WHERE recipe_id IN (
                                SELECT id FROM recipes
                                WHERE name = \"""" +
                recipeName + "\"))");

        Set<Allergen> allergens = new HashSet<>();
        while (resultSet.next()) {
            allergens.add(new Allergen(resultSet.getString("name")));
        }

        resultSet.close();
        return allergens;
    }

    public Set<Ingredient> getIngredientsByRecipeName(String recipeName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM ingredients
                WHERE id IN (
                        SELECT ingredient_id FROM ingredients_for_recipe
                        WHERE recipe_id IN (
                                SELECT id FROM recipes
                                WHERE name = \"""" +
                recipeName + "\"))");

        Set<Ingredient> ingredients = new HashSet<>();
        while (resultSet.next()) {
            ingredients.add(new Ingredient(resultSet.getString("name")));
        }

        resultSet.close();
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
        System.out.println(queries.getAllRecipesByType(Set.of("breakfast")));
        System.out.println();
        System.out.println(queries.getAllRecipesByIngredients(Set.of("flour", "eggs")));
        System.out.println();
        System.out.println(queries.getAllRecipes());
        System.out.println();
        System.out.println(queries.getAllRecipesByAllergens(Set.of("gluten")));
    }
}
