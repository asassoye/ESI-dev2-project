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

package g54327.humbug.view.text;

import g54327.humbug.model.Animals.Animal;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;

/**
 * @author Andrew SASSOYE
 * @version 1.0.1
 * @since 0.1.0
 */
public interface InterfaceView {
    /**
     * Clean console screen
     */
    void clearScreen();

    /**
     * Display board in console
     *
     * @param board Board to display
     * @deprecated
     */
    void displayBoard(Board board);

    /**
     * Display board in console
     *
     * @param board   Board to display
     * @param animals Animals to display
     * @since 1.0.1
     */
    void displayBoard(Board board, Animal... animals);

    /**
     * Ask position in console
     *
     * @return Position
     */
    Position askPosition();

    /**
     * Ask direction in console
     *
     * @return Direction
     */
    Direction askDirection();

    /**
     * Display an error in console
     *
     * @param message message to display
     */
    void displayError(String message);

    /**
     * Display a message in console
     *
     * @param message message to display
     */
    void displayMessage(String message);

}
