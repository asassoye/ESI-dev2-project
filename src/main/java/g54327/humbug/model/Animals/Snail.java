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

package g54327.humbug.model.Animals;

import g54327.humbug.model.Animals.Type.Terrestrial;
import g54327.humbug.model.Squares.SquareType;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;

/**
 * Snail Class
 *
 * @author Andrew SASSOYE
 * @version 2.0.0
 * @since 0.2.0
 */
public class Snail extends Animal implements Terrestrial {
    /**
     * Snail constructor
     */
    public Snail() {
        super();
    }

    /**
     * Snail constructor
     *
     * @param positionOnBoard initial position on Board
     */
    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Snail toString for View
     *
     * @return Snail
     */
    @Override
    public String toString() {
        return "Snail";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position move(Board board, Direction direction, Animal[] animals) {
        this.positionOnBoard = step(this.positionOnBoard, direction, 1, board, animals);

        if (this.positionOnBoard != null && board.getSquareType(positionOnBoard) == SquareType.STAR) {
            this.onStar = true;
            board.setSquareType(positionOnBoard, SquareType.GRASS);
        }

        return this.positionOnBoard;
    }
}
