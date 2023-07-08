package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in Coord.Class
 */
class CoordTest {
  private Coord c1;
  private Coord c2;

  /**
   * Initializing the test filed values
   */
  @BeforeEach
  public void initData() {
    c1 = new Coord(2, 3);
    c2 = new Coord(4, 5);
  }

  /**
   * Tests for get x
   */
  @Test
  public void getX() {
    assertEquals(2, c1.getXpos());
    assertEquals(4, c2.getXpos());
  }

  /**
   * Tests for get y
   */
  @Test
  public void getY() {
    assertEquals(3, c1.getYpos());
    assertEquals(5, c2.getYpos());
  }

  /**
   * Tests for same loc
   */
  @Test
  public void sameLoc() {
    Coord c1Dupe = new Coord(2, 3);
    assertTrue(c1.sameLoc(c1Dupe));
    assertFalse(c1.sameLoc(c2));
  }

  /**
   * Tests for update status
   */
  @Test
  public void updateStatus() {
    c1.updateStatus(ShipStatus.HIT);
    assertEquals(ShipStatus.HIT, c1.getStatus());
    c2.updateStatus(ShipStatus.SUNK);
    assertEquals(ShipStatus.SUNK, c2.getStatus());
  }

  /**
   * Tests for get status
   */
  @Test
  public void getStatus() {
    c1.updateStatus(ShipStatus.HIT);
    assertEquals(ShipStatus.HIT, c1.getStatus());
    c2.updateStatus(ShipStatus.SUNK);
    assertEquals(ShipStatus.SUNK, c2.getStatus());
  }
}