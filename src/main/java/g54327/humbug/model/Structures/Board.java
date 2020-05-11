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

package g54327.humbug.model.Structures;

import g54327.humbug.model.Exceptions.NullPositionException;
import g54327.humbug.model.Exceptions.NullSquareException;
import g54327.humbug.model.Exceptions.PositionOutOfBoundException;
import g54327.humbug.model.Squares.Square;
import g54327.humbug.model.Squares.SquareType;

import static g54327.humbug.model.Squares.SquareType.GRASS;
import static g54327.humbug.model.Squares.SquareType.STAR;

/**
 * Board class
 *
 * @author Andrew SASSOYE
 * @version 1.1.1
 * @since 0.1.0
 */
public class Board {
    private final Square[][] squares;

    /**
     * Board constructor
     */
    public Board() {
        this(new Square[0][0]);
    }

    /**
     * Board constructor
     *
     * @param squares Squares in Board
     */
    public Board(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * Static method to get initial Board
     *
     * @return initial Board
     */
    static Board getInitialBoard() {
        Square[][] squares = {
                {new Square(GRASS), new Square(GRASS), null},
                {null, new Square(GRASS), new Square(GRASS)},
                {null, null, new Square(STAR)}
        };
        return new Board(squares);
    }

    /**
     * Verify if a Square is present at given position
     *
     * @param position Position to verify
     * @return true if present, false if not
     */
    public boolean isInside(Position position) {
        if (position == null) {
            throw new NullPositionException("Position is not defined");
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

        return this.squares[position.getRow()][position.getColumn()].getType() == GRASS
                || this.squares[position.getRow()][position.getColumn()].getType() == STAR;
    }

    /**
     * get a SquareType at a given position
     *
     * @param position Position to get SquareType
     * @return SquareType at position
     */
    public SquareType getSquareType(Position position) {
        return this.getSquare(position).getType();
    }

    /**
     * this.squares getter
     *
     * @return this.squares
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * get a Square at a given position
     *
     * @param position Position to get Square
     * @return Square at position
     * @since 1.1.0
     */
    public Square getSquare(Position position) {
        if (position == null) {
            throw new NullPositionException("Position is not defined");
        }

        if (
                position.getColumn() < 0
                        || position.getRow() < 0
                        || position.getRow() > this.squares.length - 1
                        || position.getColumn() > this.squares[position.getRow()].length - 1
        ) {
            throw new PositionOutOfBoundException("Position is out of bound");
        }

        if (this.squares[position.getRow()][position.getColumn()] == null) {
            throw new NullSquareException("No Square at asked position");
        }

        return this.squares[position.getRow()][position.getColumn()];
    }

    /**
     * Set the SquareType at a given position
     *
     * @param position   Position to modify SquareType
     * @param squareType new SquareType
     * @return Board
     */
    public Board setSquareType(Position position, SquareType squareType) {
        this.squares[position.getRow()][position.getColumn()] = new Square(squareType);

        return this;
    }

    /**
     * this.nbRow getter
     *
     * @return this.nbRow
     */
    public int getNbRow() {
        return squares.length;
    }

    /**
     * this.nbColumn getter
     *
     * @return this.nbColumn
     */
    public int getNbColumn() {
        return squares[0].length;
    }
}
