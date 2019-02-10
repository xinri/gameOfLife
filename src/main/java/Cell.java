import java.util.function.Predicate;

public enum Cell {
  ALIVE(nbAlive -> nbAlive < 2 || nbAlive > 3),
  DEAD(nbAlive -> nbAlive == 3);

  private final Predicate<Long> ruleForChangingState;

  Cell(final Predicate<Long> ruleForChangingState) {
    this.ruleForChangingState = ruleForChangingState;
  }

  public Cell getNextState(long nbSurroundingAliveCell) {
    return ruleForChangingState.test(nbSurroundingAliveCell) ?
        (this == ALIVE ? DEAD : ALIVE) : this;
  }

  public static boolean isOutOfBoundCell(
      int row, int column, int height, int length) {
    return (row < 0 || row >= height || column < 0 || column >= length);
  }
}
