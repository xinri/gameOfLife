import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.Test;

public class BoardShould {

  @Test
  public void do_nothing_when_there_is_no_cell_alive() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "- - -",
            "- - -",
            "- - -"
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("- - -");
    assertThat(result[1]).isEqualTo("- - -");
    assertThat(result[2]).isEqualTo("- - -");
  }

  @Test
  public void die_when_the_cell_is_surround_by_4_dead_cells() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "- - -",
            "- * -",
            "- - -"
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("- - -");
    assertThat(result[1]).isEqualTo("- - -");
    assertThat(result[2]).isEqualTo("- - -");
  }

  @Test
  public void die_when_the_cell_is_surround_by_3_dead_cells() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "- - -",
            "- * *",
            "- - -"
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("- - -");
    assertThat(result[1]).isEqualTo("- - -");
    assertThat(result[2]).isEqualTo("- - -");
  }

  @Test
  public void die_when_there_are_4_cell_around_one() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "- * -",
            "* * *",
            "- * -"
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("* * *");
    assertThat(result[1]).isEqualTo("* - *");
    assertThat(result[2]).isEqualTo("* * *");
  }

  @Test
  public void die_when_there_is_only_one_cell() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "*",
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("-");
  }

  @Test
  public void alive_when_two_cell_around() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "- - -",
            "* * *",
            "- - -"
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("- * -");
    assertThat(result[1]).isEqualTo("- * -");
    assertThat(result[2]).isEqualTo("- * -");
  }

  @Test
  public void die_when_dead_cell_surround_by_more_than_3_cells_alive() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "* * *",
            "* - *",
            "* * *"
        });

    // when
    board.nextStep();
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("* - *");
    assertThat(result[1]).isEqualTo("- - -");
    assertThat(result[2]).isEqualTo("* - *");
  }

  @Test
  public void real_test() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "* * - * * * - * * - * * * - * *",
            "- - * - * - - * * * - * * * * -",
            "* * * * * - * - * * * * * * - *",
            "* - * * * * - * - * - * * * * *"
        });

    // when
    IntStream.range(0, 29)
        .forEach(i -> board.nextStep());
    String[] result = board.getBoard();

    // then

    assertThat(result[0]).isEqualTo("- - - - - - * * - - - - - - - -");
    assertThat(result[1]).isEqualTo("- - - - - * - - * - - - - - - -");
    assertThat(result[2]).isEqualTo("- - - - - - * - * - - - - - - -");
    assertThat(result[3]).isEqualTo("- - - - - - - * - - - - - - - -");
  }

  @Test
  public void real_test_when_over_74_step() {
    // given
    Board board = new Board();
    board.setBoard(
        new String[]{
            "- - - * - - - - - - - - - - - - - * - -",
            "- - - - - - * - - * * - - - - - - - - -",
            "- - - - - - - - - - - * * - - - - - - -",
            "- - - - - - * - - - - - - - * - - - - -",
            "- - - - - - - - - - - - * - - - - * * -",
            "- - - - - - - - - - - - - - - - * - - *",
            "- - - - - - - - - - - - - - - - - - - -",
            "* - * - - - - - - * - - - - - - - - - -",
            "- - - * - - - - - - - - - - - - - - - -",
            "- - - * - - - * - - * * - - - - - * - -",
            "- - - - - - - - - - - - - - - - - * - -",
            "* * - - - - - - - - - - - - - - - - - -",
            "- - - - - - - - * - * * - - - - - - - -",
            "- - - - - - - - - - - - - * - * - * - -",
            "- - - - * - - - - - - - - - - - * - - -",
            "- * - - - - - - - - - - * - - - - - - -"
        });

    // when
    IntStream.range(0, 74)
        .forEach(i -> board.nextStep());
    String[] result = board.getBoard();

    // then
    assertThat(result[0]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[1]).isEqualTo("- - - - - - - - - - * * - - - - - - - -");
    assertThat(result[2]).isEqualTo("- - - - - - - - - * - - * - - - - - - -");
    assertThat(result[3]).isEqualTo("- - - - - - - - - - * - * - - - - - - -");
    assertThat(result[4]).isEqualTo("- - - - - - - - - - - * - - - - - - - -");
    assertThat(result[5]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[6]).isEqualTo("- - - - - - - - - - * - - - - - - - - -");
    assertThat(result[7]).isEqualTo("- - - - - - - - - * - * - - - - - - - -");
    assertThat(result[8]).isEqualTo("- - - - - - - - - - * - - - - - - - - -");
    assertThat(result[9]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[10]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[11]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[12]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[13]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[14]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
    assertThat(result[15]).isEqualTo("- - - - - - - - - - - - - - - - - - - -");
  }
}
