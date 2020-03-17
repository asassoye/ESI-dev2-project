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

import static g54327.humbug.model.SquareType.GRASS;
import static g54327.humbug.model.SquareType.STAR;

/**
 * Board class
 *
 * @author Andrew SASSOYE <andrew@sassoye.be>
 */
public class Board {

    private Square[][] squares;

    public Board(Square[][] squares) {
        this.squares = squares;
    }

    public static Board getInitialBoard() {
        Square[][] squares = {
                {new Square(GRASS), new Square(GRASS), null},
                {null, new Square(GRASS), new Square(GRASS)},
                {null, null, new Square(STAR)}
        };
        Board board = new Board(squares);
        return board;
    }

    public boolean isInside(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position is not defined");
        }

        if (
                position.getColumn() < 0
                        || position.getRow() < 0
                        || position.getRow() > this.squares.length - 1
                        || position.getColumn() > this.squares[position.getRow()].length - 1
        ) {
            return false;
        }

        if (this.squares[position.getRow()][position.getColumn()] == null) {
            return false;
        }

        return this.squares[position.getRow()][position.getColumn()].getSquareType() == GRASS;
    }

    public SquareType getSquareType(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position is not defined");
        }

        if (
                position.getColumn() < 0
                        || position.getRow() < 0
                        || position.getRow() > this.squares.length - 1
                        || position.getColumn() > this.squares[position.getRow()].length - 1
        ) {
            throw new IllegalArgumentException("position is out of board");
        }

        if (this.squares[position.getRow()][position.getColumn()] == null) {
            throw new IllegalArgumentException("No Square at asked position");
        }

        return this.squares[position.getRow()][position.getColumn()].getSquareType();
    }

    public int getNbRow() {
        return squares.length;
    }

    public int getNbColumn() {
        return squares[0].length;
    }
}