import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class QueenTest {
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
    public void testQueenCanMoveDiagonally(){
        pawn.willMove(board.getCell(3,4), false);
        queen.willMove(board.getCell(3,3), false);
        Assert.assertEquals(board.getCell(3,3), queen.getChessboardCell());
    }

    @Test
    public void testQueenCanMoveStraight(){
        queen.willMove(board.getCell(3,4), false);
        Assert.assertEquals(board.getCell(3,4), queen.getChessboardCell());
        queen.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), queen.getChessboardCell());
        queen.willMove(board.getCell(4,3), false);
        Assert.assertEquals(board.getCell(4,3), queen.getChessboardCell());
        queen.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), queen.getChessboardCell());
        queen.willMove(board.getCell(3,4), false);
        Assert.assertEquals(board.getCell(3,4), queen.getChessboardCell());
    }

    @Test
    public void testQueenCanCapture(){
        queen.willMove(board.getCell(3,3), false);
        Assert.assertEquals(board.getCell(3,3), queen.getChessboardCell());
        queen.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), queen.getChessboardCell());
        queen.willMove(board.getCell(3,5), false);
        Assert.assertEquals(board.getCell(3,5), queen.getChessboardCell());
    }

    @Test
    public void testQueenInvalidMove() {
        queen.willMove(board.getCell(3,1), false);
        Assert.assertEquals(board.getCell(4,4), queen.getChessboardCell());
        queen.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), queen.getChessboardCell());
        queen.willMove(board.getCell(2,2), false);
        Assert.assertEquals(board.getCell(4,4), queen.getChessboardCell());
    }

}
