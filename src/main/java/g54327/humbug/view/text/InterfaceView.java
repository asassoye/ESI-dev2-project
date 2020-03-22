package g54327.humbug.view.text;

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Position;
import g54327.humbug.model.animals.Animal;

public interface InterfaceView {
    void displayBoard(Board board, Animal[] animals);

    Position askPosition();

    Direction askDirection();

    void displayError(String message);

    void displayMessage(String message);

}
