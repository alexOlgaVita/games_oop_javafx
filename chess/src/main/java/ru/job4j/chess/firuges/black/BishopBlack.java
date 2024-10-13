package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int x = position().getX();
        int y = position().getY();
        int xd = dest.getX();
        int yd = dest.getY();
        int size = (x > xd) ? x - xd : xd - x;
        Cell[] steps = new Cell[size];
        int deltaX = (x > xd) ? -1 : 1;
        int deltaY = (y > yd) ? -1 : 1;
        for (int index = 0; index < size; index++) {
            x += deltaX;
            y += deltaY;
            steps[index] = Cell.findBy(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        int x = source.getX();
        int y = source.getY();
        int xd = dest.getX();
        int yd = dest.getY();
        int sizeX = (x > xd) ? x - xd : xd - x;
        int sizeY = (y > yd) ? y - yd : yd - y;
        return sizeX == sizeY;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
