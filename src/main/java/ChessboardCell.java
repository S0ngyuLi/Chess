import javax.swing.*;
import java.awt.*;

/**
 * Created by songyuli on 9/17/17.
 *
 * ChessboardModel Cell object, which is composition of chessboardModel
 *
 */
public class ChessboardCell extends JPanel {
    private Piece piece;
    private int x_cord;
    private int y_cord;
    private ChessboardModel chessboardModel;
    private boolean vacant = true;

    private String symbol;

    public void checkCheckmate() {
        this.chessboardModel.checkCheckMate();
    }

    public ChessboardCell(int x, int y, ChessboardModel board) {
        super();
        this.x_cord = x;
        this.y_cord = y;
        this.chessboardModel = board;
        this.piece = null;

        this.symbol = "";
    }

    public String getSymbol() {
        return this.symbol;
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
        updateSymbol();
    }

    public void updateLabel() {
        super.removeAll();
        JLabel label = new JLabel(symbol, SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 25));
        label.setForeground(Color.black);
        super.add(label);
    }

    public void setPiece(Piece newPiece){
        if (this.piece != null) {
            throw new ArithmeticException("Cell Error for " + x_cord + ", " + y_cord + ": Cell should not hold two pieces.");
        }
        else {
            this.piece = newPiece;
            this.vacant = false;
            updateSymbol();
        }
    }

    private void updateSymbol() {
        if (this.isVacant()) {
            symbol = "";
            return;
        }
        if (this.piece instanceof Pawn) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♙";
            }
            else {
                symbol = "♟";
            }
        }
        if (this.piece instanceof Rook) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♖";
            }
            else {
                symbol = "♜";
            }
        }
        if (this.piece instanceof Knight) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♘";
            }
            else {
                symbol = "♞";
            }
        }
        if (this.piece instanceof Bishop) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♗";
            }
            else {
                symbol = "♝";
            }
        }
        if (this.piece instanceof Queen) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♕";
            }
            else {
                symbol = "♛";
            }
        }
        if (this.piece instanceof King) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♔";
            }
            else {
                symbol = "♚";
            }
        }
        if (this.piece instanceof Princess) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "♤";
            }
            else {
                symbol = "♠";
            }
        }
        if (this.piece instanceof Advisor) {
            if(this.piece.getOwner() == this.chessboardModel.getPlayerA()) {
                symbol = "⚐";
            }
            else {
                symbol = "⚑";
            }
        }
    }

    public Piece getPiece(){
        return this.piece;
    }

    public ChessboardCell getCell(int x, int y) {
        return chessboardModel.getCell(x, y);
    }
    public ChessboardModel getChessboardModel() {
        return this.chessboardModel;
    }
}