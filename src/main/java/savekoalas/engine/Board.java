package savekoalas.engine;

import java.io.Serializable;
import java.util.Random;

/**
* Game Board for the maze runner game
*/
public class Board implements Serializable{

    public Cell[][] board;
    private int size;
    Random rand = new Random();

    public Board(int size, int difficulty) {
        this.board = new Cell[size][size];
        this.size = size;
        // initilise all cells to blank cell type
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(CellType.BLANK);
            }
        }
        // set entrance and exit cells
        this.board[size - 1][0].setCellType(CellType.PLAYER); // player begins on the entrance cell
        this.board[0][size - 1].setCellType(CellType.EXIT); 
        // randomly set koalas, traps and fountains
        initialiseCells(CellType.KOALA, 5);
        initialiseCells(CellType.TRAP, difficulty);
        initialiseCells(CellType.FOUNTAIN, (10 - difficulty));
    }

    /**
    * Takes a celltype and the number of cells to set 
    * in and randomly sets totalCells amount of existing blank 
    * cells to be the specified celltype
    * @param cellType The celltype to set the blank cell to
    * @param totalCells The number of cells to update
    */
    public void initialiseCells(CellType cellType, int totalCells) {
        int counter = 0;
        while (counter < totalCells) {
            int randomX = rand.nextInt(size - 1);
            int randomY = rand.nextInt(size - 1);
            if (this.board[randomX][randomY].getCellType() == CellType.BLANK) {
                this.board[randomX][randomY].setCellType(cellType);
                counter++;
            }
        }
    }

    /**
    * Gets the size of the board
    * @return The size of the board
    */
    public int getSize() {
        return this.size;
    }

    /**
    * Gets the board
    * @return The the board
    */
    public Cell[][] getBoard() {
        return this.board;
    }

    /**
    * Takes an x and y position and a celltype
    * and then updates the cell at the x and y 
    * position passed to the specified celltype
    * @param x_coor The x position of the cell to update
    * @param y_coor The y position of the cell to update
    * @param type The type to set the cell to
    */
    public void setBoardCell(int x_coor, int y_coor, CellType type) {
        this.board[x_coor][y_coor].setCellType(type);
    }

    /**
    * Takes an x and y position and returns
    * the celltype of the cell at that position 
    * @param x_coor The x position of the cell to get
    * @param y_coor The y position of the cell to get
    * @return The celltype
    */
    public CellType getBoardCell(int x_coor, int y_coor) {
        return this.board[x_coor][y_coor].getCellType();
    }

    /**
    * Takes an x and y position and returns
    * the cell at that position 
    * @param x_coor The x position of the cell to get
    * @param y_coor The y position of the cell to get
    * @return The cell object at that location
    */
    public Cell getCell(int x_coor, int y_coor) {
        return this.board[x_coor][y_coor];
    }

    @Override
    public String toString() {
        String resultString = "";
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (col == 9) {
                    resultString += this.board[row][col].toString() + "\n";
                } else {
                    resultString += this.board[row][col].toString();
                }
            }   
        }
        return resultString;
    }
    
}
