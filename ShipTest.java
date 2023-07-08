package cs3500.pa03;

import static cs3500.pa03.ShipStatus.HIT;
import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for all methods in Ship.Class
 */
class ShipTest {
  private Ship carrier;
  private Ship battle;
  private Ship destroy;
  private Ship submarine;
  private ArrayList<Coord> coords;

  /**
   * Initializing the testing fields
   */
  @BeforeEach
  public void initData() {
    coords = new ArrayList<>();
    coords.add(new Coord(4, 2));
    coords.add(new Coord(5, 6));
    coords.add(new Coord(9, 9));
    coords.add(new Coord(3, 4));
    coords.add(new Coord(7, 8));
    coords.add(new Coord(6, 5));
    ArrayList<Coord> carry = new ArrayList<>();
    int idx = 0;
    while (carry.size() < CARRIER.getSize()) {
      carry.add(coords.get(idx));
      idx++;
    }
    carrier = new Ship(CARRIER, carry);
    ArrayList<Coord> battles = new ArrayList<>();
    idx = 0;
    while (battles.size() < BATTLESHIP.getSize()) {
      battles.add(coords.get(idx));
      idx++;
    }
    battle = new Ship(BATTLESHIP, battles);
    ArrayList<Coord> destroys = new ArrayList<>();
    idx = 0;
    while (destroys.size() < DESTROYER.getSize()) {
      destroys.add(coords.get(idx));
      idx++;
    }
    destroy = new Ship(DESTROYER, destroys);
    ArrayList<Coord> subs = new ArrayList<>();
    idx = 0;
    while (subs.size() < SUBMARINE.getSize()) {
      subs.add(coords.get(idx));
      idx++;
    }
    submarine = new Ship(SUBMARINE, subs);
  }

  /**
   * Tests for as char
   */
  @Test
  public void asChar() {
    assertEquals('C', carrier.asChar());
    assertEquals('B', battle.asChar());
    assertEquals('D', destroy.asChar());
    assertEquals('S', submarine.asChar());
  }

  /**
   * Tests for as chars
   */
  @Test
  public void asChars() {
    char[] car = new char[6];
    int idx = 0;
    while (idx < car.length) {
      car[idx] = carrier.asChar();
      idx++;
    }
    char[] carAct = carrier.asChars();
    for (int i = 0; i < carAct.length; i++) {
      assertEquals(car[i], carAct[i]);
    }
    initData();
    carrier.updateStatus(new Coord(5, 6), HIT);
    char[] updated = new char[car.length];
    for (int i = 0; i < car.length; i++) {
      updated[i] = car[i];
    }
    updated[1] = 'H';
    char[] carsAct = carrier.asChars();
    for (int i = 0; i < carsAct.length; i++) {
      assertEquals(updated[i], carsAct[i]);
    }
  }

  /**
   * Tests for been hit
   */
  @Test
  public void beenHit() {
    carrier.updateStatus(new Coord(5, 6), HIT);
    assertTrue(carrier.beenHit());
  }

  /**
   * Tests for sunk
   */
  @Test
  public void sunk() {
    carrier = new Ship(CARRIER, coords);
    for (Coord c : coords) {
      carrier.updateStatus(c, HIT);
    }
    assertTrue(carrier.sunk());
    initData();
    assertFalse(carrier.sunk());
  }

  /**
   * Tests for is empty
   */
  @Test
  public void isEmpty() {
    assertFalse(carrier.isEmpty());
  }


  /**
   * Tests for same piece
   */
  @Test
  public void samePiece() {
    GamePiece carryShip = new Ship(CARRIER, coords);
    assertTrue(carrier.samePiece(carryShip));
    ArrayList<Coord> fake = new ArrayList<>();
    fake.add(new Coord(4, 2));
    fake.add(new Coord(5, 6));
    GamePiece faked = new Ship(CARRIER, fake);
    assertFalse(carrier.samePiece(faked));
    assertFalse(carrier.samePiece(battle));
  }

  /**
   * Test for same cell
   */
  @Test
  public void sameCell() {
    EmptyCell empty = new EmptyCell();
    assertFalse(carrier.sameCell(empty));
  }
}