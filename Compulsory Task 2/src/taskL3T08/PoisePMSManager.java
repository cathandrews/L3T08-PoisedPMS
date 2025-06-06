package taskL3T08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The PoisePMSManager class is the entry point of the application. It establishes a connection to
 * the database and initializes the menu manager.
 */
public class PoisePMSManager {
  public static void main(String[] args) {
    // Establish a connection to the database
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poisepms",
        "otheruser", "swordfish")) {

      // Initialize the menu manager with the database connection
      MenuManager menuManager = new MenuManager(connection);
      menuManager.displayMainMenu();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
