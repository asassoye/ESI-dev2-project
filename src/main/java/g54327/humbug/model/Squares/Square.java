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

package g54327.humbug.model.Squares;

import g54327.humbug.model.Structures.Direction;

/**
 * Square on the board. A square has a type grass or star and it's all.
 * A square doesn't know where it is on the board.
 *
 * @author Andrew SASSOYE
 * @version 2.0.0
 * @since 0.1.0
 */
public class Square {
    private final SquareType type;

    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;

    public Square() {
        this(SquareType.GRASS);
    }

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     */
    public Square(SquareType type) {
        this(type, false, false, false, false);
    }

    /**
     * Constructor of Square on board.
     *
     * @param type      Square is grass or star
     * @param northWall add north wall
     * @param southWall add south wall
     * @param westWall  add west wall
     * @param eastWall  add east wall
     */
    public Square(SquareType type, boolean northWall, boolean southWall, boolean westWall, boolean eastWall) {
        this.type = type;
        this.northWall = northWall;
        this.southWall = southWall;
        this.westWall = westWall;
        this.eastWall = eastWall;
    }

    /**
     * Simple getter of type
     *
     * @return type of Square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Verify if wall is present in given Direction
     *
     * @param direction Direction of wall
     * @return true if wall is present, false if not
     * @since 2.0.0
     */
    public boolean hasWall(Direction direction) {
        switch (direction) {
            case NORTH:
                return this.hasNorthWall();
            case SOUTH:
                return this.hasSouthWall();
            case WEST:
                return this.hasWestWall();
            case EAST:
                return this.hasEastWall();
            default:
                return false;
        }
    }

    /**
     * this.northWall getter
     *
     * @return this.northWall
     * @since 2.0.0
     */
    public boolean hasNorthWall() {
        return northWall;
    }

    /**
     * this.nortWall setter
     *
     * @param northWall New northWall value
     * @since 2.0.0
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    /**
     * this.southWall getter
     *
     * @return this.southWall
     * @since 2.0.0
     */
    public boolean hasSouthWall() {
        return southWall;
    }

    /**
     * this.southWall setter
     *
     * @param southWall New southWall value
     * @since 2.0.0
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * this.westWall getter
     *
     * @return this.westWall
     * @since 2.0.0
     */
    public boolean hasWestWall() {
        return westWall;
    }

    /**
     * this.westWall setter
     *
     * @param westWall New westWall value
     * @since 2.0.0
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * this.eastWall getter
     *
     * @return this.eastWall
     * @since 2.0.0
     */
    public boolean hasEastWall() {
        return eastWall;
    }

    /**
     * this.eastWall setter
     *
     * @param eastWall New eastWall value
     * @since 2.0.0
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }
}
