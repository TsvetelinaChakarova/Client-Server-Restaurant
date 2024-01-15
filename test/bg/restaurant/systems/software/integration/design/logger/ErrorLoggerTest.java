package bg.restaurant.systems.software.integration.design.logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;

public class ErrorLoggerTest {

    private ErrorLogger errorLogger;
    private Path errorLogsPath;

    @BeforeEach
    public void setUp() throws IOException {
        errorLogsPath = Files.createTempFile("error_logs", ".txt");
        errorLogger = new ErrorLogger(errorLogsPath.toString());
    }

    @Test
    public void appendLogs_shouldAppendErrorLogToFile() throws IOException {
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
    public void appendLogs_shouldCreateNewFileIfFileIsEmpty() throws IOException {
        Files.delete(errorLogsPath);

        Exception exception = new Exception("Test Exception");

        errorLogger.appendLogs(exception);

        assertTrue(Files.exists(errorLogsPath));
        assertTrue(Files.size(errorLogsPath) > 0);
    }

    @Test
    public void appendLogs_shouldNotCreateNewFileIfFileIsNotEmpty() throws IOException {
        Files.writeString(errorLogsPath, "Existing log");

        Exception exception = new Exception("Test Exception");

        errorLogger.appendLogs(exception);

        assertTrue(Files.exists(errorLogsPath));
        Assertions.assertEquals("Existing log", Files.readString(errorLogsPath));
    }

    @Test
    public void appendLogs_shouldValidateException() throws IOException {
        Exception exception = null;

        assertThrows(IllegalArgumentException.class, () -> errorLogger.appendLogs(exception));
    }

    @Test
    public void appendLogs_shouldHandleIOException() throws IOException {
        Exception exception = new IOException("IO Exception");

        try (MockedStatic<Files> mockedFiles = Mockito.mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.writeString(any(Path.class), any(CharSequence.class), any())).thenThrow(exception);

            assertThrows(IOException.class, () -> errorLogger.appendLogs(new Exception("Test Exception")));
        }
    }
}