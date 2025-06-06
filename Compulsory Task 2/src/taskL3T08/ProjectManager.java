package taskL3T08;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The ProjectManager class handles all operations related to projects in the database. This
 * includes inserting, updating, deleting, finalizing, and reading projects.
 */
public class ProjectManager {

  /**
   * Inserts a new project into the database.
   *
   * @param connection The database connection.
   * @param projectNumber The project number.
   * @param projectName The name of the project.
   * @param buildingType The type of building.
   * @param physicalAddress The physical address of the project.
   * @param erfNumber The ERF number of the project.
   * @param totalFee The total fee for the project.
   * @param totalPaid The total amount paid for the project.
   * @param deadline The deadline for the project.
   * @param architectId The ID of the architect.
   * @param contractorId The ID of the contractor.
   * @param customerId The ID of the customer.
   * @param structuralEngineerId The ID of the structural engineer.
   * @param projectManagerId The ID of the project manager.
   * @throws SQLException If a database access error occurs.
   */
  public void insertProject(Connection connection, String projectNumber, String projectName,
      String buildingType, String physicalAddress, String erfNumber, double totalFee,
      double totalPaid, String deadline, int architectId, int contractorId, int customerId,
      int structuralEngineerId, int projectManagerId) throws SQLException {
    String sql =
        "INSERT INTO Projects (project_number, project_name, building_type, physical_address, erf_number, total_fee, total_paid, deadline, is_finalised, architect_id, contractor_id, customer_id, structural_engineer_id, project_manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, projectNumber);
      preparedStatement.setString(2, projectName);
      preparedStatement.setString(3, buildingType);
      preparedStatement.setString(4, physicalAddress);
      preparedStatement.setString(5, erfNumber);
      preparedStatement.setDouble(6, totalFee);
      preparedStatement.setDouble(7, totalPaid);
      preparedStatement.setDate(8, Date.valueOf(deadline));
      preparedStatement.setBoolean(9, false);
      preparedStatement.setInt(10, architectId);
      preparedStatement.setInt(11, contractorId);
      preparedStatement.setInt(12, customerId);
      preparedStatement.setInt(13, structuralEngineerId);
      preparedStatement.setInt(14, projectManagerId);

      preparedStatement.executeUpdate();
      System.out.println("Project inserted successfully.");
    }
  }

  /**
   * Inserts a new project into the database if it does not already exist.
   *
   * @param connection The database connection.
   * @param projectNumber The project number.
   * @param projectName The name of the project.
   * @param buildingType The type of building.
   * @param physicalAddress The physical address of the project.
   * @param erfNumber The ERF number of the project.
   * @param totalFee The total fee for the project.
   * @param totalPaid The total amount paid for the project.
   * @param deadline The deadline for the project.
   * @param architectId The ID of the architect.
   * @param contractorId The ID of the contractor.
   * @param customerId The ID of the customer.
   * @param structuralEngineerId The ID of the structural engineer.
   * @param projectManagerId The ID of the project manager.
   * @throws SQLException If a database access error occurs.
   */
  public void insertProjectIfNotExists(Connection connection, String projectNumber,
      String projectName, String buildingType, String physicalAddress, String erfNumber,
      double totalFee, double totalPaid, String deadline, int architectId, int contractorId,
      int customerId, int structuralEngineerId, int projectManagerId) throws SQLException {
    String checkSql = "SELECT COUNT(*) FROM Projects WHERE project_number = ?";
    try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
      checkStatement.setString(1, projectNumber);
      try (ResultSet resultSet = checkStatement.executeQuery()) {
        if (resultSet.next() && resultSet.getInt(1) == 0) {
          insertProject(connection, projectNumber, projectName, buildingType, physicalAddress,
              erfNumber, totalFee, totalPaid, deadline, architectId, contractorId, customerId,
              structuralEngineerId, projectManagerId);
        } else {
          System.out.println("Project with number " + projectNumber + " already exists.");
        }
      }
    }
  }

  /**
   * Updates an existing project in the database.
   *
   * @param connection The database connection.
   * @param projectId The ID of the project to update.
   * @param projectNumber The project number.
   * @param projectName The name of the project.
   * @param buildingType The type of building.
   * @param physicalAddress The physical address of the project.
   * @param erfNumber The ERF number of the project.
   * @param totalFee The total fee for the project.
   * @param totalPaid The total amount paid for the project.
   * @param deadline The deadline for the project.
   * @param architectId The ID of the architect.
   * @param contractorId The ID of the contractor.
   * @param customerId The ID of the customer.
   * @param structuralEngineerId The ID of the structural engineer.
   * @param projectManagerId The ID of the project manager.
   * @throws SQLException If a database access error occurs.
   */
  public void updateProject(Connection connection, int projectId, String projectNumber,
      String projectName, String buildingType, String physicalAddress, String erfNumber,
      double totalFee, double totalPaid, String deadline, int architectId, int contractorId,
      int customerId, int structuralEngineerId, int projectManagerId) throws SQLException {
    String sql =
        "UPDATE Projects SET project_number = ?, project_name = ?, building_type = ?, physical_address = ?, erf_number = ?, total_fee = ?, total_paid = ?, deadline = ?, architect_id = ?, contractor_id = ?, customer_id = ?, structural_engineer_id = ?, project_manager_id = ? WHERE project_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, projectNumber);
      preparedStatement.setString(2, projectName);
      preparedStatement.setString(3, buildingType);
      preparedStatement.setString(4, physicalAddress);
      preparedStatement.setString(5, erfNumber);
      preparedStatement.setDouble(6, totalFee);
      preparedStatement.setDouble(7, totalPaid);
      preparedStatement.setDate(8, Date.valueOf(deadline));
      preparedStatement.setInt(9, architectId);
      preparedStatement.setInt(10, contractorId);
      preparedStatement.setInt(11, customerId);
      preparedStatement.setInt(12, structuralEngineerId);
      preparedStatement.setInt(13, projectManagerId);
      preparedStatement.setInt(14, projectId);

      preparedStatement.executeUpdate();
      System.out.println("Project updated successfully.");
    }
  }

  /**
   * Deletes a project from the database.
   *
   * @param connection The database connection.
   * @param projectId The ID of the project to delete.
   * @throws SQLException If a database access error occurs.
   */
  public void deleteProject(Connection connection, int projectId) throws SQLException {
    String sql = "DELETE FROM Projects WHERE project_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, projectId);
      preparedStatement.executeUpdate();
      System.out.println("Project deleted successfully.");
    }
  }

  /**
   * Finalizes a project in the database.
   *
   * @param connection The database connection.
   * @param projectId The ID of the project to finalize.
   * @throws SQLException If a database access error occurs.
   */
  public void finalizeProject(Connection connection, int projectId) throws SQLException {
    String sql = "UPDATE Projects SET is_finalised = ?, completion_date = ? WHERE project_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setBoolean(1, true);
      preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
      preparedStatement.setInt(3, projectId);

      preparedStatement.executeUpdate();
      System.out.println("Project finalized successfully.");
    }
  }

  /**
   * Reads all projects from the database.
   *
   * @param connection The database connection.
   * @throws SQLException If a database access error occurs.
   */
  public void readProjects(Connection connection) throws SQLException {
    String sql = "SELECT * FROM Projects";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()) {

      System.out.println("\nAll Projects:");
      while (resultSet.next()) {
        System.out.println("Project ID: " + resultSet.getInt("project_id") + ", Project Number: "
            + resultSet.getString("project_number") + ", Project Name: "
            + resultSet.getString("project_name"));
      }
    }
  }

  /**
   * Reads all incomplete projects from the database.
   *
   * @param connection The database connection.
   * @throws SQLException If a database access error occurs.
   */
  public void readIncompleteProjects(Connection connection) throws SQLException {
    String sql = "SELECT * FROM Projects WHERE is_finalised = FALSE";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()) {

      System.out.println("\nIncomplete Projects:");
      while (resultSet.next()) {
        System.out.println("Project ID: " + resultSet.getInt("project_id") + ", Project Number: "
            + resultSet.getString("project_number") + ", Project Name: "
            + resultSet.getString("project_name"));
      }
    }
  }

  /**
   * Reads all overdue projects from the database.
   *
   * @param connection The database connection.
   * @throws SQLException If a database access error occurs.
   */
  public void readOverdueProjects(Connection connection) throws SQLException {
    String sql = "SELECT * FROM Projects WHERE deadline < CURRENT_DATE AND is_finalised = FALSE";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()) {

      System.out.println("\nOverdue Projects:");
      while (resultSet.next()) {
        System.out.println("Project ID: " + resultSet.getInt("project_id") + ", Project Number: "
            + resultSet.getString("project_number") + ", Project Name: "
            + resultSet.getString("project_name"));
      }
    }
  }

  /**
   * Finds projects by project number or name.
   *
   * @param connection The database connection.
   * @param projectNumber The project number to search for.
   * @param projectName The project name to search for.
   * @throws SQLException If a database access error occurs.
   */
  public void findProjectByNumberOrName(Connection connection, String projectNumber,
      String projectName) throws SQLException {
    String sql = "SELECT * FROM Projects WHERE project_number = ? OR project_name = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, projectNumber);
      preparedStatement.setString(2, projectName);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        System.out.println("\nProjects Found by Number or Name:");
        while (resultSet.next()) {
          System.out.println("Project ID: " + resultSet.getInt("project_id") + ", Project Number: "
              + resultSet.getString("project_number") + ", Project Name: "
              + resultSet.getString("project_name"));
        }
      }
    }
  }
}
