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

import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Position;

import java.util.Scanner;

/**
 * Vue class
 *
 * @author Andrew SASSOYE <andrew@sassoye.be>
 */
public class Vue {
    /**
     * Display board in console
     *
     * @param board Board to display
     */
    public static void displayBoard(Board board) {
        String[][] stringArray = new String[board.getNbRow()][board.getNbColumn()];

        for (int i = 0; i < board.getNbRow(); ++i) {
            for (int j = 0; j < board.getNbColumn(); ++j) {
                Position position = new Position(i, j);

                if (board.isInside(position)) {
                    stringArray[i][j] = board.getSquareType(position).toString();
                }

            }
        }

        for (String[] line : stringArray) {
            for (String word : line) {
                System.out.printf("%s ", word);
            }
            System.out.print("\n");
        }
    }

    /**
     * Display an error in console
     *
     * @param message message to display
     */
    public void displayError(String message) {
        System.out.printf("[ERROR] %s\n", message);
    }

    /**
     * Ask position in console
     *
     * @return Position
     */
    public Position askPosition() {
        Scanner scanner = new Scanner(System.in);

        // TODO: Demander de maniere robuste
        return new Position(scanner.nextInt(), scanner.nextInt());
    }

    /**
     * Ask direction in console
     *
     * @return Direction
     */
    public Direction askDirection() {
        Scanner scanner = new Scanner(System.in);

        //TODO: robuste

        switch (scanner.next()) {
            case "NORTH":
                return Direction.NORTH;
            case "SOUTH":
                return Direction.SOUTH;
            case "EAST":
                return Direction.EAST;
            case "WEST":
                return Direction.WEST;
            default:
                return null;
        }
    }
}
