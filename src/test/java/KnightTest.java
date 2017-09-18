import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class KnightTest {
    Player playerA;
    Player playerB;
    Chessboard board;
    Piece knight;
    Piece rook;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new Chessboard(playerA, playerB, null);
        board.initializeBoard();
        knight = new Knight(this.playerA, board.getCell(2,2));
        rook = new Rook(this.playerB, board.getCell(3,3));
    }

    @Test
    public void testKnightCanJump(){
        knight.willMove(board.getCell(3,4), false);
        Assert.assertEquals(board.getCell(3,4), knight.getChessboardCell());
    }

    @Test
    public void testKnightCanBeBlocked() {
        rook.willMove(board.getCell(2, 3), false);
        knight.willMove(board.getCell(3, 4), false);
        Assert.assertEquals(board.getCell(2, 2), knight.getChessboardCell());
    }

}
