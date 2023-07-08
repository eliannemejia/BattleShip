package cs3500.pa03;

/**
 * Represents the status of a ship in battle salvo
 */
public enum ShipStatus {
  SUNK,
  HIT,
  MISS,
  UNTOUCHED;

  /**
   * @return a char representation of this ship type
   */
  public char asChar() {
    if (this.equals(SUNK)) {
      return 'K';
    } else if (this.equals(HIT)) {
      return 'H';
    } else if (this.equals(MISS)) {
      return 'M';
    } else {
      return 'U';
    }
  }
}
