package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException() {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.G8));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.G8, Cell.F8);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from G8 to F8");
    }

    @Test
    public void whenMoveWay1CellThenOccupiedCellException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.G8));
        logic.add(new BishopBlack(Cell.F7));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.G8, Cell.F7);
        });
        assertThat(exception.getMessage()).isEqualTo("Ther're occupied cells.");
    }

    @Test
    public void whenMoveWay2CellsThenOccupiedCellException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.G8));
        logic.add(new BishopBlack(Cell.F7));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.G8, Cell.E6);
        });
        assertThat(exception.getMessage()).isEqualTo("Ther're occupied cells.");
    }

    @Test
    public void whenMoveWay1CellThenOk() throws OccupiedCellException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.G8));
        logic.move(Cell.G8, Cell.F7);
        logic.add(new BishopBlack(Cell.E6));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.E6, Cell.F7);
        });
        assertThat(exception.getMessage()).isEqualTo("Ther're occupied cells.");
    }

    @Test
    public void whenMoveWay2CellsThenOk() throws OccupiedCellException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.G8));
        logic.move(Cell.G8, Cell.E6);
        logic.add(new BishopBlack(Cell.F7));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.E6, Cell.G8);
        });
        assertThat(exception.getMessage()).isEqualTo("Ther're occupied cells.");
    }
}