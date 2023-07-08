package cs3500.pa03;

import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in UserPlayer.Class
 */
class UserPlayerTest {
  Board gameBoard;
  Player mockUser;
  UserShotData shotData;
  HashMap<ShipType, Integer> ships = new HashMap<>();

  /**
   * Initializing the testing fields
   */
  @BeforeEach
  void initData() {
    gameBoard = new Board(6, 6);
    shotData = new UserShotData();
    mockUser = new UserPlayer("Elianne", shotData, gameBoard);
    ships.put(CARRIER, 1);
    ships.put(BATTLESHIP, 1);
    ships.put(DESTROYER, 1);
    ships.put(SUBMARINE, 1);
    mockUser.setup(6, 6, ships);
  }

  /**
   * Tests for name
   */
  @Test
  void name() {
    assertEquals("Elianne", mockUser.name());
  }

  /**
   * Tests for setup
   */
  @Test
  void setup() {
    assertThrows(IllegalArgumentException.class, () -> mockUser.setup(4, 4, ships));
    ships = new HashMap<>();
    ships.put(CARRIER, 1);
    ships.put(BATTLESHIP, 2);
    ships.put(DESTROYER, 2);
    ships.put(SUBMARINE, 1);
    List<Ship> sample = mockUser.setup(6, 6, ships);
    assertEquals(6, sample.size());
  }

  /**
   * Tests for take shots
   */
  @Test
  void takeShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 1));
    shots.add(new Coord(0, 2));
    shots.add(new Coord(3, 5));
    shotData.takeShot(new Coord(0, 1));
    shotData.takeShot(new Coord(0, 2));
    shotData.takeShot(new Coord(3, 5));
    List<Coord> actShots = mockUser.takeShots();
    for (int i = 0; i < actShots.size(); i++) {
      assertTrue(actShots.get(i).sameLoc(shots.get(i)));
    }
  }

  /**
   * Tests for report damage
   */
  @Test
  void reportDamage() {
    List<Coord> opposition = new ArrayList<>();
    opposition.add(new Coord(0, 0));
    opposition.add(new Coord(0, 5));
    opposition.add(new Coord(2, 3));
    opposition.add(new Coord(5, 5));
    opposition.add(new Coord(5, 3));
    opposition.add(new Coord(4, 4));
    List<Coord> gotHit = new ArrayList<>();
    gotHit.add(new Coord(0, 0));
    gotHit.add(new Coord(0, 5));
    gotHit.add(new Coord(2, 3));
    List<Coord> actGotHit = mockUser.reportDamage(opposition);
  }

  /**
   * Tests for successful hits
   */
  @Test
  void successfulHits() {
    shotData.takeShot(new Coord(0, 0));
    shotData.takeShot(new Coord(0, 5));
    shotData.takeShot(new Coord(2, 3));
    shotData.takeShot(new Coord(5, 5));
    shotData.takeShot(new Coord(5, 3));
    shotData.takeShot(new Coord(4, 4));
    List<Coord> success = new ArrayList<>();
    success.add(new Coord(0, 0));
    success.add(new Coord(0, 5));
    success.add(new Coord(2, 3));
    mockUser.successfulHits(success);
    List<Coord> fails = new ArrayList<>();
    fails.add(new Coord(5, 5));
    fails.add(new Coord(5, 3));
    fails.add(new Coord(4, 4));
    char[][] op = shotData.opToChar();
    int x;
    int y;
    for (Coord c : success) {
      x = c.getXpos();
      y = c.getYpos();
      assertEquals('H', op[x][y]);
    }

    for (Coord fail : fails) {
      x = fail.getXpos();
      y = fail.getYpos();
      assertEquals('M', op[x][y]);
    }
  }
}