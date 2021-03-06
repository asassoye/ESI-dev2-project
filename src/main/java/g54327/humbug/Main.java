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

package g54327.humbug;

import g54327.humbug.controller.Controller;
import g54327.humbug.model.Game;
import g54327.humbug.model.Model;
import g54327.humbug.view.text.InterfaceView;
import g54327.humbug.view.text.View;

/**
 * Main
 *
 * @author Andrew SASSOYE
 * @version 1.1.0
 */
public class Main {
    /**
     * Main method
     *
     * @param args Execution arguments
     */
    public static void main(String[] args) {
        Model model = new Game();
        InterfaceView view = new View();
        Controller controller = new Controller(model, view);

        if (args.length == 1) {
            int level = Integer.parseInt(args[0]);
            controller.startGame(level);
        } else {
            controller.startGame();
        }
    }
}
