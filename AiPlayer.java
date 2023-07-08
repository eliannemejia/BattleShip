package cs3500.pa03;

/**
 * Represents the AI opponent for battle salvo
 */
public class AiPlayer extends AbstractPlayer {

  public AiPlayer(Board board) {
    super(board);
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "Robo Salvo";
  }
}
