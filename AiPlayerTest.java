package cs3500.pa03;

import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in AiPlayer.Class
 */
class AiPlayerTest {
  private Board gameBoard;
  private Player robot;
  private HashMap<ShipType, Integer> ships = new HashMap<>();


  /**
   * Initializing the testing fields
   */
  @BeforeEach
  public void initData() {
    gameBoard = new Board(6, 6);
    robot = new AiPlayer(gameBoard);
    ships.put(CARRIER, 1);
    ships.put(BATTLESHIP, 1);
    ships.put(DESTROYER, 1);
    ships.put(SUBMARINE, 1);
    robot.setup(6, 6, ships);
  }

  /**
   * Tests for name
   */
  @Test
  public void name() {
    assertEquals("Robo Salvo", robot.name());
  }

  /**
   * Tests for setup
   */
  @Test
  public void setup() {
    assertThrows(IllegalArgumentException.class, () -> robot.setup(4, 4, ships));
    ships = new HashMap<>();
    ships.put(CARRIER, 1);
    ships.put(BATTLESHIP, 2);
    ships.put(DESTROYER, 2);
    ships.put(SUBMARINE, 1);
    List<Ship> sample = robot.setup(6, 6, ships);
    assertEquals(6, sample.size());
  }

  /**
   * Tests for take shots
   */
  @Test
  public void takeShots() {
    gameBoard = new Board(4);
    robot.takeShots();
  }

  /**
   * Tests for successful hits
   */
  @Test
  public void successfulHits() {
    List<Coord> success = new ArrayList<>();
    success.add(new Coord(0, 0));
    success.add(new Coord(0, 5));
    success.add(new Coord(2, 3));
    success.add(new Coord(5, 5));
    success.add(new Coord(5, 3));
    success.add(new Coord(4, 4));
    robot.successfulHits(success);
  }

  @Test
  public void endGame() {
  }
}