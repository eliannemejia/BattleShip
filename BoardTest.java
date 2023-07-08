package cs3500.pa03;

import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in Board.Class
 */
class BoardTest {
  private Board testBoard;
  private HashMap<ShipType, Integer> fleet;

  @BeforeEach
  public void initData() {
    fleet = new HashMap<>();
    testBoard = new Board(7);
  }

  /**
   * Tests for constructor failures
   */
  @Test
  public void constructorTest() {
    assertThrows(IllegalArgumentException.class, () -> new Board(17, 14));
    assertThrows(IllegalArgumentException.class, () -> new Board(4, 6));
  }

  /**
   * Tests for valid dimensions
   */
  @Test
  public void validDimensions() {
    assertFalse(Board.validDimensions(3, 4));
    assertFalse(Board.validDimensions(420, 69));
    assertTrue(Board.validDimensions(6, 6));
    assertTrue(Board.validDimensions(15, 15));
  }

  /**
   * Tests for valid fleet
   */
  @Test
  public void validFleet() {
    fleet.put(CARRIER, 0);
    fleet.put(SUBMARINE, 1);
    fleet.put(BATTLESHIP, 2);
    fleet.put(DESTROYER, 5);
    assertFalse(Board.validFleet(fleet, 10));
    initData();
    fleet.put(CARRIER, 1);
    fleet.put(SUBMARINE, 2);
    fleet.put(BATTLESHIP, 1);
    fleet.put(DESTROYER, 1);
    assertFalse(Board.validFleet(fleet, 4));
    initData();
    fleet.put(CARRIER, 1);
    fleet.put(SUBMARINE, 2);
    fleet.put(BATTLESHIP, 2);
    fleet.put(DESTROYER, 1);
    assertTrue(Board.validFleet(fleet, 6));
  }

  /**
   * Tests for find best shot
   */
  @Test
  public void findBestShot() {
    assertTrue(testBoard.findBestShot().sameLoc(new Coord(4, 2)));
    testBoard.updateStatus(new Coord(3, 2), ShipStatus.HIT);
    assertTrue(testBoard.findBestShot().sameLoc(new Coord(4, 2)));
  }

  /**
   * Tests for random shot
   */
  @Test
  public void randomShot() {
    Coord shot;
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(4, 2));
    shots.add(new Coord(3, 4));
    shots.add(new Coord(4, 4));
    shots.add(new Coord(4, 5));
    shots.add(new Coord(0, 0));
    shots.add(new Coord(0, 0));
    int idx = 0;
    while (idx < 6) {
      shot = testBoard.randomShot();
      System.out.println("(" + shot.getXpos() + ", " + shot.getYpos() + ")");
      assertTrue(shot.sameLoc(shots.get(idx)));
      idx++;
    }
  }

  /**
   * Tests for place ship
   */
  @Test
  public void placeShip() {
    testBoard.placeShip(CARRIER, 1, 0);
    testBoard.placeShip(BATTLESHIP, 2, 1);
    testBoard.placeShip(DESTROYER, 2, 3);
    testBoard.placeShip(SUBMARINE, 1, 5);
    char[] carrier = new char[] {'C', 'C', 'C', 'C', 'C', 'C'};
    char[] battleship = new char[] {'B', 'B', 'B', 'B', 'B', '0'};
    char[] destroyer = new char[] {'D', 'D', 'D', 'D', '0', '0'};
    char[] submarine = new char[] {'S', 'S', 'S', '0', '0', '0'};
    char[][] board =
        new char[][] {carrier, battleship, battleship, destroyer, destroyer, submarine};
    char[][] actBoard = testBoard.gridToChar();
    for (int i = 0; i < actBoard.length; i++) {
      for (int j = 0; j < actBoard[0].length; j++) {
        assertEquals(board[i][j], actBoard[i][j]);
      }
    }
  }

  @Test
  void validPlacement() {
  }

  @Test
  void remainingShips() {
  }

  /**
   * Tests for update ships
   */
  @Test
  public void updateShips() {
    testBoard.placeShip(CARRIER, 1, 0);
    testBoard.placeShip(BATTLESHIP, 2, 1);
    testBoard.placeShip(DESTROYER, 2, 3);
    testBoard.placeShip(SUBMARINE, 1, 5);
    testBoard.updateStatus(new Coord(5, 0), ShipStatus.HIT);
    testBoard.updateStatus(new Coord(5, 1), ShipStatus.HIT);
    testBoard.updateStatus(new Coord(5, 2), ShipStatus.HIT);
    testBoard.updateShips();
    char[] carrier = new char[] {'C', 'C', 'C', 'C', 'C', 'C'};
    char[] battleship = new char[] {'B', 'B', 'B', 'B', 'B', '0'};
    char[] destroyer = new char[] {'D', 'D', 'D', 'D', '0', '0'};
    char[] submarine = new char[] {'K', 'K', 'K', '0', '0', '0'};
    char[][] board =
        new char[][] {carrier, battleship, battleship, destroyer, destroyer, submarine};
    char[][] actBoard = testBoard.gridToChar();
    for (int i = 0; i < actBoard.length; i++) {
      for (int j = 0; j < actBoard[0].length; j++) {
        assertEquals(board[i][j], actBoard[i][j]);
      }
    }
  }

  /**
   * Tests for game over
   */
  @Test
  public void gameOver() {
    assertFalse(testBoard.gameOver());
    testBoard.endGame(GameResult.LOSE, "Lost");
    assertTrue(testBoard.gameOver());
  }
}