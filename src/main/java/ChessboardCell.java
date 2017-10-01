import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public ChessboardCell(int x, int y, ChessboardModel board) {
        super();
        this.x_cord = x;
        this.y_cord = y;
        this.chessboardModel = board;
        this.piece = null;
        this.deselect();
        this.symbol = "";
    }
    /*
    Change tile UI when selected.
     */
    public void isInCheck() {
        setBackground(new Color(255, 150, 58));
        chessboardModel.notifyContentChanged();
    }

    public void select(){
        this.setBackground((x_cord+y_cord) % 2 == 0? new Color(165, 155, 155) : new Color(204, 204, 204));
        this.chessboardModel.notifyContentChanged();
    }

    /*
    Change tile UI when deselected.
     */
    public void deselect(){
        this.setBackground((x_cord+y_cord) % 2 == 0? new Color(178, 158, 158) : new Color(226, 226, 226));
        this.chessboardModel.notifyContentChanged();
    }

    /*
    Notify datamodel to check if there is a checkmate.
     */
    public void checkCheckmate() {
        this.chessboardModel.checkCheckMate();
    }

    /*
    getter for Symbol variable.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /*
    getter for X_cord
     */
    public int getX() {
        return x_cord;
    }

    /*
    getter for Y_cord
     */
    public int getY() {
        return y_cord;
    }

    /*
    getter for vacant variable.
     */
    public boolean isVacant() {
        return vacant;
    }

    /*
    Reset every var to its original value.
     */
    public void clearCell(){
        this.piece = null;
        this.vacant = true;
        updateSymbol();
    }

    /*
    Helper function to update label on the tile.
     */
    public void updateLabel() {
        super.removeAll();
        JLabel label = new JLabel(symbol, SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 25));
        label.setForeground(Color.black);
        super.add(label);
    }

    /*
    Setter for piece. If current tile is already occupied, throw an exception.
     */
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

    /*
    Update symbol string after some changes are made. It is called everytime when you make any change to a tile.
     */
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
    /*
    getter for piece variable.
     */
    public Piece getPiece(){
        return this.piece;
    }

    /*
    Get cell at a designated coordinate.
     */
    public ChessboardCell getCell(int x, int y) {
        return chessboardModel.getCell(x, y);
    }

    /*
    Get data model object.
     */
    public ChessboardModel getChessboardModel() {
        return this.chessboardModel;
    }
}