package g54327.humbug.model.animals;

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Exceptions.NullSquareException;
import g54327.humbug.model.Exceptions.PositionOutOfBoundException;
import g54327.humbug.model.Position;
import g54327.humbug.model.SquareType;

public class Spider extends Animal {
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    @Override
    public String toString() {
        return " \uD83D\uDD77 ";
    }

    @Override
    public Position move(Board board, Direction direction, Animal[] animals) {
        Position nextPosition = positionOnBoard.next(direction);

        try {
            board.getSquareType(nextPosition);
        } catch (PositionOutOfBoundException | NullSquareException e) {
            this.positionOnBoard = null;
            return null;
        }

        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().equals(nextPosition)) {
                if (board.getSquareType(positionOnBoard) == SquareType.STAR) {
                    this.onStar = true;
                    board.setSquareType(positionOnBoard, SquareType.GRASS);
                }
                return this.positionOnBoard;
            }
        }

        this.positionOnBoard = nextPosition;
        return this.move(board, direction, animals);
    }
}
