package cs3500.pa03;

import static cs3500.pa03.ShipType.BATTLESHIP;
import static cs3500.pa03.ShipType.CARRIER;
import static cs3500.pa03.ShipType.DESTROYER;
import static cs3500.pa03.ShipType.SUBMARINE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a game of battle salvo
 */
public class BattleSalvo {
  private final Scanner scan;
  private Player consolePlayer;
  private UserShotData userShotData;
  private Player roboPlayer;
  private final SalvoView view;
  Board user;
  Board robot;
  private final ArrayList<ShipType> ships = new ArrayList<>(
      Arrays.asList(CARRIER, BATTLESHIP, DESTROYER, SUBMARINE));

  public BattleSalvo(SalvoView view, Scanner sc) {
    this.view = view;
    scan = sc;
  }

  /**
   * Starts this game of battle salvo
   */
  public void welcomeUser() {
    view.displayWelcome();
    String name = scan.nextLine();
    view.displayMessage("Hi " + name + "!");
    this.initBoard(name);
  }

  /**
   * Initializes the players and their game boards
   */
  private void initBoard(String name) {
    view.displayMessage("Please enter your desired board dimensions");
    view.displayMessage("Note: dimensions must be between 6 and 15 (inclusive)");
    int width = scan.nextInt();
    int height = scan.nextInt();
    if (Board.validDimensions(width, height)) {
      initFleet(width, height, name);
    } else {
      initBoard(name);
    }
  }

  /**
   * @param w the width of the board
   * @param h the height of the board
   */
  private void initFleet(int w, int h, String name) {
    int min = Math.min(w, h);
    view.displayMessage("Please select your fleet in order");
    view.displayMessage("Your fleet may not exceed " + min + " ships");
    view.displayShipOptions(ships);
    HashMap<ShipType, Integer> userFleet = new HashMap<>();
    for (ShipType st : ships) {
      userFleet.put(st, scan.nextInt());
    }
    if (Board.validFleet(userFleet, min)) {
      userShotData = new UserShotData();
      user = new Board(w, h);
      robot = new Board(w, h);
      consolePlayer = new UserPlayer(name, userShotData, user);
      consolePlayer.setup(w, h, userFleet);
      roboPlayer = new AiPlayer(robot);
      roboPlayer.setup(w, h, userFleet);
    } else {
      view.displayMessage("Your fleet may not exceed " + min + " ships");
      initFleet(w, h, name);
    }
  }

  /**
   * The shooting stage of BattleSalvo
   */
  private void shootingRound() {
    int x;
    int y;
    Coord shotLoc;
    List<Coord> roboShots = roboPlayer.takeShots();
    view.displayMessage("Please enter " + userShotData.shotsLeft() + " shots");
    while (userShotData.shotsRemaining()) {
      x = scan.nextInt();
      y = scan.nextInt();
      shotLoc = new Coord(y, x);
      try {
        userShotData.takeShot(shotLoc);
      } catch (IndexOutOfBoundsException e) {
        view.displayMessage(e.getMessage());
        view.displayMessage("Please enter a shot within range");
      }
    }
    List<Coord> userShots = consolePlayer.takeShots();
    postRound(roboShots, userShots);
  }

  /**
   * Advances to the next round of the game
   */
  private void nextRound() {
    shootingRound();
  }

  /**
   * Reports damage and successes after a shooting round
   */
  private void postRound(List<Coord> roboShots, List<Coord> userShots) {
    List<Coord> roboSuccess = consolePlayer.reportDamage(roboShots);
    List<Coord> userSuccess = roboPlayer.reportDamage(userShots);
    consolePlayer.successfulHits(userSuccess);
    roboPlayer.successfulHits(roboSuccess);
    view.displayMessage("Your Board:");
    view.displayBoard(userShotData.gridToChar());
    view.displayMessage("Opponent Board:");
    view.displayBoard(userShotData.opToChar());
  }

  /**
   * The game loop
   */
  public void playGame() {
    view.displayMessage("Your Board:");
    view.displayBoard(userShotData.gridToChar());
    view.displayMessage("Opponent Board:");
    view.displayBoard(userShotData.opToChar());
    while (!(user.gameOver() || robot.gameOver())) {
      nextRound();
    }
    if (robot.gameOver() && user.gameOver()) {
      view.displayMessage(GameResult.DRAW.toString());
    } else if (robot.gameOver()) {
      view.displayMessage(robot.postGame());
    }
  }
}
