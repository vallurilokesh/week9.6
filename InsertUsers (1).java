import java.sql.*;

public class InsertUsers {

    // Step 2: JDBC connection constants
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) NOT NULL)";
        String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";

        // Step 3: try-with-resources for the connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {

            // Step 4: Create table if it does not exist
            try (PreparedStatement createStmt = connection.prepareStatement(createTableSql)) {
                createStmt.execute();
            }

            // Steps 5-7: Insert two records using PreparedStatement
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                // First record
                insertStmt.setString(1, "John Doe");
                insertStmt.setString(2, "johndoe@example.com");
                insertStmt.executeUpdate();

                // Second record
                insertStmt.setString(1, "Jane Doe");
                insertStmt.setString(2, "janedoe@example.com");
                insertStmt.executeUpdate();
            }

            // Step 8: Success message
            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            // Step 9: Handle SQLException
            System.err.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        // Step 10: Resources closed automatically
    }
}
