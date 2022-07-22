package savekoalas.engine;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Game Engine for the Maze Runner Game.
 *
 * NOTE: Running this class directly will play the game in terminal
 */
public class GameEngine implements Serializable{

    public Board board;
    public int size = 10;
    public int difficulty;
    public int stepCounter;
    public int stepLimit;
    public int koalasSaved;
    public boolean isTrap;
    public Player player;
    public String result;
    
    public GameEngine(int difficulty) {
        this.difficulty = difficulty;
        this.board = new Board(size, difficulty);
        this.player = new Player();
        this.stepCounter = 0;
        this.stepLimit = 200;
        this.koalasSaved = 0;
    }

    /**
    * Takes an x and y coordinate value and moves the player to this location
    * @param newPlayerPositionX The players new x position
    * @param newPlayerPositionY The players new y position
    */
    public void move(int newPlayerPositionX, int newPlayerPositionY) {
        int PlayerPositionX = player.getPositionX();
        int PlayerPositionY = player.getPositionY();
        if (board.getBoardCell(newPlayerPositionX, newPlayerPositionY) == CellType.FOUNTAIN) {
            stepLimit += 6;
        } else if (board.getBoardCell(newPlayerPositionX, newPlayerPositionY) == CellType.KOALA) {
            koalasSaved += 1;
        }
        board.setBoardCell(newPlayerPositionX, newPlayerPositionY, CellType.PLAYER);
        if (isTrap) {
            board.setBoardCell(PlayerPositionX, PlayerPositionY, CellType.TRAP);
            stepCounter += 10;
            isTrap = false;
        } else {
            board.setBoardCell(PlayerPositionX, PlayerPositionY, CellType.BLANK);
            stepCounter += 1;
        }
        player.setPositionX(newPlayerPositionX);
        player.setPositionY(newPlayerPositionY);
    }

    /**
    * Moves the player up one cell
    * @return The result calling move up
    */
    public String moveUp() {
        int PlayerPositionX = player.getPositionX();
        int newPlayerPositionX = player.getPositionX() - 1;
        int newPlayerPositionY = player.getPositionY();
        if (PlayerPositionX == 0) {
            result = "You cannot move up";
        } else {
            move(newPlayerPositionX, newPlayerPositionY);
            result = "You move up successfully";
        }
        return result;
    }
    
    /**
    * Moves the player down one cell
    * @return The result calling move down
    */
    public String moveDown() {
        int PlayerPositionX = player.getPositionX();
        int newPlayerPositionX = player.getPositionX() + 1;
        int newPlayerPositionY = player.getPositionY();
        if (PlayerPositionX == 9) {
            result = "You cannot move down";
        } else {
            move(newPlayerPositionX, newPlayerPositionY);
            result = "You move down successfully";
        }
        return result;
    }

    /**
    * Moves the player left one cell
    * @return The result calling move left
    */
    public String moveLeft() {
        int PlayerPositionY = player.getPositionY();
        int newPlayerPositionX = player.getPositionX();
        int newPlayerPositionY = player.getPositionY() - 1;
        if (PlayerPositionY == 0) {
            result = "You cannot move left";
        } else {
            move(newPlayerPositionX, newPlayerPositionY);
            result = "You move left successfully";
        }
        return result;
    }

    /**
    * Moves the player right one cell
    * @return The result calling move right
    */
    public String moveRight() {
        int PlayerPositionY = player.getPositionY();
        int newPlayerPositionX = player.getPositionX();
        int newPlayerPositionY = player.getPositionY() + 1;
        if (PlayerPositionY == 9) {
            result = "You cannot move right";
        } else {
            move(newPlayerPositionX, newPlayerPositionY);
            result = "You move right successfully";
        }
        return result;
    }

    /**
    * Gets the number of traps on the board
    * @return The number of traps on the board
    */
    public int numberOfTraps() {
        int numTraps = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.board[row][col].getCellType() == CellType.TRAP) {
                    numTraps += 1;
                }
            }
        }
        return numTraps;
    }

    /**
     * Gets the board size
     * @return The size of the board
     */
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        int diff;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                try {
                    System.out.println("Please input a difficulty level from 1-10");
                    diff = scanner.nextInt();
                }
                catch(InputMismatchException e) {
                    System.out.println("The value you have entered is invalid, difficulty defaulted to 5");
                    diff = 5;
                }
            } while (diff < 1 || diff > 10);
            GameEngine engine = new GameEngine(diff);
            System.out.println(engine.board.toString());
            int moveCommand; // 1 - up; 2 - down; 3 - left; 4 - right;
            while (engine.stepCounter < engine.stepLimit) {
                System.out.println("Command (1:up 2:down 3:left 4:right): ");
                moveCommand = scanner.nextInt();
                if (moveCommand == 1) {
                    engine.result = engine.moveUp();
                } else if (moveCommand == 2) {
                    engine.result = engine.moveDown();
                } else if (moveCommand == 3) {
                    engine.result = engine.moveLeft();
                } else if (moveCommand == 4) {
                    engine.result = engine.moveRight();
                }
                // track if the cell the player has just moved to is a trap
                if (engine.numberOfTraps() < engine.difficulty) {
                    engine.isTrap = true;
                } else if (engine.numberOfTraps() == engine.difficulty) {
                    engine.isTrap = false;
                }   
                // print new board state and results of move
                System.out.println("Action result: " + engine.result);            
                System.out.println("Steps remaining: " + Integer.toString(engine.stepLimit - engine.stepCounter));
                System.out.println("Koalas saved: " + Integer.toString(engine.koalasSaved));
                System.out.println(engine.board.toString());
                // check for player win or lose
                if (engine.koalasSaved == 5 && engine.player.getPositionX() == 0 && engine.player.getPositionY() == 9) {
                    System.out.println("Congratulation, you've won! Your score is " + (engine.stepLimit - engine.stepCounter));
                    System.exit(0);
                } else if (engine.stepCounter >= engine.stepLimit) {
                    System.out.println("Badluck, you've lost! Your score is " + (-1));
                    System.exit(0);
                }
            }
        }
    }
}