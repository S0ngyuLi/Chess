import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class BishopTest {
    Player playerA;
    Player playerB;
    ChessboardModel board;
    Piece bishop;
    Piece pawn;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new ChessboardModel(playerA, playerB, null);
        board.initializeBoard();
        bishop = new Bishop(this.playerA, board.getCell(4,4));
        pawn = new Pawn(this.playerB, board.getCell(3,3));
    }

    @Test
    public void testBishopCanMoveDiagonally() {
        pawn.willMove(board.getCell(3, 4), false);
        bishop.willMove(board.getCell(3, 3), false);
        Assert.assertEquals(board.getCell(3, 3), bishop.getChessboardCell());
    }

    @Test
    public void testBishopInvalidMove() {
        bishop.willMove(board.getCell(3,1), false);
        Assert.assertEquals(board.getCell(4,4), bishop.getChessboardCell());
        bishop.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), bishop.getChessboardCell());
        bishop.willMove(board.getCell(2,2), false);
        Assert.assertEquals(board.getCell(4,4), bishop.getChessboardCell());
    }

}
