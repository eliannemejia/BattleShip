package cs3500.pa03;

import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in SalvoView.Class
 */
class SalvoViewTest {
  private SalvoView view;
  private StringBuilder build;

  @BeforeEach
  public void initData() {
    build = new StringBuilder();
    view = new SalvoView(build);
  }

  /**
   * Tests for display welcome
   */
  @Test
  public void displayWelcome() {
    view.displayWelcome();
    String welcomeMsg = "Welcome to BattleSalvo!\nWhat's your name?\n";
    assertEquals(welcomeMsg, build.toString());
  }

  /**
   * Tests for display message
   */
  @Test
  public void displayMessage() {
    view.displayMessage("Insert Message Text");
    assertEquals("Insert Message Text\n", build.toString());
    initData();
    view.displayMessage("More Testing blah blah");
    assertEquals("More Testing blah blah\n", build.toString());
  }

  /**
   * Tests for display ship options
   */
  @Test
  public void displayShipOptions() {
    ArrayList<ShipType> ships = new ArrayList<>(
        Arrays.asList(SUBMARINE, CARRIER, DESTROYER, BATTLESHIP));
    view.displayShipOptions(ships);
    String options = "[SUBMARINE CARRIER DESTROYER BATTLESHIP]";
    assertEquals(options, build.toString());
  }

  /**
   * Tests for display board
   */
  @Test
  public void displayBoard() {
    char[][] grid = new char[5][5];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = 'P';
      }
    }
    view.displayBoard(grid);
    String gridChars = "P P P P P \nP P P P P \nP P P P P \nP P P P P \nP P P P P \n";
    assertEquals(gridChars, build.toString());
  }
}