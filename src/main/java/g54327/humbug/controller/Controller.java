/*
 * Copyright (C) 2020 Andrew SASSOYE
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the “Software”),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package g54327.humbug.controller;

import g54327.humbug.model.Exceptions.AnimalDiesException;
import g54327.humbug.model.Exceptions.NoLevelLeftException;
import g54327.humbug.model.Game;
import g54327.humbug.model.Levels.LevelStatus;
import g54327.humbug.model.Model;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;
import g54327.humbug.view.text.InterfaceView;

/**
 * @author Andrew SASSOYE
 * @version 1.1.0
 * @since 0.2.0
 */
public class Controller {
    private Model game;

    private final InterfaceView view;

    /**
     * Controller constructor
     *
     * @param game Model
     * @param view View
     */
    public Controller(Model game, InterfaceView view) {
        this.game = game;
        this.view = view;
        view.clearScreen();
    }

    /**
     * Start the game
     */
    public void startGame() {
        this.startGame(1);
    }

    /**
     * Start the game
     *
     * @param nLevel number of Level
     *
     * @since 1.1.0
     */
    public void startGame(int nLevel) {
        this.game = new Game();
        try {
            this.game.startLevel(nLevel);
        } catch (NoLevelLeftException e) {
            this.view.displayMessage("GAME COMPLETED");
            return;
        }

        while (this.game.getLevelStatus() == LevelStatus.IN_PROGRESS) {
            this.view.displayMessage(String.format("Level %d: %d moves left", nLevel, game.getRemainingMoves()));
            this.display();
            Position position = this.view.askPosition();
            Direction direction = this.view.askDirection();

            try {
                view.clearScreen();
                game.move(position, direction);
            } catch (AnimalDiesException e) {
                view.clearScreen();
                view.displayError("Animal Died. Try again");
                this.startGame(nLevel);
                break;
            }
        }

        if (this.game.getLevelStatus() == LevelStatus.FAIL) {
            view.clearScreen();
            this.view.displayError("YOU FAILED! TRY AGAIN");
            this.startGame(nLevel);

        }

        if (this.game.getLevelStatus() == LevelStatus.WIN) {
            view.clearScreen();
            this.view.displayMessage("LEVEL COMPLETED");
            this.startGame(nLevel + 1);
        }
    }

    /**
     * Display the Board
     */
    private void display() {
        this.view.displayBoard(game.getBoard(), game.getAnimals());
    }
}
