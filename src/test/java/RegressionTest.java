import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/21/17.
 */
public class RegressionTest {
    AppDelegate delegate;
    Player playerA;
    Player playerB;
    ChessboardModel board;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new ChessboardModel(playerA, playerB, null);
        delegate = new AppDelegate(playerA, playerB, board);
        board.appDelegate = delegate;
        board.initializeBoard();
    }

    @Test
    public void fastCheckMate() {
        Piece p1 = board.getCell(2,1).getPiece();
        p1.willMove(board.getCell(2,3),true);

        Piece p2 = board.getCell(3,6).getPiece();
        p2.willMove(board.getCell(3,4),true);

        Piece p3 = board.getCell(1,1).getPiece();
        p3.willMove(board.getCell(1,3),true);

        Piece p4 = board.getCell(4,7).getPiece();
        p4.willMove(board.getCell(0,3),true);

        Assert.assertEquals(0, playerA.getScore());
        Assert.assertEquals(1, playerB.getScore());
    }
    @Test
    public void undoConstructTest() {
        Piece p1 = board.getCell(2,1).getPiece();
        p1.willMove(board.getCell(2,3),true);

        UndoCommand undo = new UndoCommand(p1, this.board, board.getCell(2,1), board.getCell(2,3), null);
        undo.execute();
        Assert.assertEquals(board.getCell(2, 1), p1.getChessboardCell());
    }

    @Test
    public void undoFuncTest() {
        Piece p1 = board.getCell(0,1).getPiece();
        p1.willMove(board.getCell(0,3),true);

        Piece p2 = board.getCell(3,6).getPiece();
        p2.willMove(board.getCell(3,4),true);

        Piece p3 = board.getCell(1,1).getPiece();
        p3.willMove(board.getCell(1,3),true);

        Piece p4 = board.getCell(4,7).getPiece();
        p4.willMove(board.getCell(0,3),true);

        this.board.stack.peek().execute();
        this.board.stack.pop();

        Assert.assertEquals(board.getCell(0, 3), p1.getChessboardCell());
    }
}
