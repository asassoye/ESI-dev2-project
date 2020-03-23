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

import g54327.humbug.model.animals.Animal;

/**
 * Model interface
 *
 * @author Andrew SASSOYE
 * @version 1.0.0
 * @since 0.2.0
 */
public interface Model {
    /**
     * this.board getter
     *
     * @return this.board
     */
    Board getBoard();

    /**
     * this.animals getter
     *
     * @return this.animals
     */
    Animal[] getAnimals();

    /**
     * Start a given level
     *
     * @param level Level to start
     */
    void startLevel(int level);

    /**
     * Checks if level is over
     *
     * @return true if over, false if not
     */
    boolean levelIsOver();

    /**
     * Move an animal at a given position to a given direction.
     *
     * @param position  Position of the Animal
     * @param direction Direction to move the Animal
     */
    void move(Position position, Direction direction);
}
