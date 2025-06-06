package taskL3T08;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DatabaseManager class handles database operations such as displaying, adding, updating, and
 * deleting records.
 */
public class DatabaseManager {
  private Connection connection;

  /**
   * Constructor for DatabaseManager.
   *
   * @param connection The database connection.
   */
  public DatabaseManager(Connection connection) {
    this.connection = connection;
  }

  /**
   * Displays all columns of a specified table.
   *
   * @param tableName The name of the table to display.
   * @throws SQLException If a database access error occurs.
   */
  public void displayAllColumns(String tableName) throws SQLException {
    String sql = "SELECT * FROM " + tableName;
    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      ResultSetMetaData metaData = resultSet.getMetaData();
      int columnCount = metaData.getColumnCount();

      System.out.println("\n" + tableName + ":");

      // Print column headers
      for (int i = 1; i <= columnCount; i++) {
        System.out.print(metaData.getColumnName(i) + "\t");
      }
      System.out.println();

      // Print rows
      while (resultSet.next()) {
        for (int i = 1; i <= columnCount; i++) {
          System.out.print(resultSet.getString(i) + "\t");
        }
        System.out.println();
      }
    }
  }

  /**
   * Adds a new record to the specified table.
   *
   * @param tableName The name of the table to add the record to.
   * @param values The values of the new record.
   * @throws SQLException If a database access error occurs.
   */
  public void addNewRecord(String tableName, String[] values) throws SQLException {
    switch (tableName) {
      case "Projects":
        addProject(values);
        break;
      case "Architects":
        addArchitect(values);
        break;
      case "StructuralEngineers":
        addStructuralEngineer(values);
        break;
      case "Contractors":
        addContractor(values);
        break;
      case "Customers":
        addCustomer(values);
        break;
      default:
        System.out.println("Unsupported table: " + tableName);
    }
  }

  /**
   * Adds a new project to the Projects table.
   *
   * @param values The values of the new project.
   * @throws SQLException If a database access error occurs.
   */
  private void addProject(String[] values) throws SQLException {
    String sql =
        "INSERT INTO Projects (project_number, project_name, building_type, physical_address, erf_number, total_fee, total_paid, deadline, is_finalised, architect_id, contractor_id, customer_id, structural_engineer_id, project_manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // project_number
      preparedStatement.setString(2, values[1]); // project_name
      preparedStatement.setString(3, values[2]); // building_type
      preparedStatement.setString(4, values[3]); // physical_address
      preparedStatement.setString(5, values[4]); // erf_number
      preparedStatement.setDouble(6, Double.parseDouble(values[5])); // total_fee
      preparedStatement.setDouble(7, Double.parseDouble(values[6])); // total_paid
      preparedStatement.setDate(8, java.sql.Date.valueOf(values[7])); // deadline
      preparedStatement.setBoolean(9, Boolean.parseBoolean(values[8])); // is_finalised
      preparedStatement.setInt(10, Integer.parseInt(values[9])); // architect_id
      preparedStatement.setInt(11, Integer.parseInt(values[10])); // contractor_id
      preparedStatement.setInt(12, Integer.parseInt(values[11])); // customer_id
      preparedStatement.setInt(13, Integer.parseInt(values[12])); // structural_engineer_id
      preparedStatement.setInt(14, Integer.parseInt(values[13])); // project_manager_id

      preparedStatement.executeUpdate();
      System.out.println("Project added successfully.");
    }
  }

  /**
   * Adds a new architect to the Architects table.
   *
   * @param values The values of the new architect.
   * @throws SQLException If a database access error occurs.
   */
  private void addArchitect(String[] values) throws SQLException {
    String sql =
        "INSERT INTO Architects (name, telephone_number, email_address, physical_address) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address

      preparedStatement.executeUpdate();
      System.out.println("Architect added successfully.");
    }
  }

  /**
   * Adds a new structural engineer to the StructuralEngineers table.
   *
   * @param values The values of the new structural engineer.
   * @throws SQLException If a database access error occurs.
   */
  private void addStructuralEngineer(String[] values) throws SQLException {
    String sql =
        "INSERT INTO StructuralEngineers (name, telephone_number, email_address, physical_address) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address

      preparedStatement.executeUpdate();
      System.out.println("Structural Engineer added successfully.");
    }
  }

  /**
   * Adds a new contractor to the Contractors table.
   *
   * @param values The values of the new contractor.
   * @throws SQLException If a database access error occurs.
   */
  private void addContractor(String[] values) throws SQLException {
    String sql =
        "INSERT INTO Contractors (name, telephone_number, email_address, physical_address) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address

      preparedStatement.executeUpdate();
      System.out.println("Contractor added successfully.");
    }
  }

  /**
   * Adds a new customer to the Customers table.
   *
   * @param values The values of the new customer.
   * @throws SQLException If a database access error occurs.
   */
  private void addCustomer(String[] values) throws SQLException {
    String sql =
        "INSERT INTO Customers (name, telephone_number, email_address, physical_address) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address

      preparedStatement.executeUpdate();
      System.out.println("Customer added successfully.");
    }
  }

  /**
   * Updates an existing record in the specified table.
   *
   * @param tableName The name of the table to update the record in.
   * @param values The new values of the record.
   * @throws SQLException If a database access error occurs.
   */
  public void updateExistingRecord(String tableName, String[] values) throws SQLException {
    switch (tableName) {
      case "Projects":
        updateProject(values);
        break;
      case "Architects":
        updateArchitect(values);
        break;
      case "StructuralEngineers":
        updateStructuralEngineer(values);
        break;
      case "Contractors":
        updateContractor(values);
        break;
      case "Customers":
        updateCustomer(values);
        break;
      default:
        System.out.println("Unsupported table: " + tableName);
    }
  }

  /**
   * Updates an existing project in the Projects table.
   *
   * @param values The new values of the project.
   * @throws SQLException If a database access error occurs.
   */
  private void updateProject(String[] values) throws SQLException {
    String sql =
        "UPDATE Projects SET project_number = ?, project_name = ?, building_type = ?, physical_address = ?, erf_number = ?, total_fee = ?, total_paid = ?, deadline = ?, is_finalised = ?, architect_id = ?, contractor_id = ?, customer_id = ?, structural_engineer_id = ?, project_manager_id = ? WHERE project_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // project_number
      preparedStatement.setString(2, values[1]); // project_name
      preparedStatement.setString(3, values[2]); // building_type
      preparedStatement.setString(4, values[3]); // physical_address
      preparedStatement.setString(5, values[4]); // erf_number
      preparedStatement.setDouble(6, Double.parseDouble(values[5])); // total_fee
      preparedStatement.setDouble(7, Double.parseDouble(values[6])); // total_paid
      preparedStatement.setDate(8, java.sql.Date.valueOf(values[7])); // deadline
      preparedStatement.setBoolean(9, Boolean.parseBoolean(values[8])); // is_finalised
      preparedStatement.setInt(10, Integer.parseInt(values[9])); // architect_id
      preparedStatement.setInt(11, Integer.parseInt(values[10])); // contractor_id
      preparedStatement.setInt(12, Integer.parseInt(values[11])); // customer_id
      preparedStatement.setInt(13, Integer.parseInt(values[12])); // structural_engineer_id
      preparedStatement.setInt(14, Integer.parseInt(values[13])); // project_manager_id
      preparedStatement.setInt(15, Integer.parseInt(values[14])); // project_id

      preparedStatement.executeUpdate();
      System.out.println("Project updated successfully.");
    }
  }

  /**
   * Updates an existing architect in the Architects table.
   *
   * @param values The new values of the architect.
   * @throws SQLException If a database access error occurs.
   */
  private void updateArchitect(String[] values) throws SQLException {
    String sql =
        "UPDATE Architects SET name = ?, telephone_number = ?, email_address = ?, physical_address = ? WHERE architect_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address
      preparedStatement.setInt(5, Integer.parseInt(values[4])); // architect_id

      preparedStatement.executeUpdate();
      System.out.println("Architect updated successfully.");
    }
  }

  /**
   * Updates an existing structural engineer in the StructuralEngineers table.
   *
   * @param values The new values of the structural engineer.
   * @throws SQLException If a database access error occurs.
   */
  private void updateStructuralEngineer(String[] values) throws SQLException {
    String sql =
        "UPDATE StructuralEngineers SET name = ?, telephone_number = ?, email_address = ?, physical_address = ? WHERE structural_engineer_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address
      preparedStatement.setInt(5, Integer.parseInt(values[4])); // structural_engineer_id

      preparedStatement.executeUpdate();
      System.out.println("Structural Engineer updated successfully.");
    }
  }

  /**
   * Updates an existing contractor in the Contractors table.
   *
   * @param values The new values of the contractor.
   * @throws SQLException If a database access error occurs.
   */
  private void updateContractor(String[] values) throws SQLException {
    String sql =
        "UPDATE Contractors SET name = ?, telephone_number = ?, email_address = ?, physical_address = ? WHERE contractor_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address
      preparedStatement.setInt(5, Integer.parseInt(values[4])); // contractor_id

      preparedStatement.executeUpdate();
      System.out.println("Contractor updated successfully.");
    }
  }

  /**
   * Updates an existing customer in the Customers table.
   *
   * @param values The new values of the customer.
   * @throws SQLException If a database access error occurs.
   */
  private void updateCustomer(String[] values) throws SQLException {
    String sql =
        "UPDATE Customers SET name = ?, telephone_number = ?, email_address = ?, physical_address = ? WHERE customer_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, values[0]); // name
      preparedStatement.setString(2, values[1]); // telephone_number
      preparedStatement.setString(3, values[2]); // email_address
      preparedStatement.setString(4, values[3]); // physical_address
      preparedStatement.setInt(5, Integer.parseInt(values[4])); // customer_id

      preparedStatement.executeUpdate();
      System.out.println("Customer updated successfully.");
    }
  }

  /**
   * Deletes an architect from the Architects table.
   *
   * @param connection The database connection.
   * @param architectId The ID of the architect to delete.
   * @throws SQLException If a database access error occurs.
   */
  public void deleteArchitect(Connection connection, int architectId) throws SQLException {
    String sql = "DELETE FROM Architects WHERE architect_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, architectId);
      preparedStatement.executeUpdate();
      System.out.println("Architect deleted successfully.");
    }
  }

  /**
   * Deletes a structural engineer from the StructuralEngineers table.
   *
   * @param connection The database connection.
   * @param structuralEngineerId The ID of the structural engineer to delete.
   * @throws SQLException If a database access error occurs.
   */
  public void deleteStructuralEngineer(Connection connection, int structuralEngineerId)
      throws SQLException {
    String sql = "DELETE FROM StructuralEngineers WHERE structural_engineer_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, structuralEngineerId);
      preparedStatement.executeUpdate();
      System.out.println("Structural Engineer deleted successfully.");
    }
  }

  /**
   * Deletes a contractor from the Contractors table.
   *
   * @param connection The database connection.
   * @param contractorId The ID of the contractor to delete.
   * @throws SQLException If a database access error occurs.
   */
  public void deleteContractor(Connection connection, int contractorId) throws SQLException {
    String sql = "DELETE FROM Contractors WHERE contractor_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, contractorId);
      preparedStatement.executeUpdate();
      System.out.println("Contractor deleted successfully.");
    }
  }

  /**
   * Deletes a customer from the Customers table.
   *
   * @param connection The database connection.
   * @param customerId The ID of the customer to delete.
   * @throws SQLException If a database access error occurs.
   */
  public void deleteCustomer(Connection connection, int customerId) throws SQLException {
    String sql = "DELETE FROM Customers WHERE customer_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, customerId);
      preparedStatement.executeUpdate();
      System.out.println("Customer deleted successfully.");
    }
  }
}
