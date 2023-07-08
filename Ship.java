package cs3500.pa03;

import java.util.ArrayList;

/**
 * Represents a ship in battle salvo
 */
public class Ship implements GamePiece {
  private final ShipType type;
  private final ArrayList<Coord> occupies;
  private ShipStatus status;

  /**
   * @param st   the ship type for this ship
   * @param locs the locations occupied by this ship
   */
  public Ship(ShipType st, ArrayList<Coord> locs) {
    type = st;
    occupies = locs;
    status = ShipStatus.UNTOUCHED;
  }

  /**
   * @return a character representation of this ship
   */
  @Override
  public char asChar() {
    return type.asChar();
  }

  /**
   * @return a character representation
   */
  public char[] asChars() {
    char[] chars = new char[occupies.size()];
    if (sunk()) {
      for (int i = 0; i < occupies.size(); i++) {
        chars[i] = ShipStatus.SUNK.asChar();
      }
    } else {
      for (int i = 0; i < occupies.size(); i++) {
        if (occupies.get(i).getStatus() == ShipStatus.HIT) {
          chars[i] = ShipStatus.HIT.asChar();
        } else {
          chars[i] = this.asChar();
        }
      }
    }
    return chars;
  }

  /**
   * @return has this ship been hit
   */
  public boolean beenHit() {
    boolean hit = false;
    for (Coord c : occupies) {
      if (c.getStatus() == ShipStatus.HIT) {
        hit = true;
        break;
      }
    }
    return hit;
  }

  /**
   * @return has this ship been sunk
   */
  public boolean sunk() {
    boolean sunk = false;
    for (Coord c : occupies) {
      if (c.getStatus() == ShipStatus.HIT) {
        sunk = true;
      } else {
        sunk = false;
        break;
      }
    }
    return sunk;
  }

  /**
   * @return is this game piece empty
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * @param status the new status for this ship
   */
  public void updateStatus(Coord loc, ShipStatus status) {
    updateStatus(status);
    for (Coord c : occupies) {
      if (c.sameLoc(loc)) {
        c.updateStatus(status);
        break;
      }
    }
  }

  /**
   * @param status the new status for this ship
   */
  @Override
  public void updateStatus(ShipStatus status) {
    this.status = status;
  }

  /**
   * @param other the game piece to be compared to
   * @return is this ship the same as the given game piece
   */
  @Override
  public boolean samePiece(GamePiece other) {
    return other.sameShip(this);
  }

  /**
   * @param other the empty cell to be compared to
   * @return is this ship the same as the given cell
   */
  @Override
  public boolean sameCell(EmptyCell other) {
    return false;
  }

  /**
   * @param other the ship to be compared to
   * @return is this ship the same as the given one
   */
  public boolean sameShip(Ship other) {
    return this.type.equals(other.type)
        && this.occupies.equals(other.occupies);
  }
}
