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

package g54327.humbug.model;

import g54327.humbug.model.Animals.Animal;
import g54327.humbug.model.Animals.Snail;
import g54327.humbug.model.Exceptions.AnimalDiesException;
import g54327.humbug.model.Exceptions.LevelNotStartedException;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;

/**
 * Game class
 *
 * @author Andrew SASSOYE
 * @version 1.0.0
 * @since 0.2.0
 */
public class Game implements Model {
    private Board board;

    private Animal[] animals;

    /**
     * {@inheritDoc}
     */
    @Override
    public Board getBoard() {
        return this.board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Animal[] getAnimals() {
        return this.animals;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Position position, Direction direction) {
        if (this.board == null || this.animals == null) {
            throw new LevelNotStartedException();
        }

        for (var animal : animals) {
            if (animal.getPositionOnBoard().equals(position)) {
                Position newPosition = animal.move(this.board, direction, this.animals);

                if (newPosition == null) {
                    throw new AnimalDiesException();
                }
            }
        }
    }
}
