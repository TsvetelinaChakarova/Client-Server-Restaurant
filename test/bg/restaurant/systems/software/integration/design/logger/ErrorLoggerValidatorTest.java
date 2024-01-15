package bg.restaurant.systems.software.integration.design.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorLoggerValidatorTest {

    private ErrorLoggerValidator errorLoggerValidator;

    @BeforeEach
    public void setUp() {
        errorLoggerValidator = new ErrorLoggerValidator() {};
    }

    @Test
    public void validateErrorLogsPath_shouldThrowExceptionWhenPathIsNull() {
        String path = null;

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateErrorLogsPath_shouldThrowExceptionWhenPathIsEmpty() {
        String path = "";

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateErrorLogsPath_shouldThrowExceptionWhenPathIsBlank() {
        String path = "   ";

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateErrorLogsPath_shouldNotThrowExceptionWhenPathIsValid() {
        String path = "/path/to/error/logs.txt";

        assertDoesNotThrow(() -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateException_shouldThrowExceptionWhenExceptionIsNull() {
        Exception exception = null;

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateException(exception));
    }

    @Test
    public void validateException_shouldNotThrowExceptionWhenExceptionIsNotNull() {
        Exception exception = new Exception("Test Exception");

        assertDoesNotThrow(() -> errorLoggerValidator.validateException(exception));
    }
}