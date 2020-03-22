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

package g54327.humbug.model.animals;

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Exceptions.NullSquareException;
import g54327.humbug.model.Exceptions.PositionOutOfBoundException;
import g54327.humbug.model.Position;
import g54327.humbug.model.SquareType;

public class Spider extends Animal {
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    @Override
    public String toString() {
        return " \uD83D\uDD77 ";
    }

    @Override
    public Position move(Board board, Direction direction, Animal[] animals) {
        Position nextPosition = positionOnBoard.next(direction);

        try {
            board.getSquareType(nextPosition);
        } catch (PositionOutOfBoundException | NullSquareException e) {
            this.positionOnBoard = null;
            return null;
        }

        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().equals(nextPosition)) {
                if (board.getSquareType(positionOnBoard) == SquareType.STAR) {
                    this.onStar = true;
                    board.setSquareType(positionOnBoard, SquareType.GRASS);
                }
                return this.positionOnBoard;
            }
        }

        this.positionOnBoard = nextPosition;
        return this.move(board, direction, animals);
    }
}
