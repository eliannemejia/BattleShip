package cs3500.pa03;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the view for a battle salvo game
 */
public class SalvoView {
  private final Appendable output;

  public SalvoView(Appendable a) {
    output = a;
  }

  /**
   * Welcomes the user to the battle salvo game
   */
  public void displayWelcome() {
    try {
      this.output.append("Welcome to BattleSalvo!").append(System.lineSeparator());
      this.output.append("What's your name?").append(System.lineSeparator());
    } catch (IOException e) {
      System.out.println("We've encountered an error!");
    }
  }

  /**
   * @param message to be printed
   */
  public void displayMessage(String message) {
    try {
      this.output.append(message).append(System.lineSeparator());
    } catch (IOException e) {
      System.out.println("We've encountered an error!");
    }
  }

  /**
   * @param ships the ship types to choose from
   */
  public void displayShipOptions(ArrayList<ShipType> ships) {
    try {
      output.append("[");
    } catch (IOException e) {
      System.out.println("We've encountered an error!");
    }
    for (ShipType st : ships) {
      if (ships.indexOf(st) == ships.size() - 1) {
        try {
          output.append(st.toString());
          output.append("]");
        } catch (IOException e) {
          System.out.println("We've encountered an error!");
        }
      } else {
        try {
          output.append(st.toString());
          output.append(" ");
        } catch (IOException e) {
          System.out.println("We've encountered an error!");
        }
      }
    }
  }

  /**
   * @param grid the game board to be displayed
   */
  public void displayBoard(char[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        try {
          output.append(grid[i][j]);
          output.append(" ");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      try {
        output.append(System.lineSeparator());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
