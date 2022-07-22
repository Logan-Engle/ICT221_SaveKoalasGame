package savekoalas.engine;

import java.io.Serializable;

/**
* Cell class for the maze runner game
*/
public class Cell implements Serializable{

    private CellType cellType;

    public Cell(CellType cellType) {
        this.cellType = cellType;
    }

    /**
    * Gets the celltype of a cell
    * @return The celltype
    */
    public CellType getCellType() {
        return this.cellType;
    }

    /**
    * Sets the celltype of a cell
    * @param cellType The celltype
    */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public String toString() {
        switch (getCellType()) {
            case BLANK:
                return " _ ";
            case KOALA:
                return " K ";
            case FOUNTAIN:
                return " M ";
            case TRAP:
                return " T ";
            case ENTRANCE:
                return " S ";
            case EXIT:
                return " E ";
            case PLAYER:
                return " P ";
        }
        return " _ ";
    }

}
