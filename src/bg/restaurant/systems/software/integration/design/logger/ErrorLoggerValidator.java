package bg.restaurant.systems.software.integration.design.logger;

public abstract class ErrorLoggerValidator {
    void validateErrorLogsPath(String path) {
        if (path == null || path.isEmpty() || path.isBlank()) {
            throw new IllegalArgumentException("The user database path cannot be null, empty or blank!");
        }
    }

    void validateException(Exception e) {
        if (e == null) {
            throw new IllegalArgumentException("The exception passed cannot be null!");
        }
    }
}