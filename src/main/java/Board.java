import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {

  private Cell[][] board;

  public void setBoard(String[] boardRepresentation) {

    int length = boardRepresentation[0]
        .replaceAll(" ", "")
        .length();

    int height = boardRepresentation.length;

    this.board = new Cell[height][length];

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < length; i++) {
        if (boardRepresentation[j].charAt(i*2) == '-') {
          board[j][i] = Cell.DEAD;
        } else {
          board[j][i] = Cell.ALIVE;
        }
      }
    }
  }

  public void nextStep() {

  }

  public String[] getBoard() {
    return Arrays.stream(board)
        .map(line ->
            Arrays.stream(line)
              .map(cell -> cell == Cell.ALIVE ? "*" : "-")
              .collect(Collectors.joining(" ")))
        .toArray(String[]::new);
  }
}
