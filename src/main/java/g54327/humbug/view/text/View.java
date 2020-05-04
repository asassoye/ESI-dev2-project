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
import g54327.humbug.model.Animals.Animal;
import g54327.humbug.model.Squares.Square;
import g54327.humbug.model.Squares.SquareType;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;
import g54327.utils.RobustScanner;

/**
 * View class
 *
 * @author Andrew SASSOYE
 * @version 1.1.0
 * @since 0.1.0
 */
public class View implements InterfaceView {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayBoard(Board board) {
        this.displayBoard(board, (Animal[]) null);
    }

    private static void printBoard(Board board, Animal... animals) {
        Grid grid = new Grid(board.getNbColumn(), board.getNbRow(), 3, 1);
        System.out.println();

        for (var i = 0; i < board.getNbRow(); ++i) {
            for (var j = 0; j < board.getNbColumn(); ++j) {
                Position position = new Position(i, j);
                if (board.isInside(position)) {
                    Square square = board.getSquare(position);
                    Animal animal = Animal.getAnimal(animals, position);

                    if (animal != null && !animal.isOnStar()) {
                        grid.setElement(i, j, animal.toString(),
                                square.hasNorthWall(),
                                square.hasSouthWall(),
                                square.hasWestWall(),
                                square.hasEastWall()
                        );
                    } else {
                        switch (board.getSquareType(position)) {
                            case GRASS:
                                grid.setElement(i, j, String.valueOf(Grid.GRASS),
                                        square.hasNorthWall(),
                                        square.hasSouthWall(),
                                        square.hasWestWall(),
                                        square.hasEastWall()
                                );
                                break;
                            case STAR:
                                grid.setElement(i, j, String.valueOf(Grid.STAR),
                                        square.hasNorthWall(),
                                        square.hasSouthWall(),
                                        square.hasWestWall(),
                                        square.hasEastWall()
                                );
                                break;
                            default:
                                grid.setElement(i, j, "?",
                                        square.hasNorthWall(),
                                        square.hasSouthWall(),
                                        square.hasWestWall(),
                                        square.hasEastWall()
                                );
                        }
                    }
                }
            }
        }

        for (char[] row : grid.getGrid()) {
            System.out.print("\t");
            for (char element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {
        InterfaceView.clearScreen();
        System.out.println();
        System.out.print("\t");
        System.out.print("   ");

        printBoard(board, animals);
    }

    /**
     * {@inheritDoc}
     */
    public void displayError(String message) {
        errorPrinter.println(message);
    }

    /**
     * {@inheritDoc}
     */
    public void displayMessage(String message) {
        grassPrinter.println(message);
    }

    /**
     * {@inheritDoc}
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
        return new Position(row - 1, column - 1);
    }

    /**
     * {@inheritDoc}
     */
    public Direction askDirection() {
        System.out.println();
        starPrinter.println("\tChoose the direction [NORTH, EAST, SOUTH, WEST]\t");
        String directionString = RobustScanner
                .askString(
                        "Direction:",
                        "Error: Must be in [NORTH, EAST, SOUTH, WEST]. Retry:",
                        "^(NORTH|EAST|SOUTH|WEST)$",
                        grassPrinter::print,
                        errorPrinter::print
                );

        return Direction.valueOf(directionString);
    }
}
