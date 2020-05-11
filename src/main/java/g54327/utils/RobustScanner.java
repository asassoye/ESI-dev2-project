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

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * RobustScanner
 *
 * @author Andrew SASSOYE
 * @version 1.0.1
 * @since 0.2.0
 */
public final class RobustScanner {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Ask int (robust)
     *
     * @param preMessage    Message to display before asking
     * @param errorMessage  Message to display when error
     * @param preConsumer   Lambda to display preMessage and stuff
     * @param errorConsumer Lambda to display error and stuff
     *
     * @return asked int
     */
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

    /**
     * Ask askString (robust) with regex
     *
     * @param preMessage    Message to display before asking
     * @param errorMessage  Message to display when error
     * @param regex         Regular expression
     * @param preConsumer   Lambda to display preMessage and stuff
     * @param errorConsumer Lambda to display error and stuff
     * @return asked String
     */
    public static String askString(String preMessage, String errorMessage, String regex, Consumer<String> preConsumer, Consumer<String> errorConsumer) {
        preConsumer.accept(preMessage);
        System.out.print("\t");
        String word;
        Pattern pattern = Pattern.compile(regex);
        do {
            while (!scanner.hasNext()) {
                scanner.nextLine();
                errorConsumer.accept(errorMessage);
                System.out.print("\t");
            }
            word = scanner.next();
            if (!pattern.matcher(word).matches()) {
                errorConsumer.accept(errorMessage);
                word = null;
            }
        } while (word == null);

        return word;
    }
}
