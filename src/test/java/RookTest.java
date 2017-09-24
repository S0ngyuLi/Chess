import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class RookTest {
    Player playerA;
    Player playerB;
    ChessboardModel board;
    Piece rook;
    Piece pawn;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new ChessboardModel(playerA, playerB, null);
        board.initializeBoard();
        rook = new Rook(this.playerA, board.getCell(4,4));
        pawn = new Pawn(this.playerB, board.getCell(4,2));
    }

    @Test
    public void testRookCanCapture(){
        rook.willMove(board.getCell(4,2), false);
        Assert.assertEquals(board.getCell(4,2), rook.getChessboardCell());
    }

    @Test
    public void testRookCanBeCaptured() {
        rook.willMove(board.getCell(4,2), false);
        Assert.assertTrue(!pawn.isOnBoard());
    }

}
