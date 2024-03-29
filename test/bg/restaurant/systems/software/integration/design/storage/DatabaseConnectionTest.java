package bg.restaurant.systems.software.integration.design.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DatabaseConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private DatabaseConnection databaseConnection;

    @BeforeEach
    public void setup() {
        databaseConnection = new DatabaseConnection(URL, USER, PASSWORD);
    }

    @Test
    public void testExecuteQuery_ValidQuery() {
        String query = "SELECT * FROM users";
        ResultSet resultSet = databaseConnection.executeQuery(query);

        assertNull(resultSet);
    }

    @Test
    public void testExecuteQuery_InvalidQuery() {
        String query = "SELECT * FROM non_existing_table";
        ResultSet resultSet = databaseConnection.executeQuery(query);

        assertNull(resultSet);
    }

    @Test
    public void testCloseConnectionsToDatabase() {
        assertDoesNotThrow(() -> {
            databaseConnection.closeConnectionsToDatabase();
        });
    }

    @AfterEach
    public void cleanup() {
        databaseConnection.closeConnectionsToDatabase();
    }
}