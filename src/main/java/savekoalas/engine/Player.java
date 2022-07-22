package savekoalas.engine;

import java.io.Serializable;

/**
 * Player for the Maze Runner Game.
 */
public class Player implements Serializable{

    private int positionX;
    private int positionY;

    public Player() {
        this.positionX = 9;
        this.positionY = 0;
    }

    /**
    * Gets the x position of the player
    * @return The x position of the player
    */
    public int getPositionX() {
        return this.positionX;
    }

    /**
    * Sets the x position of the player
    * @param positionX The new x position of the player
    */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
    * Gets the y position of the player
    * @return The y position of the player
    */
    public int getPositionY() {
        return this.positionY;
    }

    /**
    * Sets the y position of the player
    * @param positionY The new y position of the player
    */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "{" +
            " positionX='" + getPositionX() + "'" +
            ", positionY='" + getPositionY() + "'" +
            "}";
    }
}