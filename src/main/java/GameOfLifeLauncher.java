import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class GameOfLifeLauncher {

  private static final int NB_NEXT_STEP = 74;

  public static void main(String[] args) {
    GameOfLifeLauncher game = new GameOfLifeLauncher();
    game.openFileAndLaunchGame();
  }

  private void openFileAndLaunchGame() {
    ClassLoader classLoader = getClass().getClassLoader();

    File file = new File(classLoader.getResource("boardInput.txt").getFile());
    Board board = new Board();

    try (Scanner scanner = new Scanner(file)) {

      if (!scanner.hasNextLine()) {
        throw new IllegalStateException("Must input the first line as the actual nb step");
      }

      int nbStep = Integer.valueOf(scanner.nextLine());

      if (!scanner.hasNextLine()) {
        throw new IllegalStateException("No Data");
      }

      List<String> boardRepresentation = new ArrayList<>();

      while (scanner.hasNextLine()) {
        boardRepresentation.add(scanner.nextLine());
      }

      board.setBoard(boardRepresentation.toArray(new String[boardRepresentation.size()]));

      int nbExecution = nbStep >= NB_NEXT_STEP ? 0 : NB_NEXT_STEP - nbStep;

      IntStream.range(0, nbExecution)
          .forEach(i -> board.nextStep());

      Arrays.stream(board.getBoard())
          .forEach(line -> System.out.println(line));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
