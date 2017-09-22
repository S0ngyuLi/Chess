import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class PawnTest {
    Player playerA;
    Player playerB;
    Chessboard board;
    Piece testingPawn;
    @Before
    public void setEnvironment() {
        playerA = new Player("A");
        playerB = new Player("B");
        board = new Chessboard(playerA, playerB, null);
        board.initializeBoard();
        testingPawn = new Pawn(this.playerA, board.getCell(4,2));
    }

    @Test
    public void testPawnCanMoveTwoSteps(){
        testingPawn.willMove(board.getCell(4,4), false);
        Assert.assertEquals(board.getCell(4,4), testingPawn.getChessboardCell());
    }

    @Test
    public void testPawnCannotMakeTwoStepsAfterInitialMoving() {
        testingPawn.willMove(board.getCell(4,3), true);
        testingPawn.willMove(board.getCell(4,5), true);
        Assert.assertEquals(board.getCell(4,3), testingPawn.getChessboardCell());
    }

}
