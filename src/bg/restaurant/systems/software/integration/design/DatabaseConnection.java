package bg.restaurant.systems.software.integration.design;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public DatabaseConnection(String url, String user, String password) {
        this.connection = null;

        try {
            Class.forName(CLASS_NAME);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return resultSet;
    }

    public void closeConnectionsToDatabase() {
        try {
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] arg) {
        DatabaseConnection databaseConnection = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/restaurants", "root", "");

        try {
            ResultSet resultSet = databaseConnection.executeQuery("select * from recipes");

            int id;
            String name;
            String type;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                type = resultSet.getString("type");
                System.out.println("Id : " + id
                        + " Name : " + name + " " + type);
            }
        } catch (
                Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
