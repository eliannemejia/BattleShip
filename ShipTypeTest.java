package cs3500.pa03;

import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for all methods ShipType.Class
 */
class ShipTypeTest {

  /**
   * Tests for get size
   */
  @Test
  public void getSize() {
    assertEquals(6, CARRIER.getSize());
    assertEquals(5, BATTLESHIP.getSize());
    assertEquals(4, DESTROYER.getSize());
    assertEquals(3, SUBMARINE.getSize());
  }

  /**
   * Tests for as char
   */
  @Test
  public void asChar() {
    assertEquals('C', CARRIER.asChar());
    assertEquals('B', BATTLESHIP.asChar());
    assertEquals('D', DESTROYER.asChar());
    assertEquals('S', SUBMARINE.asChar());
  }
}