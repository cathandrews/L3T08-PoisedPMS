package taskL3T08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class handles the connection to the database. It provides a method to get
 * a connection to the database.
 */
public class DatabaseConnection {
  /**
   * Gets a connection to the database.
   *
   * @return A connection to the database.
   * @throws SQLException If a database access error occurs.
   */
  public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/poisepms";
    String username = "otheruser";
    String password = "swordfish";

    return DriverManager.getConnection(url, username, password);
  }
}
