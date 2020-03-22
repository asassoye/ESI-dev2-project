package g54327.humbug.model.animals;

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Position;

public abstract class Animal {
    protected Position positionOnBoard;

    protected boolean onStar;

    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public static Animal getAnimal(Animal[] animals, Position position) {
        for (var animal : animals) {
            if (animal.positionOnBoard.equals(position)) {
                return animal;
            }
        }

        return null;
    }

    public abstract Position move(Board board, Direction direction, Animal[] animals);

    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public boolean isOnStar() {
        return onStar;
    }

    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }
}
