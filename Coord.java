package cs3500.pa03;

/**
 * represents a point on the cartesian plane
 */
public class Coord {
  private final int xpos;
  private final int ypos;
  private ShipStatus status;

  public Coord(int x, int y) {
    this.xpos = x;
    this.ypos = y;
  }

  /**
   * @return the x value for this coordinate
   */
  public int getXpos() {
    return this.xpos;
  }

  /**
   * @return the y value for this coordinate
   */
  public int getYpos() {
    return this.ypos;
  }

  /**
   * @param loc the location to compare to
   * @return whether the given location is the same as this one
   */
  public boolean sameLoc(Coord loc) {
    return this.getXpos() == loc.getXpos()
        && this.getYpos() == loc.getYpos();
  }

  /**
   * @param status the new status for this location
   */
  public void updateStatus(ShipStatus status) {
    this.status = status;
  }

  /**
   * @return the ship status for this location
   */
  public ShipStatus getStatus() {
    return status;
  }
}
