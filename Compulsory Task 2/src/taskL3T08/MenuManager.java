package taskL3T08;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The MenuManager class handles the display and navigation of menus in the application. It provides
 * methods to display different menus and handle user choices.
 */
public class MenuManager {
  private Connection connection;
  private DatabaseManager dbManager;
  private InputHandler inputHandler;
  private ProjectManager projectManager;

  /**
   * Constructor for MenuManager.
   *
   * @param connection The database connection.
   */
  public MenuManager(Connection connection) {
    this.connection = connection;
    this.dbManager = new DatabaseManager(connection);
    this.inputHandler = new InputHandler(new Scanner(System.in));
    this.projectManager = new ProjectManager();
  }

  /**
   * Displays the main menu and handles user choices.
   *
   * @throws SQLException If a database access error occurs.
   */
  public void displayMainMenu() throws SQLException {
    while (true) {
      System.out.println("Hello! Welcome to PoisePMS.\n");
      System.out.println("What would you like to do?");
      System.out.println("1. View Information");
      System.out.println("2. Add New Information");
      System.out.println("3. Update Existing Information");
      System.out.println("4. Delete Information");
      System.out.println("5. Exit");
      System.out.println(); // Empty line for separation
      System.out.print("Enter your choice: ");
      String choice = inputHandler.getUserInput("");

      switch (choice) {
        case "1":
          displayTableMenu();
          break;
        case "2":
          displayAddMenu();
          break;
        case "3":
          displayUpdateMenu();
          break;
        case "4":
          displayDeleteMenu();
          break;
        case "5":
          System.out.println("Exiting PoisePMS. Goodbye!");
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Displays the table menu and handles user choices.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void displayTableMenu() throws SQLException {
    while (true) {
      System.out.println("\nWhat information are you looking for?");
      System.out.println("1. Project");
      System.out.println("2. Architect");
      System.out.println("3. Structural Engineer");
      System.out.println("4. Contractor");
      System.out.println("5. Customer");
      System.out.println("6. All Information");
      System.out.println("7. Return to Main Menu");
      System.out.println(); // Empty line for separation
      System.out.print("Enter your choice: ");
      String tableChoice = inputHandler.getUserInput("");

      switch (tableChoice) {
        case "1":
          dbManager.displayAllColumns("Projects");
          break;
        case "2":
          dbManager.displayAllColumns("Architects");
          break;
        case "3":
          dbManager.displayAllColumns("StructuralEngineers");
          break;
        case "4":
          dbManager.displayAllColumns("Contractors");
          break;
        case "5":
          dbManager.displayAllColumns("Customers");
          break;
        case "6":
          String[] tables =
              {"Projects", "Architects", "StructuralEngineers", "Contractors", "Customers"};
          for (String table : tables) {
            dbManager.displayAllColumns(table);
          }
          break;
        case "7":
          return; // Return to the main menu
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Displays the add menu and handles user choices.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void displayAddMenu() throws SQLException {
    while (true) {
      System.out.println("\nAdd New Information to:");
      System.out.println("1. Project");
      System.out.println("2. Architect");
      System.out.println("3. Structural Engineer");
      System.out.println("4. Contractor");
      System.out.println("5. Customer");
      System.out.println("6. Return to Main Menu");
      System.out.println(); // Empty line for separation
      System.out.print("Enter your choice: ");
      String tableChoice = inputHandler.getUserInput("");

      switch (tableChoice) {
        case "1":
          addProject();
          break;
        case "2":
          addArchitect();
          break;
        case "3":
          addStructuralEngineer();
          break;
        case "4":
          addContractor();
          break;
        case "5":
          addCustomer();
          break;
        case "6":
          return; // Return to the main menu
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Adds a new project to the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void addProject() throws SQLException {
    System.out.println("\nEnter Project Details:");
    String projectNumber = inputHandler.getUserInput("Project Number: ");
    String projectName = inputHandler.getUserInput("Project Name: ");
    String buildingType = inputHandler.getUserInput("Building Type: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");
    String erfNumber = inputHandler.getUserInput("ERF Number: ");
    double totalFee = inputHandler.getDoubleInput("Total Fee: ");
    double totalPaid = inputHandler.getDoubleInput("Total Paid: ");
    String deadline = inputHandler.getUserInput("Deadline (YYYY-MM-DD): ");
    int architectId = inputHandler.getIntInput("Architect ID: ");
    int contractorId = inputHandler.getIntInput("Contractor ID: ");
    int customerId = inputHandler.getIntInput("Customer ID: ");
    int structuralEngineerId = inputHandler.getIntInput("Structural Engineer ID: ");
    int projectManagerId = inputHandler.getIntInput("Project Manager ID: ");

    projectManager.insertProjectIfNotExists(connection, projectNumber, projectName, buildingType,
        physicalAddress, erfNumber, totalFee, totalPaid, deadline, architectId, contractorId,
        customerId, structuralEngineerId, projectManagerId);
  }

  /**
   * Adds a new architect to the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void addArchitect() throws SQLException {
    System.out.println("\nEnter Architect Details:");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values = {name, telephoneNumber, emailAddress, physicalAddress};
    dbManager.addNewRecord("Architects", values);
  }

  /**
   * Adds a new structural engineer to the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void addStructuralEngineer() throws SQLException {
    System.out.println("\nEnter Structural Engineer Details:");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values = {name, telephoneNumber, emailAddress, physicalAddress};
    dbManager.addNewRecord("StructuralEngineers", values);
  }

  /**
   * Adds a new contractor to the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void addContractor() throws SQLException {
    System.out.println("\nEnter Contractor Details:");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values = {name, telephoneNumber, emailAddress, physicalAddress};
    dbManager.addNewRecord("Contractors", values);
  }

  /**
   * Adds a new customer to the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void addCustomer() throws SQLException {
    System.out.println("\nEnter Customer Details:");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values = {name, telephoneNumber, emailAddress, physicalAddress};
    dbManager.addNewRecord("Customers", values);
  }

  /**
   * Displays the update menu and handles user choices.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void displayUpdateMenu() throws SQLException {
    while (true) {
      System.out.println("\nUpdate Information in:");
      System.out.println("1. Project");
      System.out.println("2. Architect");
      System.out.println("3. Structural Engineer");
      System.out.println("4. Contractor");
      System.out.println("5. Customer");
      System.out.println("6. Return to Main Menu");
      System.out.println(); // Empty line for separation
      System.out.print("Enter your choice: ");
      String tableChoice = inputHandler.getUserInput("");

      switch (tableChoice) {
        case "1":
          updateProject();
          break;
        case "2":
          updateArchitect();
          break;
        case "3":
          updateStructuralEngineer();
          break;
        case "4":
          updateContractor();
          break;
        case "5":
          updateCustomer();
          break;
        case "6":
          return; // Return to the main menu
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Updates an existing project in the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void updateProject() throws SQLException {
    System.out.println("\nEnter Project ID to update:");
    int projectId = inputHandler.getIntInput("Project ID: ");

    System.out.println("Enter new Project Details (leave blank to keep current value):");
    String projectNumber = inputHandler.getUserInput("Project Number: ");
    String projectName = inputHandler.getUserInput("Project Name: ");
    String buildingType = inputHandler.getUserInput("Building Type: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");
    String erfNumber = inputHandler.getUserInput("ERF Number: ");
    String totalFeeInput = inputHandler.getUserInput("Total Fee: ");
    String totalPaidInput = inputHandler.getUserInput("Total Paid: ");
    String deadline = inputHandler.getUserInput("Deadline (YYYY-MM-DD): ");
    String architectIdInput = inputHandler.getUserInput("Architect ID: ");
    String contractorIdInput = inputHandler.getUserInput("Contractor ID: ");
    String customerIdInput = inputHandler.getUserInput("Customer ID: ");
    String structuralEngineerIdInput = inputHandler.getUserInput("Structural Engineer ID: ");
    String projectManagerIdInput = inputHandler.getUserInput("Project Manager ID: ");

    double totalFee = totalFeeInput.isEmpty() ? 0 : Double.parseDouble(totalFeeInput);
    double totalPaid = totalPaidInput.isEmpty() ? 0 : Double.parseDouble(totalPaidInput);
    int architectId = architectIdInput.isEmpty() ? 0 : Integer.parseInt(architectIdInput);
    int contractorId = contractorIdInput.isEmpty() ? 0 : Integer.parseInt(contractorIdInput);
    int customerId = customerIdInput.isEmpty() ? 0 : Integer.parseInt(customerIdInput);
    int structuralEngineerId =
        structuralEngineerIdInput.isEmpty() ? 0 : Integer.parseInt(structuralEngineerIdInput);
    int projectManagerId =
        projectManagerIdInput.isEmpty() ? 0 : Integer.parseInt(projectManagerIdInput);

    projectManager.updateProject(connection, projectId, projectNumber, projectName, buildingType,
        physicalAddress, erfNumber, totalFee, totalPaid, deadline, architectId, contractorId,
        customerId, structuralEngineerId, projectManagerId);
  }

  /**
   * Updates an existing architect in the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void updateArchitect() throws SQLException {
    System.out.println("\nEnter Architect ID to update:");
    int architectId = inputHandler.getIntInput("Architect ID: ");

    System.out.println("Enter new Architect Details (leave blank to keep current value):");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values =
        {name, telephoneNumber, emailAddress, physicalAddress, String.valueOf(architectId)};
    dbManager.updateExistingRecord("Architects", values);
  }

  /**
   * Updates an existing structural engineer in the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void updateStructuralEngineer() throws SQLException {
    System.out.println("\nEnter Structural Engineer ID to update:");
    int structuralEngineerId = inputHandler.getIntInput("Structural Engineer ID: ");

    System.out
        .println("Enter new Structural Engineer Details (leave blank to keep current value):");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values = {name, telephoneNumber, emailAddress, physicalAddress,
        String.valueOf(structuralEngineerId)};
    dbManager.updateExistingRecord("StructuralEngineers", values);
  }

  /**
   * Updates an existing contractor in the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void updateContractor() throws SQLException {
    System.out.println("\nEnter Contractor ID to update:");
    int contractorId = inputHandler.getIntInput("Contractor ID: ");

    System.out.println("Enter new Contractor Details (leave blank to keep current value):");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values =
        {name, telephoneNumber, emailAddress, physicalAddress, String.valueOf(contractorId)};
    dbManager.updateExistingRecord("Contractors", values);
  }

  /**
   * Updates an existing customer in the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void updateCustomer() throws SQLException {
    System.out.println("\nEnter Customer ID to update:");
    int customerId = inputHandler.getIntInput("Customer ID: ");

    System.out.println("Enter new Customer Details (leave blank to keep current value):");
    String name = inputHandler.getUserInput("Name: ");
    String telephoneNumber = inputHandler.getUserInput("Telephone Number: ");
    String emailAddress = inputHandler.getUserInput("Email Address: ");
    String physicalAddress = inputHandler.getUserInput("Physical Address: ");

    String[] values =
        {name, telephoneNumber, emailAddress, physicalAddress, String.valueOf(customerId)};
    dbManager.updateExistingRecord("Customers", values);
  }

  /**
   * Displays the delete menu and handles user choices.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void displayDeleteMenu() throws SQLException {
    while (true) {
      System.out.println("\nDelete Information from:");
      System.out.println("1. Project");
      System.out.println("2. Architect");
      System.out.println("3. Structural Engineer");
      System.out.println("4. Contractor");
      System.out.println("5. Customer");
      System.out.println("6. Return to Main Menu");
      System.out.println(); // Empty line for separation
      System.out.print("Enter your choice: ");
      String tableChoice = inputHandler.getUserInput("");

      switch (tableChoice) {
        case "1":
          deleteProject();
          break;
        case "2":
          deleteArchitect();
          break;
        case "3":
          deleteStructuralEngineer();
          break;
        case "4":
          deleteContractor();
          break;
        case "5":
          deleteCustomer();
          break;
        case "6":
          return; // Return to the main menu
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Deletes a project from the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void deleteProject() throws SQLException {
    System.out.println("\nEnter Project ID to delete:");
    int projectId = inputHandler.getIntInput("Project ID: ");
    projectManager.deleteProject(connection, projectId);
  }

  /**
   * Deletes an architect from the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void deleteArchitect() throws SQLException {
    System.out.println("\nEnter Architect ID to delete:");
    int architectId = inputHandler.getIntInput("Architect ID: ");
    dbManager.deleteArchitect(connection, architectId);
  }

  /**
   * Deletes a structural engineer from the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void deleteStructuralEngineer() throws SQLException {
    System.out.println("\nEnter Structural Engineer ID to delete:");
    int structuralEngineerId = inputHandler.getIntInput("Structural Engineer ID: ");
    dbManager.deleteStructuralEngineer(connection, structuralEngineerId);
  }

  /**
   * Deletes a contractor from the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void deleteContractor() throws SQLException {
    System.out.println("\nEnter Contractor ID to delete:");
    int contractorId = inputHandler.getIntInput("Contractor ID: ");
    dbManager.deleteContractor(connection, contractorId);
  }

  /**
   * Deletes a customer from the database.
   *
   * @throws SQLException If a database access error occurs.
   */
  private void deleteCustomer() throws SQLException {
    System.out.println("\nEnter Customer ID to delete:");
    int customerId = inputHandler.getIntInput("Customer ID: ");
    dbManager.deleteCustomer(connection, customerId);
  }
}
