import savekoalas.engine.CellType;
import savekoalas.engine.GameEngine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestBoard {

    @Test
    void testGetSize() {
        GameEngine ge = new GameEngine(10);
        assertEquals(10, ge.board.getSize());
    }

    @Test
    void testInitialiseCells() {
        GameEngine ge = new GameEngine(10);
        ge.board.initialiseCells(CellType.KOALA, 5);
        int counter = 0;
        for (int row = 0; row < ge.size; row++) {
            for (int col = 0; col < ge.size; col++) {
                if (ge.board.board[row][col].getCellType() == CellType.KOALA) {
                    counter += 1;
                }
            }
        }
        assertEquals(10, counter);
    }
    
    @Test
    void testGetBoardCell() {
        GameEngine ge = new GameEngine(10);
        ge.board.getBoardCell(0, 9);
        assertEquals(CellType.EXIT, ge.board.getBoardCell(0, 9));
    }

    @Test
    void testSetBoardCell() {
        GameEngine ge = new GameEngine(10);
        ge.board.setBoardCell(0, 9, CellType.KOALA);
        assertEquals(CellType.KOALA, ge.board.getBoardCell(0, 9));
    }

    @Test
    void testNumberOfTraps() {
        GameEngine ge = new GameEngine(10);
        assertEquals(10, ge.numberOfTraps());
    }
}