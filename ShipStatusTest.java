package cs3500.pa03;

import static cs3500.pa03.ShipStatus.HIT;
import static cs3500.pa03.ShipStatus.MISS;
import static cs3500.pa03.ShipStatus.SUNK;
import static cs3500.pa03.ShipStatus.UNTOUCHED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in ShipStatus.Class
 */
class ShipStatusTest {


  /**
   * Tests for as char
   */
  @Test
  public void asChar() {
    assertEquals('K', SUNK.asChar());
    assertEquals('H', HIT.asChar());
    assertEquals('M', MISS.asChar());
    assertEquals('U', UNTOUCHED.asChar());
  }
}