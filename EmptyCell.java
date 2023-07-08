package cs3500.pa03;

/**
 * An empty cell in a battle salvo game board
 */
public class EmptyCell implements GamePiece {
  private ShipStatus status;

  /**
   * @return character representation of this empty cell
   */
  @Override
  public char asChar() {
    if (status != null) {
      return status.asChar();
    } else {
      return '0';
    }
  }

  /**
   * @return has this cell been hit
   */
  @Override
  public boolean beenHit() {
    return this.status == ShipStatus.HIT
        || this.status == ShipStatus.SUNK;
  }

  /**
   * @return is this game piece empty
   */
  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * @param status the new status for this cell
   */
  @Override
  public void updateStatus(Coord loc, ShipStatus status) {
    this.status = status;
  }

  @Override
  public void updateStatus(ShipStatus status) {
    this.status = status;
  }

  /**
   * @param other the game piece to be compared to
   * @return is this empty cell the same as the given game piece
   */
  public boolean samePiece(GamePiece other) {
    return other.sameCell(this);
  }

  /**
   * @param other the empty cell to be compared to
   * @return is this empty cell the same as the given empty cell
   */
  @Override
  public boolean sameCell(EmptyCell other) {
    return true;
  }

  /**
   * @param other the ship to be compared to
   * @return is this empty cell the same as the given ship
   */
  @Override
  public boolean sameShip(Ship other) {
    return false;
  }

  /**
   * @return has this cell been sunk
   */
  @Override
  public boolean sunk() {
    return this.status == ShipStatus.SUNK;
  }
}
