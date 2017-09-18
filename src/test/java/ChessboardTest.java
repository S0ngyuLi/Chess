import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class ChessboardTest {
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
    public void testCellVacancy(){
        Assert.assertTrue(!board.getCell(3,3).isVacant());
        Assert.assertTrue(board.getCell(3,4).isVacant());
    }

    @Test
    public void testCellVacancyAfterMovement() {
        queen.willMove(board.getCell(3,4), false);
        Assert.assertTrue(board.getCell(4,4).isVacant());
    }

    @Test
    public void testRooksPosition(){
        Assert.assertTrue(board.getCell(0,0).getPiece() instanceof Rook);
        Assert.assertTrue(board.getCell(7,0).getPiece() instanceof Rook);
        Assert.assertTrue(board.getCell(0,7).getPiece() instanceof Rook);
        Assert.assertTrue(board.getCell(7,7).getPiece() instanceof Rook);
    }

    @Test
    public void testNightsPosition(){
        Assert.assertTrue(board.getCell(1,0).getPiece() instanceof Knight);
        Assert.assertTrue(board.getCell(6,0).getPiece() instanceof Knight);
        Assert.assertTrue(board.getCell(1,7).getPiece() instanceof Knight);
        Assert.assertTrue(board.getCell(6,7).getPiece() instanceof Knight);
    }

    @Test
    public void testBishopsPosition(){
        Assert.assertTrue(board.getCell(2,0).getPiece() instanceof Bishop);
        Assert.assertTrue(board.getCell(5,0).getPiece() instanceof Bishop);
        Assert.assertTrue(board.getCell(2,7).getPiece() instanceof Bishop);
        Assert.assertTrue(board.getCell(5,7).getPiece() instanceof Bishop);
    }

    @Test
    public void testKingAndQueenPosition(){
        Assert.assertTrue(board.getCell(3,0).getPiece() instanceof King);
        Assert.assertTrue(board.getCell(4,0).getPiece() instanceof Queen);
        Assert.assertTrue(board.getCell(3,7).getPiece() instanceof King);
        Assert.assertTrue(board.getCell(4,7).getPiece() instanceof Queen);
    }
}
