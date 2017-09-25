import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/25/17.
 */
public class AdvisorTest {
    Player playerA;
    Player playerB;
    ChessboardModel board;
    Piece advisor;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new ChessboardModel(playerA, playerB, null);
        board.initializeBoard();
        advisor = new Advisor(this.playerA, board.getCell(3,3));
    }

    @Test
    public void testAdvisorCanMoveDiagonally() {
        advisor.willMove(board.getCell(2, 2), false);
        Assert.assertEquals(board.getCell(2, 2), advisor.getChessboardCell());
    }

    @Test
    public void testBishopInvalidMove() {
        advisor.willMove(board.getCell(5,5), false);
        Assert.assertEquals(board.getCell(3,3), advisor.getChessboardCell());
        Piece rivalAdvisor = new Advisor(this.playerB, board.getCell(5,5));
        rivalAdvisor.willMove(board.getCell(2,2), false);
        Assert.assertEquals(board.getCell(5,5), rivalAdvisor.getChessboardCell());
    }
}
