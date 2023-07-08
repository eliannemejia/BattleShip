package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for battle salvo
 */
class BattleSalvoTest {
  private Scanner scan;
  private SalvoView view;
  private StringReader reader;
  private StringBuilder mockInput;
  private StringBuilder mockOutput;
  private BattleSalvo salvo;

  /**
   * Initialize the testing fields
   */
  @BeforeEach
  void initData() {
    mockInput = new StringBuilder();
    reader = new StringReader(mockInput.toString());
    scan = new Scanner(reader);
    mockOutput = new StringBuilder();
    view = new SalvoView(mockOutput);
    salvo = new BattleSalvo(view, scan);
  }

  /**
   * Tests for welcome user
   */
  @Test
  void welcomeUser() {
    mockInput.append("Elianne").append(System.lineSeparator());
    mockInput.append(6).append(System.lineSeparator());
    mockInput.append(6).append(System.lineSeparator());
    mockInput.append(1).append(System.lineSeparator());
    mockInput.append(2).append(System.lineSeparator());
    mockInput.append(2).append(System.lineSeparator());
    mockInput.append(1).append(System.lineSeparator());
    reader = new StringReader(mockInput.toString());
    scan = new Scanner(reader);
    mockOutput = new StringBuilder();
    view = new SalvoView(mockOutput);
    salvo = new BattleSalvo(view, scan);
    salvo.welcomeUser();
    StringBuilder sb = new StringBuilder();
    sb.append("Welcome to BattleSalvo!").append(System.lineSeparator());
    sb.append("What's your name?").append(System.lineSeparator());
    sb.append("Hi Elianne!").append(System.lineSeparator());
    sb.append("Please enter your desired board dimensions").append(System.lineSeparator());
    sb.append("Note: dimensions must be between 6 and 15 (inclusive)")
        .append(System.lineSeparator());
    sb.append("Please select your fleet in order").append(System.lineSeparator());
    sb.append("Your fleet may not exceed 6 ships").append(System.lineSeparator());
    sb.append("[CARRIER BATTLESHIP DESTROYER SUBMARINE]");
    assertEquals(sb.toString(), mockOutput.toString());
  }

  /**
   * Tests for play game
   */
  @Test
  void playGame() {
    mockInput.append("Elianne").append(System.lineSeparator());
    mockInput.append(6).append(System.lineSeparator());
    mockInput.append(6).append(System.lineSeparator());
    mockInput.append(1).append(System.lineSeparator());
    mockInput.append(2).append(System.lineSeparator());
    mockInput.append(2).append(System.lineSeparator());
    mockInput.append(1).append(System.lineSeparator());
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        mockInput.append(i);
        mockInput.append(" ");
        mockInput.append(j);
        mockInput.append(System.lineSeparator());
      }
    }
    reader = new StringReader(mockInput.toString());
    scan = new Scanner(reader);
    mockOutput = new StringBuilder();
    view = new SalvoView(mockOutput);
    salvo = new BattleSalvo(view, scan);
    salvo.welcomeUser();
    salvo.playGame();
    StringBuilder sb = new StringBuilder();
    sb.append("You won! :D").append(System.lineSeparator());
    sb.append("All ships have been sunk!").append(System.lineSeparator());
    int idx = mockOutput.length() - sb.length();
    mockOutput.delete(0, idx);
    assertEquals(sb.toString(), mockOutput.toString());
  }
}