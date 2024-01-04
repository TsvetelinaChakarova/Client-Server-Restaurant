package bg.sofia.uni.fmi.mjt.cocktail.server.command;

import bg.sofia.uni.fmi.mjt.cocktail.server.Cocktail;
import bg.sofia.uni.fmi.mjt.cocktail.server.Ingredient;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.CocktailStorage;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CommandExecutor extends CommandsCreatorValidator {
    private static final String CREATE = "create";
    private static final String GET = "get";
    private static final String ALL = "all";
    private static final String BY_NAME = "by-name";
    private static final String BY_INGREDIENT = "by-ingredient";
    private final CocktailStorage cocktailStorage;

    public CommandExecutor(CocktailStorage storage) {
        this.cocktailStorage = storage;
    }

    public String execute(Command cmd) {
        return switch (cmd.command()) {
            case CREATE -> createCommand(cmd.arguments());
            case GET -> getCommand(cmd.arguments());
            default -> "Unknown command";
        };
    }


    private String createCommand(String[] args) {
        String cocktailName = args[0];
        Cocktail cocktail = new Cocktail(cocktailName, fillIngredientsSet(args));

        try {
            cocktailStorage.createCocktail(cocktail);
            return "{\"status\":\"CREATED\"}";
        } catch (CocktailAlreadyExistsException e) {
            return "{\"status\":\"ERROR\",\"errorMessage\":\"" + e.getMessage() + "\"}";
        }
    }

    private Set<Ingredient> fillIngredientsSet(String[] args) {
        Set<Ingredient> ingredients = new HashSet<>();

        for (int i = 1; i < args.length; i++) {
            ingredients.add(getIngredientFromArguments(args[i]));
        }

        return ingredients;
    }

    private Ingredient getIngredientFromArguments(String ingredientString) {
        String splitString = "=";
        String[] ingredientArgs = ingredientString.split(splitString);

        return new Ingredient(ingredientArgs[0], ingredientArgs[1]);
    }


    public String getCommand(String[] args) {
        return switch (args[0]) {
            case ALL -> getAll();
            case BY_NAME -> getByName(args);
            case BY_INGREDIENT -> getByIngredient(args);
            default -> "Unknown command";
        };
    }

    private String getAll() {
        Cocktail[] cocktails = cocktailStorage.getCocktails().stream()
            .map(c -> new Cocktail(c.name(), c.ingredients()))
            .toArray(Cocktail[]::new);

        return getJsonString(cocktails);
    }

    private String getJsonString(Cocktail... cocktails) {
        StringBuilder response = new StringBuilder();
        String responseStart = "{\"status\":\"OK\",\"cocktails\":[";
        String responseEnd = "]}";

        response.append(responseStart);

        if (cocktails.length != 0) {
            response.append("{");
        }

        for (Cocktail cocktail : cocktails) {
            response.append("\"name\":");
            response.append("\"").append(cocktail.name()).append("\"");
            response.append(",");
            response.append("\"ingredients\":");
            response.append(getIngredientsList(cocktail.ingredients()));
        }

        if (cocktails.length != 0) {
            response.append("}");
        }

        response.append(responseEnd);

        return response.toString();
    }

    private String getIngredientsList(Set<Ingredient> ingredients) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        if (!ingredients.isEmpty()) {
            stringBuilder.append("{");
        }

        for (Ingredient ingredient : ingredients) {
            stringBuilder.append("\"name\":");
            stringBuilder.append("\"").append(ingredient.name()).append("\"");
            stringBuilder.append(",");
            stringBuilder.append("\"amount\":");
            stringBuilder.append("\"").append(ingredient.amount()).append("\"");
        }

        if (!ingredients.isEmpty()) {
            stringBuilder.append("}");
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private String getByName(String[] args) {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException("Invalid number of arguments!");
        }

        try {
            return getJsonString(cocktailStorage.getCocktail(args[1]));
        } catch (CocktailNotFoundException e) {
            return "{\"status\":\"ERROR\",\"errorMessage\":\"" + e.getMessage() + "\"}";
        }

    }

    private String getByIngredient(String[] args) {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException("Invalid number of arguments!");
        }

        Cocktail[] cocktails = cocktailStorage.getCocktailsWithIngredient(args[1]).stream()
            .map(c -> new Cocktail(c.name(), c.ingredients()))
            .toArray(Cocktail[]::new);

        return getJsonString(cocktails);
    }
}