import static org.assertj.core.api.Assertions.assertThat;

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
  public void die_when_dead_cell_surround_by_more_than_3_cells_alive  () {
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
}
