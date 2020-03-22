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

package g54327.utils;

import g54327.humbug.model.Direction;

import java.util.Scanner;
import java.util.function.Consumer;

public final class RobustScanner {
    private static Scanner scanner = new Scanner(System.in);

    public static int askInt(String preMessage, String errorMessage, Consumer<String> preConsumer, Consumer<String> errorConsumer) {
        preConsumer.accept(preMessage);
        System.out.print("\t");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            errorConsumer.accept(errorMessage);
            System.out.print("\t");
        }

        return scanner.nextInt();
    }

    public static Direction askDirection(String preMessage, String errorMessage, Consumer<String> preConsumer, Consumer<String> errorConsumer) {
        preConsumer.accept(preMessage);
        System.out.print("\t");
        Direction direction;
        do {
            while (!scanner.hasNext()) {
                scanner.nextLine();
                errorConsumer.accept(errorMessage);
                System.out.print("\t");
            }
            direction = Direction.valueOfByShortName(scanner.next());
            if (direction == null) {
                errorConsumer.accept(errorMessage);
            }
        } while (direction == null);

        return direction;
    }
}
