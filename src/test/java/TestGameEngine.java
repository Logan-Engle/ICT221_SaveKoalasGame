import savekoalas.engine.CellType;
import savekoalas.engine.GameEngine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestGameEngine {

    @Test
    void testGetSize() {
        GameEngine ge = new GameEngine(10);
        assertEquals(10, ge.getSize());
    }

    @Test
    void testMove() {
        GameEngine ge = new GameEngine(10);
        ge.move(8, 0);
        assertTrue(ge.player.getPositionX() == 8 && ge.player.getPositionY() == 0);
    }
    
    @Test
    void testMoveUp() {
        GameEngine ge = new GameEngine(10);
        ge.moveUp();
        assertTrue(ge.player.getPositionX() == 8 && ge.player.getPositionY() == 0);
    }

    @Test
    void testMoveDown() {
        GameEngine ge = new GameEngine(10);
        ge.board.setBoardCell(8, 0, CellType.BLANK);
        ge.moveUp();
        ge.moveDown();
        assertTrue(ge.player.getPositionX() == 9 && ge.player.getPositionY() == 0);
    }

    @Test
    void testMoveRight() {
        GameEngine ge = new GameEngine(10);
        ge.board.setBoardCell(9, 1, CellType.BLANK);
        ge.moveRight();
        assertTrue(ge.player.getPositionX() == 9 && ge.player.getPositionY() == 1);
    }

    @Test
    void testMoveLeft() {
        GameEngine ge = new GameEngine(10);
        ge.board.setBoardCell(9, 1, CellType.BLANK);
        ge.moveRight();
        ge.moveLeft();
        assertTrue(ge.player.getPositionX() == 9 && ge.player.getPositionY() == 0);
    }


}
