package bg.restaurant.systems.software.integration.design.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ErrorLogger extends ErrorLoggerValidator {
    private static final String TIMESTAMP_LINE = "Timestamp: ";
    private static final String CAUSE_LINE = "Caused by: ";
    private static final String MESSAGE_LINE = "Exception message: ";
    private static final String TRACE_LINE = "Stack trace: ";
    private final Path errorLogsPath;

    public ErrorLogger(String path) {
        validateErrorLogsPath(path);

        this.errorLogsPath = Path.of(path);
    }

    public void appendLogs(Exception e) throws IOException {
        validateException(e);

        if (!isFileEmpty()) {
            Files.writeString(errorLogsPath, System.lineSeparator(), StandardOpenOption.APPEND);
        }
        Files.writeString(errorLogsPath, getErrorLog(e), StandardOpenOption.APPEND);
    }

    private String getErrorLog(Exception e) {
        return TIMESTAMP_LINE +
            LocalDateTime.now() + "\n" +
            CAUSE_LINE +
            e.getClass() + "\n" +
            MESSAGE_LINE +
            e.getMessage() + "\n" +
            TRACE_LINE +
            Arrays.toString(e.getStackTrace()) + "\n\n";
    }

    private boolean isFileEmpty() throws IOException {
        return Files.size(errorLogsPath) == 0;
    }
}