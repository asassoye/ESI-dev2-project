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

import g54327.humbug.model.Squares.Square;
import g54327.humbug.model.Squares.SquareType;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Bumblebee Tests
 *
 * @author Andrew SASSOYE
 * @version 1.0.0
 * @since 2.0.0
 */
public class BumblebeeTest {
    @Nested
    class Constructor {
        @Test
        public void ok() {
            Position position = new Position(0, 0);
            Grasshopper grasshopper = new Grasshopper(position);

            assertEquals(grasshopper.getPositionOnBoard(), position);
        }
    }

    @Nested
    class Move {
        private final Board board = new Board(new Square[][]{
                {null, null, null, new Square(SquareType.GRASS), null, null, null},
                {null, null, null, new Square(SquareType.GRASS), null, null, null},
                {null, null, null, new Square(SquareType.GRASS), null, null, null},
                {new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS)},
                {null, null, null, new Square(SquareType.GRASS), null, null, null},
                {null, null, null, new Square(SquareType.GRASS), null, null, null},
                {null, null, null, new Square(SquareType.GRASS), null, null, null},
        });

        @ParameterizedTest
        @EnumSource(Direction.class)
        public void free(Direction direction) {
            Board board = this.board;
            Position position = new Position(3, 3);
            Bumblebee bumblebee = new Bumblebee(position);
            Animal[] animals = {
                    bumblebee,
            };

            bumblebee.move(board, direction, animals);

            assertAll(
                    () -> assertEquals(position.getRow() + direction.getDeltaRow() * 2, bumblebee.positionOnBoard.getRow()),
                    () -> assertEquals(position.getColumn() + direction.getDeltaColumn() * 2, bumblebee.positionOnBoard.getColumn())
            );
        }

        @ParameterizedTest
        @EnumSource(Direction.class)
        public void notFreeInter(Direction direction) {
            Board board = this.board;
            Position position = new Position(3, 3);
            Bumblebee bumblebee = new Bumblebee(position);
            Animal[] animals = {
                    new Snail(new Position(2, 3)),
                    new Snail(new Position(3, 2)),
                    bumblebee,
                    new Snail(new Position(3, 4)),
                    new Snail(new Position(4, 3)),
            };

            bumblebee.move(board, direction, animals);

            assertAll(
                    () -> assertEquals(position.getRow() + direction.getDeltaRow() * 2, bumblebee.positionOnBoard.getRow()),
                    () -> assertEquals(position.getColumn() + direction.getDeltaColumn() * 2, bumblebee.positionOnBoard.getColumn())
            );
        }

        @ParameterizedTest
        @EnumSource(Direction.class)
        public void notFreeFinal(Direction direction) {
            Board board = this.board;
            Position position = new Position(3, 3);
            Bumblebee bumblebee = new Bumblebee(position);
            Animal[] animals = {
                    new Snail(new Position(1, 3)),
                    new Snail(new Position(3, 1)),
                    bumblebee,
                    new Snail(new Position(3, 5)),
                    new Snail(new Position(5, 3)),
            };

            bumblebee.move(board, direction, animals);

            assertAll(
                    () -> assertEquals(position.getRow() + direction.getDeltaRow() * 3, bumblebee.positionOnBoard.getRow()),
                    () -> assertEquals(position.getColumn() + direction.getDeltaColumn() * 3, bumblebee.positionOnBoard.getColumn())
            );
        }
    }
}
