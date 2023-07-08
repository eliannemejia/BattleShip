package cs3500.pa03;

/**
 * Represents the result of a battle salvo game
 */
public enum GameResult {
  WIN,
  LOSE,
  DRAW;

  /**
   * @return a string representation of this game result
   */
  @Override
  public String toString() {
    if (this.equals(WIN)) {
      return "You won! :D";
    } else if (this.equals(LOSE)) {
      return "You lost! :(";
    } else {
      return "You tied ._.";
    }
  }

  /**
   * @return the opposite of this game result
   */
  public GameResult opposite() {
    if (this.equals(LOSE)) {
      return WIN;
    } else if (this.equals(WIN)) {
      return LOSE;
    } else {
      return DRAW;
    }
  }
}
