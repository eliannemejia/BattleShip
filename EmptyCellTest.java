package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in EmptyCell.Class
 */
class EmptyCellTest {
  private EmptyCell empty;

  /**
   * Initialize the testing fields
   */
  @BeforeEach
  public void initData() {
    empty = new EmptyCell();
  }

  /**
   * Tests for as char
   */
  @Test
  public void asChar() {
    assertEquals('0', empty.asChar());
    empty.updateStatus(ShipStatus.MISS);
    assertEquals('M', empty.asChar());
  }

  /**
   * Tests for been hit
   */
  @Test
  public void beenHit() {
    assertFalse(empty.beenHit());
    empty.updateStatus(ShipStatus.HIT);
    assertTrue(empty.beenHit());
    empty.updateStatus(ShipStatus.SUNK);
    assertTrue(empty.beenHit());
  }

  /**
   * Tests for is empty
   */
  @Test
  public void isEmpty() {
    assertTrue(empty.isEmpty());
  }

  /**
   * Tests for single parameter update status
   */
  @Test
  public void updateStatus() {
    empty.updateStatus(ShipStatus.HIT);
    assertTrue(empty.beenHit());
    empty.updateStatus(ShipStatus.MISS);
    assertFalse(empty.beenHit());
    empty.updateStatus(ShipStatus.SUNK);
    assertTrue(empty.beenHit());
  }

  /**
   * Tests for two parameter update status
   */
  @Test
  public void testUpdateStatus() {
    empty.updateStatus(new Coord(6, 66), ShipStatus.HIT);
    assertTrue(empty.beenHit());
  }

  /**
   * Tests for same piece
   */
  @Test
  public void samePiece() {
    GamePiece alsoEmpty = new EmptyCell();
    assertTrue(empty.samePiece(alsoEmpty));
    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(4, 2));
    coords.add(new Coord(5, 6));
    GamePiece ship = new Ship(ShipType.BATTLESHIP, coords);
    assertFalse(empty.samePiece(ship));
  }

  /**
   * Tests for same ship
   */
  @Test
  public void sameShip() {
    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(4, 2));
    coords.add(new Coord(5, 6));
    Ship ship = new Ship(ShipType.BATTLESHIP, coords);
    assertFalse(empty.sameShip(ship));
  }

  /**
   * Tests for sunk
   */
  @Test
  public void sunk() {
    assertFalse(empty.sunk());
    empty.updateStatus(ShipStatus.SUNK);
    assertTrue(empty.sunk());
  }
}