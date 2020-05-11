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

package g54327.humbug.model.Animals.Type;

import g54327.humbug.model.Animals.Animal;
import g54327.humbug.model.Exceptions.NullSquareException;
import g54327.humbug.model.Exceptions.PositionOutOfBoundException;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;

public interface Terrestrial {
    default Position step(Position actualPosition, Direction direction, int nSquares, Board board, Animal[] animals) {
        if (actualPosition == null) {
            throw new IllegalArgumentException();
        }

        if (direction == null) {
            throw new IllegalArgumentException();
        }

        if (nSquares < 1) {
            throw new IllegalArgumentException();
        }

        Position nextPosition = null;

        for (var i = 0; i < nSquares; ++i) {
            if (board.getSquare(actualPosition).hasWall(direction)) {
                return actualPosition;
            }

            nextPosition = actualPosition.next(direction);
            try {
                board.getSquareType(nextPosition);
            } catch (NullSquareException | PositionOutOfBoundException e) {
                return null;
            }

            if (board.getSquare(nextPosition).hasWall(direction.opposite())) {
                return actualPosition;
            }

            for (Animal animal : animals) {
                if (!animal.isOnStar() && animal.getPositionOnBoard().equals(nextPosition)) {
                    return actualPosition;
                }
            }

            actualPosition = nextPosition;
        }

        return actualPosition;
    }
}
