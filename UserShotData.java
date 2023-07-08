package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents user shot data in a game of battle salvo
 */
public class UserShotData {
  private int initialShots;
  private ArrayList<Coord> shots = new ArrayList<>();
  private Board userBoard;
  private Board opBoard;

  /**
   * @return the amount of shots that have been taken so far
   */
  public int shotsTaken() {
    return shots.size();
  }

  /**
   * @param loc the location of the shot taken
   */
  public void takeShot(Coord loc) {
    if (userBoard.inRange(loc)) {
      shots.add(loc);
    } else {
      throw new ArrayIndexOutOfBoundsException("Shot location out of bounds");
    }
  }

  /**
   * @return the shots taken by the user during this round
   */
  public List<Coord> getShots() {
    return shots;
  }

  /**
   * resets the shots for a new round
   */
  public void postRound() {
    shots = new ArrayList<>();
  }

  /**
   * @return are there more shots left
   */
  public boolean shotsRemaining() {
    return shotsTaken() < initialShots;
  }

  /**
   * @return the number of available shots remaining
   */
  public int shotsLeft() {
    return initialShots - shotsTaken();
  }

  /**
   * @param size the
   */
  public void setAmo(int size) {
    initialShots = size;
  }

  /**
   * @param user the user game board
   * @param op   the opponent board
   */
  public void setBoards(Board user, Board op) {
    userBoard = user;
    opBoard = op;
  }

  /**
   * @return a character grid representation of the user's game board
   */
  public char[][] gridToChar() {
    return userBoard.gridToChar();
  }

  /**
   * @return a character grid representation of the opponents game board
   */
  public char[][] opToChar() {
    return opBoard.gridToChar();
  }
}
