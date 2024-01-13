package bg.restaurant.systems.software.integration.design.restaurant.exceptions;

public class RecipeNotFoundException extends Exception {
    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}