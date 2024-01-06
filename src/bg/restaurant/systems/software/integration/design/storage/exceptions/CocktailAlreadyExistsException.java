package bg.restaurant.systems.software.integration.design.storage.exceptions;

public class CocktailAlreadyExistsException extends Exception {
    public CocktailAlreadyExistsException(String message) {
        super(message);
    }

    public CocktailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}