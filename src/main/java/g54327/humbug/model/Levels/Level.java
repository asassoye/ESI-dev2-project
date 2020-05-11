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

package g54327.humbug.model.Levels;

import com.fasterxml.jackson.databind.ObjectMapper;
import g54327.humbug.model.Animals.Animal;
import g54327.humbug.model.Structures.Board;

/**
 * Level class
 *
 * @author Andrew SASSOYE
 * @version 1.0.0
 * @since 2.0.0
 */
public class Level {
    private final Board board;

    private final Animal[] animals;

    private final int nMoves;

    /**
     * Level constructor
     */
    public Level() {
        this(null, null, 0);
    }

    /**
     * Level constructor
     *
     * @param board Board
     * @param animals List of Animals
     * @param nMoves number of permitted moves
     */
    public Level(Board board, Animal[] animals, int nMoves) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Get specific level
     *
     * @param level level number
     *
     * @return asked Level
     */
    public static Level getLevel(int level) {
        return readLevel(level);
    }

    private static Level readLevel(int level) {
        var objectMapper = new ObjectMapper();
        var inputStream = Level.class.getResourceAsStream(String.format("/data/level-%d.json", level));

        try {
            return objectMapper.readValue(inputStream, Level.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * this.board getter
     *
     * @return this.board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * this.animals getter
     *
     * @return this.animals
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * this.nMoves getter
     *
     * @return this.nMoves
     */
    public int getnMoves() {
        return nMoves;
    }
}
