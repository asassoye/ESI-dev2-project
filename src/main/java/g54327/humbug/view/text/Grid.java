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

import java.util.Arrays;

public class Grid {
    public final static char SPACE = '\u0020';
    public final static char VERTICAL_BAR = '\u2502'; //│
    public final static char VERTICAL_DOUBLE_BAR = '\u2551'; //║
    public final static char HORIZONTAL_BAR = '\u2500'; //──
    public final static char HORIZONTAL_DOUBLE_BAR = '\u2550'; //══
    public final static char GRASS = '\u2591'; //░
    public final static char STAR = '*'; //*
    public final static char BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_RIGHT = '\u256D'; //╭
    public final static char BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT = '\u256E'; //╮
    public final static char BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT = '\u256F'; //╯
    public final static char BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT = '\u2570'; //╰
    public final static char BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL = '\u252C'; //┬
    public final static char BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL = '\u2534'; //┴
    public final static char BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT = '\u251C'; //├
    public final static char BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT = '\u2524'; //┤
    public final static char BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL = '\u253C'; //┼

    private final char[][] grid;

    private final int contentLength;

    private final int padding;

    private final int rows;

    private final int columns;

    /**
     * Grid constructor
     *
     * @param rows          number of rows
     * @param columns       number of columns
     * @param contentLength number of chars of elements
     * @param padding       padding inside element (left and right)
     */
    public Grid(int rows, int columns, int contentLength, int padding) {
        this.rows = rows;
        this.columns = columns;
        this.contentLength = contentLength;
        this.padding = padding;
        this.grid = initGrid();
    }

    private char[][] initGrid() {
        int totalWidth = 3 + this.columns * (this.contentLength + 2 * padding + 1);
        int totalHeight = 2 + this.rows * 2;

        char[][] grid = new char[totalHeight][totalWidth];
        for (char[] chars : grid) {
            Arrays.fill(chars, SPACE);
        }

        addColumnNumbers(grid);
        addRowNumbers(grid);

        return grid;
    }

    private void addColumnNumbers(char[][] grid) {
        int positionX, positionY;

        for (var i = 0; i < this.columns; ++i) {
            positionX = 3 + (padding * 2 + contentLength) / 2 + i * (this.contentLength + 2 * padding + 1);
            positionY = 0;
            grid[positionY][positionX] = String.valueOf(i + 1).charAt(0);
        }
    }

    private void addRowNumbers(char[][] grid) {
        for (var i = 0; i < this.rows; ++i) {
            grid[2 + (2 * i)][0] = String.valueOf(i + 1).charAt(0);
        }
    }

    /**
     * this.grid getter
     *
     * @return this.grid
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Set element at given position. The text will be cropped if it is longer then
     * contentLength or duplicated if too short.
     *
     * @param row    row of element
     * @param column column of element
     * @param text   content of element
     */
    public void setElement(int row, int column, String text) {
        setElement(row, column, text, false, false, false, false);
    }

    /**
     * Set element at given position. The text will be cropped if it is longer then
     * contentLength or duplicated if too short.
     *
     * @param row       row of element
     * @param column    column of element
     * @param text      content of element
     * @param northWall Add north wall
     * @param southWall Add south wall
     * @param westWall  Add west wall
     * @param eastWall  Add east wall
     */
    public void setElement(int row, int column, String text, boolean northWall, boolean southWall, boolean westWall, boolean eastWall) {
        int positionX = 3 + padding + column * (this.contentLength + 2 * padding + 1);
        int positionY = 2 + row * 2;

        char[] charArray = text.toCharArray();
        for (var i = 0; i < this.contentLength; ++i) {
            if (i < charArray.length) {
                this.grid[positionY][positionX + i] = charArray[i];
            } else {
                this.grid[positionY][positionX + i] = charArray[i % charArray.length];
            }
        }
        this.setBorders(row, column, northWall, southWall, westWall, eastWall);
        this.setCorners(row, column);
    }

    private void setBorders(int row, int column) {
        setBorders(row, column, false, false, false, false);
    }

