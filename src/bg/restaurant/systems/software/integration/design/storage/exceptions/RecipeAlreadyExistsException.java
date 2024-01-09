package bg.restaurant.systems.software.integration.design.storage.exceptions;

public class RecipeAlreadyExistsException extends Exception {
    public RecipeAlreadyExistsException(String message) {
        super(message);
    }

    public RecipeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}