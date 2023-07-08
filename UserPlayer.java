package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a user controlled battle salvo player
 */
public class UserPlayer extends AbstractPlayer {
  private final String name;
  private final UserShotData shotData;

  /**
   * @param name     the name of this player
   * @param shotData the shot data for this player
   * @param game     the game board for this player
   */
  public UserPlayer(String name, UserShotData shotData, Board game) {
    super(game);
    this.shotData = shotData;
    this.name = name;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return this.name;
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
  public List<Ship> setup(int width, int height, Map<ShipType, Integer> specifications) {
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
    shotData.setAmo(ships.size());
    shotData.setBoards(gameBoard, opBoard);
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
    return shotData.getShots();
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    List<Coord> totalShots = shotData.getShots();
    Coord success;
    for (int i = 0; i < shotsThatHitOpponentShips.size(); i++) {
      success = shotsThatHitOpponentShips.get(i);
      opBoard.updateStatus(success, ShipStatus.HIT);
      for (int j = 0; j < totalShots.size(); j++) {
        if (totalShots.get(j).sameLoc(success)) {
          totalShots.remove(j);
        }
      }
    }
    for (Coord fail : totalShots) {
      opBoard.updateStatus(fail, ShipStatus.MISS);
    }
    shotData.postRound();
  }
}
