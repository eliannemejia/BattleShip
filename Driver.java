package cs3500.pa03;

import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    SalvoView view = new SalvoView(System.out);
    Scanner scan = new Scanner(System.in);
    BattleSalvo bs = new BattleSalvo(view, scan);
    bs.welcomeUser();
    bs.playGame();
  }
}