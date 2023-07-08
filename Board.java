package cs3500.pa03;

import static cs3500.pa03.ShipStatus.UNTOUCHED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents a battle salvo game board
 */
public class Board {
  private static final int absMax = 15;
  private static final int absMin = 6;
  private final HashMap<Ship, ShipStatus> fleet = new HashMap<>();
  private final GamePiece[][] grid;
  private boolean gameOver;
  private String gameMessage;
  Random rand;

  /**
   * @param width  the width of this board
   * @param height the height of this board
   */
  public Board(int width, int height) {
    if (width >= absMin && width <= absMax && height >= absMin && height <= absMax) {
      this.grid = new GamePiece[width][height];
    } else {
      throw new IllegalArgumentException("Height and width must be within bounds");
    }
    rand = new Random();
    initBoard();
  }

  /**
   * @param seed seed for this boards Random object
   */
  public Board(int seed) {
    this.grid = new GamePiece[6][6];
    rand = new Random(seed);
    initBoard();
  }

  /**
   * @param width  the desired board width
   * @param height the desired board height
   * @return are these board dimensions valid
   */
  public static boolean validDimensions(int width, int height) {
    return (width >= absMin && width <= absMax && height >= absMin && height <= absMax);
  }

  /**
   * @param fleet a players fleet for a game of battle salvo
   * @return is this fleet valid or not
   */
  public static boolean validFleet(HashMap<ShipType, Integer> fleet, int max) {
    boolean valid = false;
    int sum = 0;
    for (Integer i : fleet.values()) {
      sum += i;
      if (i <= 0) {
        valid = false;
        break;
      } else {
        valid = true;
      }
    }
    if (sum > max) {
      valid = false;
    }
    return valid;
  }

  /**
   * Initializes the gameBoard
   */
  private void initBoard() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = new EmptyCell();
      }
    }
  }

  /**
   * @return the next best spot to fire at
   */
  public Coord findBestShot() {
    ArrayList<Coord> options = new ArrayList<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].beenHit()) {
          if (j < grid[0].length) {
            options.add(new Coord(i, j + 1));
          }
          if (j > 0) {
            options.add(new Coord(i, j - 1));
          }
          if (i < grid.length - 1) {
            options.add(new Coord(i + 1, j));
          }
          if (i > 0) {
            options.add(new Coord(i - 1, j));
          }
        }
      }
    }
    if (options.size() > 0) {
      return options.get(rand.nextInt(options.size()));
    } else {
      return this.randomShot();
    }
  }

  /**
   * @return a random location on the board that has not been hit yet
   */
  public Coord randomShot() {
    int x = rand.nextInt(grid.length);
    int y = rand.nextInt(grid[0].length);
    Coord coord = new Coord(x, y);
    if (!grid[coord.getXpos()][coord.getYpos()].beenHit()) {
      return coord;
    } else {
      return randomShot();
    }
  }

  /**
   * @param ship the ship type being placed
   */
  public Ship placeShip(ShipType ship, int count, int round) {
    Ship currShip;
    ArrayList<Coord> occupies;
    occupies = validPlacement(ship, round);
    currShip = new Ship(ship, occupies);
    for (Coord c : occupies) {
      grid[c.getXpos()][c.getYpos()] = currShip;
      fleet.put(currShip, UNTOUCHED);
    }
    count--;
    if (count > 0) {
      placeShip(ship, count, round + 1);
    }
    return currShip;
  }

  /**
   * @param ship the ship to be placed
   * @return whether there is enough space for this ship
   */
  public ArrayList<Coord> validPlacement(ShipType ship, int rowIdx) {
    ArrayList<Coord> place = new ArrayList<>();
    int len = ship.getSize();
    int colIdx = 0;
    while (colIdx < grid[0].length && grid[rowIdx][colIdx].isEmpty()) {
      if (place.size() == len) {
        break;
      }
      place.add(new Coord(rowIdx, colIdx));
      colIdx++;
    }
    return place;
  }

  /**
   * @return the number of un-sunk ships remaining
   */
  public int remainingShips() {
    int count = 0;
    for (Ship s : fleet.keySet()) {
      if (!s.sunk()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Updates the ship status of any ships that have been sunk
   */
  public void updateShips() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].sunk()) {
          grid[i][j].updateStatus(ShipStatus.SUNK);
        }
      }
    }
  }

  /**
   * @param loc    location of the affected ship
   * @param status the new status for the ship
   */
  public void updateStatus(Coord loc, ShipStatus status) {
    grid[loc.getXpos()][loc.getYpos()].updateStatus(loc, status);
  }

  /**
   * @param loc the queried coordinate
   * @return whether there is a ship at the given location
   */
  public boolean shipExists(Coord loc) {
    if (inRange(loc)) {
      return !grid[loc.getXpos()][loc.getYpos()].isEmpty();
    }
    return false;
  }

  /**
   * Creates a grid composed of the character representation of the contents
   * of this board
   */
  public char[][] gridToChar() {
    int idx;
    int h;
    char[][] asChars = new char[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].isEmpty()) {
          asChars[i][j] = grid[i][j].asChar();
        } else if (grid[i][j] instanceof Ship) {
          idx = j;
          h = 0;
          char[] chars = ((Ship) grid[i][j]).asChars();
          while (idx < asChars[0].length && h < chars.length) {
            asChars[i][idx] = chars[h];
            idx++;
            h++;
          }
          if (chars.length < asChars[0].length) {
            idx = asChars[0].length - chars.length;
            while (idx < grid[0].length - 1) {
              asChars[i][idx] = grid[i][idx].asChar();
              idx++;
            }
          }
        }
      }
    }
    return asChars;
  }

  /**
   * Ends this game of BattleSalvo
   */
  public void endGame(GameResult result, String reason) {
    this.gameOver = true;
    gameMessage = result.toString() + "\n" + reason;
  }

  /**
   * @return a message describing the game results
   */
  String postGame() {
    return gameMessage;
  }

  /**
   * @return the end of game results
   */
  public boolean gameOver() {
    return this.gameOver;
  }

  /**
   * @param c the desired shot location
   * @return is this shot within range
   */
  public boolean inRange(Coord c) {
    return c.getXpos() < grid[0].length
        && c.getYpos() < grid.length;
  }
}
