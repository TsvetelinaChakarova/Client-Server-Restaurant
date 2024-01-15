package bg.restaurant.systems.software.integration.design.logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorLoggerTest {

    private ErrorLogger errorLogger;
    private Path errorLogsPath;

    @BeforeEach
    public void setUp() throws IOException {
        errorLogsPath = Files.createTempFile("error_logs", ".txt");
        errorLogger = new ErrorLogger(errorLogsPath.toString());
    }

    @Test
    void testCreateErrorLogsWithNullPath() {
        assertThrows(IllegalArgumentException.class, () -> new ErrorLogger(null),
            "The system should correctly handle null path!");
    }

    @Test
    void testAppendNullLog() {
        assertThrows(IllegalArgumentException.class, () -> errorLogger.appendLogs(null),
            "The system should correctly handle null exception passed to append logs!");
    }

    @Test
    public void appendLogsShouldAppendErrorLogToFile() throws IOException {
        Exception exception = new Exception("Test Exception");

        errorLogger.appendLogs(exception);

        String expectedLog = "Timestamp: " + LocalDateTime.now() + "\n" +
                "Caused by: class java.lang.Exception\n" +
                "Exception message: Test Exception\n" +
                "Stack trace: " + Arrays.toString(exception.getStackTrace()) + "\n\n";

        String actualLog = Files.readString(errorLogsPath);
        Assertions.assertTrue(actualLog.contains(expectedLog));
    }

    @Test
    public void appendLogsShouldCreateNewFileIfFileIsEmpty() throws IOException {
        Exception exception = new Exception("Test Exception");

        errorLogger.appendLogs(exception);

        assertTrue(Files.exists(errorLogsPath));
        assertTrue(Files.size(errorLogsPath) > 0);
    }

    @Test
    public void appendLogsShouldNotCreateNewFileIfFileIsNotEmpty() throws IOException {
        Files.writeString(errorLogsPath, "Existing log");

        Exception exception = new Exception("Test Exception");

        errorLogger.appendLogs(exception);

        assertTrue(Files.exists(errorLogsPath));
    }

    @Test
    public void appendLogsShouldValidateException() throws IOException {
        Exception exception = null;

        assertThrows(IllegalArgumentException.class, () -> errorLogger.appendLogs(exception));
    }
}