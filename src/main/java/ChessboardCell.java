/**
 * Created by songyuli on 9/17/17.
 *
 * Chessboard Cell object, which is composition of chessboard
 *
 */
public class ChessboardCell {
    private Piece piece;
    private int x_cord;
    private int y_cord;
    private Chessboard chessboard;
    private boolean vacant = true;

    public void checkCheckmate() {
        this.chessboard.checkCheckMate();
    }

    public ChessboardCell(int x, int y, Chessboard board) {
        this.x_cord = x;
        this.y_cord = y;
        this.chessboard = board;
        this.piece = null;
    }

    public int getX() {
        return x_cord;
    }
    public int getY() {
        return y_cord;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void clearCell(){
        this.piece = null;
        this.vacant = true;
    }

    public void setPiece(Piece newPiece){
        if (this.piece != null) {
            throw new ArithmeticException("Cell Error for " + x_cord + ", " + y_cord + ": Cell should not hold two pieces.");
        }
        else {
            this.piece = newPiece;
            this.vacant = false;
        }
    }

    public Piece getPiece(){
        return this.piece;
    }

    public ChessboardCell getCell(int x, int y) {
        return chessboard.getCell(x, y);
    }
    public Chessboard getChessboard() {
        return this.chessboard;
    }
}