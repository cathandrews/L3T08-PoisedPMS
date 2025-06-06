package taskL3T08;

import java.util.Scanner;

/**
 * The InputHandler class handles user input from the console. It provides methods to get different
 * types of input from the user.
 */
public class InputHandler {
  private Scanner scanner;

  /**
   * Constructor for InputHandler.
   *
   * @param scanner The Scanner object to read user input.
   */
  public InputHandler(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * Gets a string input from the user.
   *
   * @param prompt The prompt to display to the user.
   * @return The user's input as a string.
   */
  public String getUserInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
  }

  /**
   * Gets an integer input from the user.
   *
   * @param prompt The prompt to display to the user.
   * @return The user's input as an integer.
   */
  public int getIntInput(String prompt) {
    while (true) {
      try {
        return Integer.parseInt(getUserInput(prompt));
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  /**
   * Gets a double input from the user.
   *
   * @param prompt The prompt to display to the user.
   * @return The user's input as a double.
   */
  public double getDoubleInput(String prompt) {
    while (true) {
      try {
        return Double.parseDouble(getUserInput(prompt));
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }
  }
}
