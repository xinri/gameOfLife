import java.util.Arrays;

public class Board {

  private Cell[][] board;
  private int boardLength;
  private int boardHeight;

  public void setBoard(String[] boardRepresentation) {

    this.boardLength = boardRepresentation[0]
        .replaceAll(" ", "")
        .length();

    this.boardHeight = boardRepresentation.length;

    this.board = BoardUtils.toCellRepresentation(boardRepresentation, boardLength, boardHeight);
  }

  public void nextStep() {

    Cell[][] updatedBoard = new Cell[boardHeight][boardLength];

    for (int row = 0; row < boardHeight; row++) {
      for (int column = 0; column < boardLength; column++) {

        long aliveCells = getSurroundedAliveCellsCount(row, column);

        updatedBoard[row][column] =
            board[row][column].getNextState(aliveCells);
      }
    }

    this.board = updatedBoard;
  }

  private long getSurroundedAliveCellsCount(int row, int column) {
    return Arrays.asList(
        getCell(row - 1, column - 1),
        getCell(row, column - 1),
        getCell(row + 1, column - 1),
        getCell(row - 1, column),
        getCell(row + 1, column),
        getCell(row - 1, column + 1),
        getCell(row, column + 1),
        getCell(row + 1, column + 1)).stream()
        .filter(cell -> cell == Cell.ALIVE)
        .count();
  }

  private Cell getCell(int row, int column) {
    return Cell.isOutOfBoundCell(row, column, boardHeight, boardLength) ?
        Cell.DEAD : board[row][column];
  }

  public String[] getBoard() {
    return BoardUtils.toStringRepresentation(board);
  }
}
