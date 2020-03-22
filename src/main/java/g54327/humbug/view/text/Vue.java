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

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import g54327.humbug.model.Board;
import g54327.humbug.model.Direction;
import g54327.humbug.model.Position;
import g54327.humbug.model.SquareType;
import g54327.humbug.model.animals.Animal;
import g54327.utils.RobustScanner;

/**
 * Vue class
 *
 * @author Andrew SASSOYE <andrew@sassoye.be>
 */
public class Vue implements InterfaceView {

    private final static ColoredPrinter grassPrinter = new ColoredPrinter.Builder(0, false)
            .foreground(Ansi.FColor.WHITE).background(Ansi.BColor.GREEN)   //setting format
            .build();

    private final static ColoredPrinter starPrinter = new ColoredPrinter.Builder(0, false)
            .foreground(Ansi.FColor.WHITE).background(Ansi.BColor.YELLOW)   //setting format
            .build();

    private final static ColoredPrinter voidPrinter = new ColoredPrinter.Builder(0, false)
            .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.BLACK).attribute(Ansi.Attribute.HIDDEN)  //setting format
            .build();

    private final static ColoredPrinter errorPrinter = new ColoredPrinter.Builder(0, false)
            .foreground(Ansi.FColor.WHITE).background(Ansi.BColor.RED)
            .build();

    private static ColoredPrinter getColoredPrinter(SquareType squareType) {
        switch (squareType) {
            case GRASS:
                return grassPrinter;
            case STAR:
                return starPrinter;
            default:
                return voidPrinter;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Display board in console
     *
     * @param board   Board to display
     * @param animals Animals on the board
     */
    public void displayBoard(Board board, Animal[] animals) {
        clearScreen();
        System.out.println();
        System.out.printf("\t");
        System.out.printf("   ");

        for (var i = 0; i < board.getNbRow(); ++i) {
            System.out.printf("%d  ", i);
        }
        System.out.println();
        for (var i = 0; i < board.getNbRow(); ++i) {
            System.out.printf("\t");
            System.out.print(i + " ");
            for (var j = 0; j < board.getNbColumn(); ++j) {
                Position position = new Position(i, j);
                if (board.isInside(position)) {
                    Animal animal = Animal.getAnimal(animals, position);
                    ColoredPrinter printer = getColoredPrinter(board.getSquareType(position));

                    if (animal != null) {
                        printer.print(animal);
                    } else {
                        printer.setAttribute(Ansi.Attribute.HIDDEN);
                        printer.print(" \u2205 ");
                        printer.setAttribute(Ansi.Attribute.CLEAR);
                    }

                } else {
                    ColoredPrinter printer = voidPrinter;

                    printer.print(" \u2205 ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Display an error in console
     *
     * @param message message to display
     */
    public void displayError(String message) {
        errorPrinter.println(message);
    }

    public void displayMessage(String message) {
        grassPrinter.println(message);
    }

    /**
     * Ask position in console
     *
     * @return Position
     */
    public Position askPosition() {
        System.out.println();
        starPrinter.println("\tChoose the position\t");
        int row = RobustScanner
                .askInt(
                        "ROW:",
                        "Error: Must be an int. Retry:",
                        grassPrinter::print,
                        errorPrinter::print
                );

        int column = RobustScanner
                .askInt(
                        "COLUMN:",
                        "Error: Must be an int. Retry:",
                        grassPrinter::print,
                        errorPrinter::print
                );

        System.out.println();
        return new Position(row, column);
    }

    /**
     * Ask direction in console
     *
     * @return Direction
     */
    public Direction askDirection() {
        System.out.println();
        starPrinter.println("\tChoose the direction [N, E, S, W]\t");
        Direction direction = RobustScanner
                .askDirection(
                        "Direction:",
                        "Error: Must be in [N, E, S, W]. Retry:",
                        grassPrinter::print,
                        errorPrinter::print
                );

        return direction;
    }
}
