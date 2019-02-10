import java.util.Arrays;
import java.util.stream.Collectors;

public final class BoardUtils {

  private BoardUtils() {
    throw new IllegalStateException("reflexion not allowed");
  }

  public static String[] toStringRepresentation(Cell[][] boardToDisplay) {
    return Arrays.stream(boardToDisplay)
        .map(line ->
            Arrays.stream(line)
                .map(cell -> cell == Cell.ALIVE ? "*" : "-")
                .collect(Collectors.joining(" ")))
        .toArray(String[]::new);
  }

  public static Cell[][] toCellRepresentation(
      String[] boardRepresentation, int length, int height) {

    Cell[][] cellBoard = new Cell[height][length];
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < length; column++) {
        if (boardRepresentation[row].charAt(column * 2) == '-') {
          cellBoard[row][column] = Cell.DEAD;
        } else {
          cellBoard[row][column] = Cell.ALIVE;
        }
      }
    }

    return cellBoard;
  }
}
