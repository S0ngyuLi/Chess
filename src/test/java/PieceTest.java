import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/17/17.
 */
public class PieceTest {
    Player playerA;
    Player playerB;
    Chessboard board;
    Piece queen;
    Piece pawn;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new Chessboard(playerA, playerB, null);
        board.initializeBoard();
        queen = new Queen(this.playerA, board.getCell(4,4));
        pawn = new Pawn(this.playerB, board.getCell(3,3));
    }
    @Test
    public void testPieceOwner(){
        Assert.assertEquals(playerA, queen.getOwner());
    }

    @Test
    public void testPieceMovementChangeCell(){
        queen.willMove(board.getCell(5,5), false);
        Assert.assertEquals(5, queen.getChessboardCell().getX());
        Assert.assertEquals(5, queen.getChessboardCell().getY());
    }

    @Test
    public void testPieceCanCapture(){
        queen.willMove(board.getCell(3,3), false);
        Assert.assertEquals(board.getCell(3,3), queen.getChessboardCell());
    }

    @Test
    public void testPieceCanBeCaptured() {
        queen.willMove(board.getCell(3,3), false);
        Assert.assertTrue(!pawn.isOnBoard());
    }

}
