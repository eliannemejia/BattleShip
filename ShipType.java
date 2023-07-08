package cs3500.pa03;

/**
 * Represents the four battle salvo ship types
 */
public enum ShipType {
  CARRIER,
  BATTLESHIP,
  DESTROYER,
  SUBMARINE;

  /**
   * @return the size of this ship type
   */
  public int getSize() {
    if (this.equals(CARRIER)) {
      return 6;
    } else if (this.equals(BATTLESHIP)) {
      return 5;
    } else if (this.equals(DESTROYER)) {
      return 4;
    } else {
      return 3;
    }
  }

  /**
   * @return a char representation of this ship type
   */
  public char asChar() {
    return this.toString().charAt(0);
  }
}