    private void setBorders(int row, int column, boolean northWall, boolean southWall, boolean westWall, boolean eastWall) {
        this.setLeftBorder(row, column, westWall);
        this.setTopBorder(row, column, northWall);
        this.setRightBorder(row, column, eastWall);
        this.setBottomBorder(row, column, southWall);
    }

    private void setCorners(int row, int column) {
        this.setLeftTopBorder(row, column);
        this.setRightTopBorder(row, column);
        this.setRightBottomBorder(row, column);
        this.setLeftBottomBorder(row, column);
    }

    private void setLeftBorder(int row, int column) {
        setLeftBorder(row, column, false);
    }

    private void setLeftBorder(int row, int column, boolean wall) {
        int positionX = 2 + column * (this.contentLength + 2 * padding + 1);
        int positionY = 2 + row * 2;

        if (wall) {
            this.grid[positionY][positionX] = VERTICAL_DOUBLE_BAR;
        } else {
            this.grid[positionY][positionX] = VERTICAL_BAR;
        }
    }

    private void setTopBorder(int row, int column) {
        setTopBorder(row, column, false);
    }

    private void setTopBorder(int row, int column, boolean wall) {
        int positionX = 3 + column * (this.contentLength + 2 * padding + 1);
        int positionY = 1 + row * 2;
        char bar;

        if (wall) {
            bar = HORIZONTAL_DOUBLE_BAR;
        } else {
            bar = HORIZONTAL_BAR;
        }

        for (var i = 0; i < padding * 2 + contentLength; ++i) {
            this.grid[positionY][positionX + i] = bar;
        }
    }

    private void setRightBorder(int row, int column) {
        setRightBorder(row, column, false);
    }

    private void setRightBorder(int row, int column, boolean wall) {
        int positionX = 2 + (column + 1) * (this.contentLength + 2 * padding + 1);
        int positionY = 2 + row * 2;

        if (wall) {
            this.grid[positionY][positionX] = VERTICAL_DOUBLE_BAR;
        } else {
            this.grid[positionY][positionX] = VERTICAL_BAR;
        }
    }

    private void setBottomBorder(int row, int column) {
        setBottomBorder(row, column, false);
    }

    private void setBottomBorder(int row, int column, boolean wall) {
        int positionX = 3 + column * (this.contentLength + 2 * padding + 1);
        int positionY = 1 + (row + 1) * 2;
        char bar;

        if (wall) {
            bar = HORIZONTAL_DOUBLE_BAR;
        } else {
            bar = HORIZONTAL_BAR;
        }

        for (var i = 0; i < padding * 2 + contentLength; ++i) {
            this.grid[positionY][positionX + i] = bar;
        }
    }

    private void setLeftTopBorder(int row, int column) {
        int positionX = 2 + column * (this.contentLength + 2 * padding + 1);
        int positionY = 1 + row * 2;

        switch (this.grid[positionY][positionX]) {
            case BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT:
            case BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL;
                break;
            default:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_RIGHT;
        }
    }

    private void setRightTopBorder(int row, int column) {
        int positionX = 2 + (column + 1) * (this.contentLength + 2 * padding + 1);
        int positionY = 1 + row * 2;

        switch (this.grid[positionY][positionX]) {
            case BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_RIGHT:
            case BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL;
                break;
            default:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT;
        }
    }

    private void setLeftBottomBorder(int row, int column) {
        int positionX = 2 + column * (this.contentLength + 2 * padding + 1);
        int positionY = 1 + (row + 1) * 2;

        switch (this.grid[positionY][positionX]) {
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT:
            case BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL;
                break;
            default:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT;
        }
    }

    private void setRightBottomBorder(int row, int column) {
        int positionX = 2 + (column + 1) * (this.contentLength + 2 * padding + 1);
        int positionY = 1 + (row + 1) * 2;

        switch (this.grid[positionY][positionX]) {
            case BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT:
            case BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT;
                break;
            case BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_RIGHT:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL:
            case BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL;
                break;
            default:
                this.grid[positionY][positionX] = BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT;
        }
    }
}