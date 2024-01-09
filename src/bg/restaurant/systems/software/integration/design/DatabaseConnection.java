package bg.restaurant.systems.software.integration.design;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] arg) {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurants", "root", "");

            Statement statement;
            statement = connection.createStatement();

            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from recipes");

            int id;
            String name;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                System.out.println("Id : " + id
                        + " Name : " + name);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
