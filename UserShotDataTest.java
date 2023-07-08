package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in UserShotData.Class
 */
class UserShotDataTest {
  private UserShotData shotData;
  private Board userBoard;
  private Board opBoard;

  /**
   * Initialize the testing fields
   */
  @BeforeEach
  public void initData() {
    userBoard = new Board(6, 6);
    opBoard = new Board(6, 6);
    shotData = new UserShotData();
    shotData.setBoards(userBoard, opBoard);
    shotData.setAmo(3);
  }

  /**
   * Tests for shots taken
   */
  @Test
  public void shotsTaken() {
    assertEquals(0, shotData.shotsTaken());
    shotData.takeShot(new Coord(0, 1));
    shotData.takeShot(new Coord(0, 2));
    shotData.takeShot(new Coord(3, 5));
    assertEquals(3, shotData.shotsTaken());
  }

  /**
   * Tests take shot
   */
  @Test
  public void takeShot() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 1));
    shots.add(new Coord(0, 2));
    shots.add(new Coord(3, 5));
    shotData.takeShot(new Coord(0, 1));
    shotData.takeShot(new Coord(0, 2));
    shotData.takeShot(new Coord(3, 5));
    List<Coord> actShots = shotData.getShots();
    for (int i = 0; i < actShots.size(); i++) {
      assertTrue(actShots.get(i).sameLoc(shots.get(i)));
    }
  }

  /**
   * Tests for get shots
   */
  @Test
  public void getShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 1));
    shots.add(new Coord(0, 2));
    shots.add(new Coord(3, 5));
    shotData.takeShot(new Coord(0, 1));
    shotData.takeShot(new Coord(0, 2));
    shotData.takeShot(new Coord(3, 5));
    List<Coord> actShots = shotData.getShots();
    for (int i = 0; i < actShots.size(); i++) {
      assertTrue(actShots.get(i).sameLoc(shots.get(i)));
    }
  }

  /**
   * Tests for post round
   */
  @Test
  public void postRound() {
    shotData.takeShot(new Coord(0, 1));
    shotData.takeShot(new Coord(0, 2));
    shotData.takeShot(new Coord(3, 5));
    List<Coord> actShots = new ArrayList<>(shotData.getShots());
    assertEquals(3, actShots.size());
    shotData.postRound();
    List<Coord> updated = new ArrayList<>(shotData.getShots());
    assertEquals(0, updated.size());
  }

  /**
   * Tests for shots remaining
   */
  @Test
  public void shotsRemaining() {
    assertTrue(shotData.shotsRemaining());
    shotData.takeShot(new Coord(0, 1));
    shotData.takeShot(new Coord(0, 2));
    shotData.takeShot(new Coord(3, 5));
    assertFalse(shotData.shotsRemaining());
  }

  /**
   * Tests for shots left
   */
  @Test
  public void shotsLeft() {
    assertThrows(IndexOutOfBoundsException.class,
        () -> shotData.takeShot(new Coord(6, 66)));
    assertEquals(3, shotData.shotsLeft());
    shotData.takeShot(new Coord(2, 4));
    assertEquals(2, shotData.shotsLeft());
    shotData.takeShot(new Coord(4, 3));
    assertEquals(1, shotData.shotsLeft());
    shotData.takeShot(new Coord(5, 2));
    assertEquals(0, shotData.shotsLeft());
  }

  /**
   * Tests for set amo
   */
  @Test
  public void setAmo() {
    shotData.setAmo(999);
    assertEquals(999, shotData.shotsLeft());
  }

  /**
   * Tests for set board
   */
  @Test
  public void setBoards() {
    shotData = new UserShotData();
    shotData.setBoards(userBoard, opBoard);
    char[][] chars = new char[6][6];
    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[0].length; j++) {
        chars[i][j] = '0';
      }
    }
    char[][] actChars = shotData.gridToChar();
    for (int i = 0; i < actChars.length; i++) {
      for (int j = 0; j < actChars[0].length; j++) {
        assertEquals(chars[i][j], actChars[i][j]);
      }
    }
  }

  /**
   * Tests for grid to char
   */
  @Test
  public void gridToChar() {
    char[][] chars = new char[6][6];
    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[0].length; j++) {
        chars[i][j] = '0';
      }
    }
    char[][] actChars = shotData.gridToChar();
    for (int i = 0; i < actChars.length; i++) {
      for (int j = 0; j < actChars[0].length; j++) {
        assertEquals(chars[i][j], actChars[i][j]);
      }
    }
  }

  /**
   * Tests for opToChar
   */
  @Test
  public void opToChar() {
    char[][] chars = new char[6][6];
    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[0].length; j++) {
        chars[i][j] = '0';
      }
    }
    char[][] actChars = shotData.opToChar();
    for (int i = 0; i < actChars.length; i++) {
      for (int j = 0; j < actChars[0].length; j++) {
        assertEquals(chars[i][j], actChars[i][j]);
      }
    }
  }
}