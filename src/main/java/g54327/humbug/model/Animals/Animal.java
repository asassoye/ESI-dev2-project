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

package g54327.humbug.model.Animals;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import g54327.humbug.model.Structures.Board;
import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;

/**
 * Animal abstract Class
 *
 * @author Andrew SASSOYE
 * @version 1.0.0
 * @since 0.2.0
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Snail.class),
        @JsonSubTypes.Type(value = Spider.class),
        @JsonSubTypes.Type(value = Grasshopper.class),
        @JsonSubTypes.Type(value = Bumblebee.class),
        @JsonSubTypes.Type(value = Ladybird.class),
        @JsonSubTypes.Type(value = Butterfly.class),
})
public abstract class Animal {

    /**
     * Actual position on Board
     */
    protected Position positionOnBoard;

    /**
     * Is on star (complete)
     */
    protected boolean onStar;

    public Animal() {
        this(new Position());
    }

    /**
     * Animal constructor
     *
     * @param positionOnBoard Position of Animal on the Board
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Static method to find animal in array on position
     *
     * @param animals  Animal array
     * @param position position to find Animal in
     * @return Animal if the animal is found, null if no animal found
     */
    public static Animal getAnimal(Animal[] animals, Position position) {
        for (var animal : animals) {
            if (animal.positionOnBoard.equals(position)) {
                return animal;
            }
        }

        return null;
    }

    /**
     * Move animal in given direction
     *
     * @param board     Board
     * @param direction Direction of movement
     * @param animals   Animal array
     * @return Position of moved animal, null if fallen
     */
    public abstract Position move(Board board, Direction direction, Animal[] animals);

    /**
     * this.positionOnBoard getter
     *
     * @return this.positionOnBoard
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * this.positionOnBoard setter
     *
     * @param positionOnBoard new Position to set
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * this.onStar getter
     *
     * @return true if on star, false if not
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * this.onStar setter
     *
     * @param onStar new boolean value of this.onStar
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }
}
