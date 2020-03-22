package g54327.humbug.model;

import g54327.humbug.model.Exceptions.LevelNotStartedException;
import g54327.humbug.model.animals.Animal;
import g54327.humbug.model.animals.Snail;

public class Game implements Model {
    private Board board;

    private Animal[] animals;

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Animal[] getAnimals() {
        return this.animals;
    }

    @Override
    public void startLevel(int level) {
        switch (level) {
            case 1:
                this.board = Board.getInitialBoard();
                this.animals = new Animal[]{
                        new Snail(new Position(0, 0))
                };
        }
    }

    @Override
    public boolean levelIsOver() {
        if (this.board == null || this.animals == null) {
            throw new LevelNotStartedException();
        }

        for (var animal : animals) {
            if (!animal.isOnStar()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move(Position position, Direction direction) {
        if (this.board == null || this.animals == null) {
            throw new LevelNotStartedException();
        }

        for (var animal : animals) {
            if (animal.getPositionOnBoard().equals(position)) {
                animal.move(this.board, direction, this.animals);
            }
        }
    }
}
