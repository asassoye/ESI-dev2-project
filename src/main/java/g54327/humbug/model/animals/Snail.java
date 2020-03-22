package g54327.humbug.model.animals;

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Exceptions.NullSquareException;
import g54327.humbug.model.Exceptions.PositionOutOfBoundException;
import g54327.humbug.model.Position;
import g54327.humbug.model.SquareType;

public class Snail extends Animal {

    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    @Override
    public String toString() {
        return " \uD83D\uDC0C";
    }

    @Override
    public Position move(Board board, Direction direction, Animal[] animals) {
        Position nextPosition = positionOnBoard.next(direction);

        try {
            board.getSquareType(nextPosition);
        } catch (NullSquareException | PositionOutOfBoundException e) {
            this.positionOnBoard = null;
            return null;
        }

        for (Animal animal : animals) {
            if (!animal.isOnStar() && animal.getPositionOnBoard().equals(nextPosition)) {
                return this.positionOnBoard;
            }
        }

        this.positionOnBoard = nextPosition;

        if (board.getSquareType(positionOnBoard) == SquareType.STAR) {
            this.onStar = true;
            board.setSquareType(positionOnBoard, SquareType.GRASS);
        }

        return this.positionOnBoard;
    }
}
