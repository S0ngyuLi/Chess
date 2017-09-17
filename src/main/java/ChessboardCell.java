/**
 * Created by songyuli on 9/17/17.
 */
public class ChessboardCell {
    private Piece piece;
    private int x_cord;
    private int y_cord;
    private Chessboard chessboard;

    public int getX() {
        return x_cord;
    }
    public int getY() {
        return y_cord;
    }

    public boolean checkIfVacant() {
        if(this.piece == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public Piece getPiece(){
        return this.piece;
    }

    public ChessboardCell getCell(int x, int y) {
        return chessboard.getCell(x, y);
    }
}