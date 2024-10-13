package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    void position() {
        Cell dest = Cell.A1;
        Cell destNot = Cell.A2;
        BishopBlack bishopBlack = new BishopBlack(dest);
        assertThat(bishopBlack.position().getX()).isEqualTo(dest.getX());
        assertThat(bishopBlack.position().getY()).isEqualTo(dest.getY());
        assertThat(bishopBlack.position().getX()).isEqualTo(destNot.getX());
        assertThat(bishopBlack.position().getY()).isNotEqualTo(destNot.getY());
    }

    @Test
    void copy() {
        Cell dest = Cell.A1;
        Cell dest2 = Cell.B2;
        BishopBlack bishopBlack = new BishopBlack(dest);
        Figure bishopBlack2 = bishopBlack.copy(dest2);
        System.out.println(bishopBlack2.getClass().getSimpleName());
        assertThat("BishopBlack").isEqualTo(bishopBlack2.getClass().getSimpleName());
        assertThat(bishopBlack2.position().getX()).isEqualTo(dest2.getX());
        assertThat(bishopBlack2.position().getY()).isEqualTo(dest2.getY());
    }

    //@Disabled("Когда будет реализован метод way(...)")
    @Test
    void when1CellslWayCorrect() {
        Cell dest = Cell.A1;
        Cell dest2 = Cell.B2;
        BishopBlack bishopBlack = new BishopBlack(dest);
        Cell[] expected = new Cell[]{Cell.B2};
        Cell[] result = bishopBlack.way(dest2);
        assertThat(result).containsExactly(expected);
    }

    //@Disabled("Когда будет реализован метод way(...)")
    @Test
    void when4CellsWayCorrect() {
        Cell dest = Cell.C1;
        Cell dest2 = Cell.G5;
        BishopBlack bishopBlack = new BishopBlack(dest);
        Cell[] expected = new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] result = bishopBlack.way(dest2);
        assertThat(result).containsExactly(expected);
    }

    @Test
    void whenWayNotDiagonal() {
        Cell dest = Cell.C1;
        Cell dest2 = Cell.F5;
        BishopBlack bishopBlack = new BishopBlack(dest);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    new BishopBlack(dest).way(dest2);
                });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from C1 to F5");
    }
}