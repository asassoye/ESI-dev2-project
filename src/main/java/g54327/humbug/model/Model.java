package g54327.humbug.model;

import g54327.humbug.model.animals.Animal;

public interface Model {
    Board getBoard();

    Animal[] getAnimals();

    void startLevel(int level);

    boolean levelIsOver();

    void move(Position position, Direction direction);
}
