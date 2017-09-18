import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class KingTest {
    Player playerA;
    Player playerB;
    Chessboard board;
    Piece king;
    Piece pawn;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new Chessboard(playerA, playerB, null);
        board.initializeBoard();
        king = new King(this.playerA, board.getCell(4,4));
        pawn = new Pawn(this.playerB, board.getCell(3,3));
    }

    @Test
    public void testKingCanMoveDiagonally(){
        pawn.willMove(board.getCell(3,4), false);
        king.willMove(board.getCell(3,3), false);
        Assert.assertEquals(board.getCell(3,3), king.getChessboardCell());
    }

    @Test
    public void testKingCanMoveStraight(){
        king.willMove(board.getCell(3,4), false);
        Assert.assertEquals(board.getCell(3,4), king.getChessboardCell());
        king.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), king.getChessboardCell());
    }

    @Test
    public void testKingCanCapture(){
        king.willMove(board.getCell(3,3), false);
        Assert.assertEquals(board.getCell(3,3), king.getChessboardCell());
    }

    @Test
    public void testKingPossibleRoutes(){
        Assert.assertEquals(8, king.getAllPossibleRoutes().size());
    }
}
