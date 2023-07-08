package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a Battle Salvo player
 */
public class AbstractPlayer implements Player {
  protected final Board gameBoard;
  protected Board opBoard;

  /**
   * @param board the game board for this player
   */
  public AbstractPlayer(Board board) {
    this.gameBoard = board;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "ABSTRACT";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    opBoard = new Board(width, height);
    ArrayList<Ship> ships = new ArrayList<>();
    int idx = 0;
    for (ShipType key : specifications.keySet()) {
      int shipCount = specifications.get(key);
      int count = 0;
      Ship s = gameBoard.placeShip(key, shipCount, idx);
      while (count < shipCount) {
        ships.add(s);
        count++;
        idx++;
      }
    }
    return ships;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    int shotsRemaining = gameBoard.remainingShips();
    int shotsTaken = 0;
    Coord nextShot;
    ArrayList<Coord> shots = new ArrayList<>();
    while (shotsTaken < shotsRemaining) {
      nextShot = opBoard.findBestShot();
      shots.add(nextShot);
      shotsTaken++;
    }
    return shots;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    ArrayList<Coord> gotHit = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      if (gameBoard.shipExists(c)) {
        gotHit.add(c);
        gameBoard.updateStatus(c, ShipStatus.HIT);
      }
    }
    if (gameBoard.remainingShips() == 0) {
      endGame(GameResult.LOSE, "All ships have been sunk!");
    }
    return gotHit;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord loc : shotsThatHitOpponentShips) {
      opBoard.updateStatus(loc, ShipStatus.HIT);
    }
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    gameBoard.endGame(result.opposite(), reason);
  }
}
