package g54327.humbug.controller;

import g54327.humbug.model.Direction;
import g54327.humbug.model.Exceptions.AnimalDiesException;
import g54327.humbug.model.Game;
import g54327.humbug.model.Model;
import g54327.humbug.model.Position;
import g54327.humbug.view.text.InterfaceView;

public class Controller {
    private Model game;

    private InterfaceView view;

    public Controller(Model game, InterfaceView view) {
        this.game = game;
        this.view = view;
    }

    public void startGame() {
        this.game = new Game();
        this.game.startLevel(1);

        while (!this.game.levelIsOver()) {
            this.display();
            Position position = this.view.askPosition();
            Direction direction = this.view.askDirection();

            try {
                game.move(position, direction);
            } catch (AnimalDiesException e) {
                view.displayError("Animal Died. GAME OVER");
                break;
            }
        }

        if (this.game.levelIsOver()) {
            this.view.displayMessage("LEVEL COMPLETED");
        }

    }

    private void display() {
        this.view.displayBoard(game.getBoard(), game.getAnimals());
    }
}
