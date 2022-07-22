import savekoalas.engine.CellType;
import savekoalas.engine.GameEngine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestPlayer {

    @Test
    void testGetPositionX() {
        GameEngine ge = new GameEngine(10);
        assertEquals(9, ge.player.getPositionX());
    }

    @Test
    void testGetPositionY() {
        GameEngine ge = new GameEngine(10);
        assertEquals(0, ge.player.getPositionY());
    }

    @Test
    void testSetPositionX() {
        GameEngine ge = new GameEngine(10);
        ge.board.setBoardCell(8, 0, CellType.BLANK);
        ge.player.setPositionX(8);
        assertEquals(8, ge.player.getPositionX());
    }

    @Test
    void testSetPositionY() {
        GameEngine ge = new GameEngine(10);
        ge.board.setBoardCell(9, 1, CellType.BLANK);
        ge.player.setPositionY(1);
        assertEquals(1, ge.player.getPositionY());
    }
}