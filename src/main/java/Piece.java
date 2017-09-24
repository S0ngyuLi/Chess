import java.util.ArrayList;

/**
 * Created by songyuli on 9/17/17.
 *
 * Abstract class of piece
 */
public abstract class Piece {
    private boolean isOnboard;
    private Player owner;
    private ChessboardCell cell;

    private ChessboardCell lastLocation;
    private Piece lastPieceCaptured;

    public Piece(Player player, ChessboardCell cell) {
        this.owner = player;
        cell.setPiece(this);
        this.cell = cell;
        this.isOnboard = true;

        this.lastLocation = null;
        this.lastPieceCaptured = null;
    }

    public void willMove(ChessboardCell targetCell, boolean animated) {
        if(this.checkViablePath(targetCell)) {
            this.lastLocation = this.getChessboardCell();
            if (targetCell.isVacant() != true) {
                this.lastPieceCaptured = targetCell.getPiece();
                this.getChessboardCell().getChessboardModel().capturePieces(targetCell);
            }
            else {
                this.lastPieceCaptured = null;
            }
            this.didMove(targetCell, animated);
        }
    }

    public void didMove(ChessboardCell targetCell, boolean animated) {
        // TODO: Update GUI here
        setCell(targetCell);
        if (animated == true) {
            this.cell.checkCheckmate();
        }
    }
    /*
     * resume last step. Should only be used immediately after a move to verify checkmate
     */
    public void resume() {
        ChessboardCell currLocation = this.getChessboardCell();
        if(lastLocation != null) {
            setCell(lastLocation);
            lastLocation = null;
        }
        if(lastPieceCaptured != null) {
            currLocation.setPiece(lastPieceCaptured);
            lastPieceCaptured.setCell(currLocation);
            lastPieceCaptured = null;
        }
    }

    private void setCell(ChessboardCell targetCell) {
        this.cell.clearCell();
        this.cell = targetCell;
        targetCell.setPiece(this);
        this.cell.getChessboardModel().notifyContentChanged();
    }

    public void willRemoveFromBoard() {
        this.getChessboardCell().clearCell();
        this.cell = null;
        this.didRemoveFromBoard();
    }

    public void didRemoveFromBoard() {
        // TODO: Update GUI here
        this.isOnboard = false;
    }

    public boolean isOnBoard() {
        return this.isOnboard;
    }

    public ChessboardCell getChessboardCell() {
        return this.cell;
    }

    public Player getOwner() {
        return this.owner;
    }

    /*
     *  Use brute force to traverse all cells, and find all possible movements of piece.
     *  Should be overridden to a more efficient implementation in substantial classes
     * */

    public ArrayList<ChessboardCell> getAllPossibleRoutes() {
        ArrayList<ChessboardCell> ret = new ArrayList<ChessboardCell>();
        if (this.isOnboard) {
            this.getChessboardCell().getChessboardModel().traverseCell(ret, targetCell -> {
                return this.checkViablePath(targetCell);
            }); // Hacky: fancy lambda expression here
        }
        return ret;
    }

    public abstract boolean checkViablePath(ChessboardCell targetCell);
}