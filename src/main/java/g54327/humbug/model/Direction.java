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

/**
 * Direction enumeration. NORTH, SOUTH, EAST or WEST
 *
 * @author Andrew SASSOYE <andrew@sassoye.be>
 */
public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    private int deltaRow;

    private int deltaColumn;

    /**
     * Constructor
     *
     * @param deltaRow    delta of rows
     * @param deltaColumn delta of column
     */
    Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * DeltaRow getter
     *
     * @return this.deltaRow
     */
    public int getDeltaRow() {
        return this.deltaRow;
    }

    /**
     * DeltaColumn getter
     *
     * @return this.deltaColumn
     */
    public int getDeltaColumn() {
        return this.deltaColumn;
    }

    public static Direction valueOfByShortName(String s) {
        for (var direction : Direction.values()) {
            if (direction.name().charAt(0) == s.charAt(0)) {
                return direction;
            }
        }

        return null;
    }
}
