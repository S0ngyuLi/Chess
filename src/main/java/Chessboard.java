import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by songyuli on 9/17/17.
 */
public class Chessboard {
    private ChessboardCell [][] cells;
    private HashSet<Piece> box; // A box of pieces that have been captured
    private King kingForA;
    private King kingForB;
    private Player playerA; // Upper side player
    private Player playerB; // Lower side player
    private AppDelegate appDelegate;

    public Chessboard(Player playerA, Player playerB, AppDelegate delegate){
        this.cells = new ChessboardCell[8][8];
        this.playerA = playerA;
        this.playerB = playerB;
        this.appDelegate = delegate;
        this.box = new HashSet<Piece>();
    }

    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new ChessboardCell(i, j, this);
            }
        }

        for (int i = 0; i < 8; i++) {
            new Pawn(this.playerA, cells[1][i]);
            new Pawn(this.playerB, cells[6][i]);
        }

        this.kingForA = new King(this.playerA, cells[0][3]);
        this.kingForA = new King(this.playerA, cells[0][3]);

        new Queen(this.playerA, cells[0][4]);
        new Queen(this.playerB, cells[7][4]);

        new Rook(this.playerA, cells[0][0]);
        new Rook(this.playerA, cells[0][7]);
        new Rook(this.playerB, cells[7][0]);
        new Rook(this.playerB, cells[7][7]);

        new Knight(this.playerA, cells[0][1]);
        new Knight(this.playerA, cells[0][6]);
        new Knight(this.playerB, cells[7][1]);
        new Knight(this.playerB, cells[7][6]);

        new Bishop(this.playerA, cells[0][2]);
        new Bishop(this.playerA, cells[0][5]);
        new Bishop(this.playerB, cells[7][2]);
        new Bishop(this.playerB, cells[7][5]);
    }

    public void checkCheckMate() {
        List<ChessboardCell> routeForA = kingForA.getAllPossibleRoutes();
        if (routeForA.size() == 0) {
            appDelegate.endWithWinner(this.playerB);
            return;
        }

        List<ChessboardCell> routeForB = kingForB.getAllPossibleRoutes();
        if (routeForB.size() == 0) {
            appDelegate.endWithWinner(this.playerA);
            return;
        }

        return; // Nobody wins
    }

    public ChessboardCell getCell(int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            return cells[x][y];
        }
        return null;
    }
}
