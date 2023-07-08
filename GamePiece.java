package cs3500.pa03;

/**
 * Represents a battle salvo game piece
 */
public interface GamePiece {
  /**
   * @return a character reprsentation of this game piece
   */
  char asChar();

  /**
   * @return has this game piece been hit
   */
  boolean beenHit();

  /**
   * @return is this game piece empty
   */
  boolean isEmpty();

  /**
   * @param loc    the desired update location
   * @param status the new ship status for given location
   */
  void updateStatus(Coord loc, ShipStatus status);

  /**
   * @param status the new ship status for this game piece
   */
  void updateStatus(ShipStatus status);

  /**
   * @param other the game piece to be compared to
   * @return is this game piece the same as the given one
   */
  boolean samePiece(GamePiece other);

  /**
   * @param other the empty cell to be compared to
   * @return is this game piece the same as the given empty cell
   */
  boolean sameCell(EmptyCell other);

  /**
   * @param other the ship to be compared to
   * @return is this game piece the same as the given ship
   */
  boolean sameShip(Ship other);

  /**
   * @return has this game piece been sunk
   */
  boolean sunk();
}
