package bg.restaurant.systems.software.integration.design.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class ErrorLogger extends ErrorLoggerValidator {
    private static final String CAUSE_LINE = "Caused by: ";
    private static final String MESSAGE_LINE = ", exception message: ";
    private static final String TRACE_LINE = ", with stack trace: ";
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
        return CAUSE_LINE +
            e.getClass() +
            MESSAGE_LINE +
            e.getMessage() +
            TRACE_LINE +
            Arrays.toString(e.getStackTrace());
    }

    private boolean isFileEmpty() throws IOException {
        return Files.size(errorLogsPath) == 0;
    }
}