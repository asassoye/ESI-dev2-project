package g54327.humbug.view.text;

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Position;

public interface InterfaceView {
    void displayBoard(Board board);

    Position askPosition();

    Direction askDirection();

    void displayError(String message);

}
