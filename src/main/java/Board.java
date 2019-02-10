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

        long deadCells = getSurroundedDeadCellsCount(row, column);

        long cellAlive = 8 - deadCells;

        if (board[row][column] == Cell.ALIVE) {
          ruleForAliveCell(updatedBoard, row, column, cellAlive);
        } else {
          ruleForDeadCell(updatedBoard, row, column, cellAlive);
        }
      }
    }

    this.board = updatedBoard;
  }

  private void ruleForDeadCell(Cell[][] boardToUpdate, int row, int column, long cellAlive) {
    if (cellAlive == 3) {
      boardToUpdate[row][column] = Cell.ALIVE;
    } else {
      boardToUpdate[row][column] = Cell.DEAD;
    }
  }

  private void ruleForAliveCell(Cell[][] boardToUpdate, int row, int column, long cellAlive) {
    if (cellAlive < 2 || cellAlive > 3) {
      boardToUpdate[row][column] = Cell.DEAD;
    } else {
      boardToUpdate[row][column] = Cell.ALIVE;
    }
  }

  private long getSurroundedDeadCellsCount(int row, int column) {
    return Arrays.asList(
        getCell(row - 1, column - 1),
        getCell(row, column - 1),
        getCell(row + 1, column - 1),
        getCell(row - 1, column),
        getCell(row + 1, column),
        getCell(row - 1, column + 1),
        getCell(row, column + 1),
        getCell(row + 1, column + 1)).stream()
        .filter(cell -> cell == Cell.DEAD)
        .count();
  }

  private Cell getCell(int row, int column) {
    if (row < 0 || row >= boardHeight) {
      return Cell.DEAD;
    }

    if (column < 0 || column >= boardLength) {
      return Cell.DEAD;
    }

    return board[row][column];
  }

  public String[] getBoard() {
    return BoardUtils.toStringRepresentation(board);
  }


}
