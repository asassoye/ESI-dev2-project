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
 * Snail Tests
 *
 * @author Andrew SASSOYE
 * @version 1.0.0
 * @since 2.0.0
 */
public class SnailTest {
    @Nested
    class Constructor {
        @Test
        public void ok() {
            Position position = new Position(0, 0);
            Snail snail = new Snail(position);

            assertEquals(snail.getPositionOnBoard(), position);
        }
    }

    @Nested
    class Move {
        @ParameterizedTest
        @EnumSource(Direction.class)
        public void free(Direction direction) {
            Board board = new Board(new Square[][]{
                    {null, new Square(SquareType.GRASS), null},
                    {new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS)},
                    {null, new Square(SquareType.GRASS), null}
            });
            Position position = new Position(1, 1);
            Snail snail = new Snail(position);
            Animal[] animals = {
                    snail
            };

            snail.move(board, direction, animals);

            assertAll(
                    () -> assertEquals(position.getRow() + direction.getDeltaRow(), snail.positionOnBoard.getRow()),
                    () -> assertEquals(position.getColumn() + direction.getDeltaColumn(), snail.positionOnBoard.getColumn())
            );
        }

        @ParameterizedTest
        @EnumSource(Direction.class)
        public void notFree(Direction direction) {
            Board board = new Board(new Square[][]{
                    {null, new Square(SquareType.GRASS), null},
                    {new Square(SquareType.GRASS), new Square(SquareType.GRASS), new Square(SquareType.GRASS)},
                    {null, new Square(SquareType.GRASS), null}
            });
            Position position = new Position(1, 1);
            Snail snail = new Snail(position);
            Animal[] animals = {
                    new Snail(new Position(0, 1)),
                    new Snail(new Position(1, 0)),
                    snail,
                    new Snail(new Position(1, 2)),
                    new Snail(new Position(2, 1)),
            };

            snail.move(board, direction, animals);

            assertAll(
                    () -> assertEquals(position.getRow(), snail.positionOnBoard.getRow()),
                    () -> assertEquals(position.getColumn(), snail.positionOnBoard.getColumn())
            );
        }

        @ParameterizedTest
        @EnumSource(Direction.class)
        public void walls(Direction direction) {
            Board board = new Board(new Square[][]{
                    {null, new Square(SquareType.GRASS, false, true, false, false), null},
                    {new Square(SquareType.GRASS, false, false, false, true), new Square(SquareType.GRASS, true, true, true, true), new Square(SquareType.GRASS, false, false, true, false)},
                    {null, new Square(SquareType.GRASS, true, false, false, false), null}
            });
            Position position = new Position(1, 1);
            Snail snail = new Snail(position);
            Animal[] animals = {
                    snail
            };

            snail.move(board, direction, animals);

            assertAll(
                    () -> assertEquals(position.getRow(), snail.positionOnBoard.getRow()),
                    () -> assertEquals(position.getColumn(), snail.positionOnBoard.getColumn())
            );
        }
    }
}
