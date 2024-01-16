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
    public void validateErrorLogsPathShouldThrowExceptionWhenPathIsNull() {
        String path = null;

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateErrorLogsPathShouldThrowExceptionWhenPathIsEmpty() {
        String path = "";

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateErrorLogsPathShouldThrowExceptionWhenPathIsBlank() {
        String path = "   ";

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateErrorLogsPathShouldNotThrowExceptionWhenPathIsValid() {
        String path = "/path/to/error/logs.txt";

        assertDoesNotThrow(() -> errorLoggerValidator.validateErrorLogsPath(path));
    }

    @Test
    public void validateExceptionShouldThrowExceptionWhenExceptionIsNull() {
        Exception exception = null;

        assertThrows(IllegalArgumentException.class, () -> errorLoggerValidator.validateException(exception));
    }

    @Test
    public void validateExceptionShouldNotThrowExceptionWhenExceptionIsNotNull() {
        Exception exception = new Exception("Test Exception");

        assertDoesNotThrow(() -> errorLoggerValidator.validateException(exception));
    }
}