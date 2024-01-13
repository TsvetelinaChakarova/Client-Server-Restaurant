package bg.restaurant.systems.software.integration.design.command.handlers;

import java.sql.SQLException;

@FunctionalInterface
public interface CommandHandler {
    String execute() throws SQLException;
}
