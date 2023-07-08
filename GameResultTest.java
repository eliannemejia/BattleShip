package cs3500.pa03;

import static cs3500.pa03.GameResult.DRAW;
import static cs3500.pa03.GameResult.LOSE;
import static cs3500.pa03.GameResult.WIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in GameResult.Class
 */
class GameResultTest {

  /**
   * Tests for to string
   */
  @Test
  public void testToString() {
    assertEquals("You won! :D", WIN.toString());
    assertEquals("You lost! :(", LOSE.toString());
    assertEquals("You tied ._.", DRAW.toString());
  }

  /**
   * Tests for opposite
   */
  @Test
  public void opposite() {
    assertEquals(LOSE, WIN.opposite());
    assertEquals(WIN, LOSE.opposite());
    assertEquals(DRAW, DRAW.opposite());
  }
}