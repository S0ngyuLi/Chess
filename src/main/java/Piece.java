import java.util.ArrayList;

/**
 * Created by songyuli on 9/17/17.
 *
 * Abstract class of piece. All pieces classes should extends from this one, since only a Piece object can be accepted by tile object.
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

    /*
    Check if a piece should move to the target location.
     */
    public void willMove(ChessboardCell targetCell, boolean animated) {
        if(this.checkViablePath(targetCell) && this.isMyTurn(animated)) {
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

    /*
    Actually move the piece to target location.
     */
    public void didMove(ChessboardCell targetCell, boolean animated) {
        // TODO: Update GUI here
        setCell(targetCell);
        if (animated == true) {
            this.getChessboardCell().getChessboardModel().toggleAShouldMove();
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
            if (lastPieceCaptured.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerA()) {
                this.getChessboardCell().getChessboardModel().piecesForPlayerA.add(lastPieceCaptured);
                this.getChessboardCell().getChessboardModel().box.remove(lastPieceCaptured);
            }
            else {
                this.getChessboardCell().getChessboardModel().piecesForPlayerB.add(lastPieceCaptured);
                this.getChessboardCell().getChessboardModel().box.remove(lastPieceCaptured);
            }
            currLocation.setPiece(lastPieceCaptured);
            lastPieceCaptured.setCell(currLocation);
            lastPieceCaptured = null;
        }
    }

    /*
    set the cell of this piece.
     */
    private void setCell(ChessboardCell targetCell) {
        this.cell.clearCell();
        this.cell = targetCell;
        targetCell.setPiece(this);
        this.cell.getChessboardModel().notifyContentChanged();
    }

    /*
    Called before a piece is removed from the board. (i.e. is captured)
     */
    public void willRemoveFromBoard() {
        this.getChessboardCell().clearCell();
        this.cell = null;
        this.didRemoveFromBoard();
    }

    /*
    Called after a piece is removed from the board. (i.e. is captured)
     */
    public void didRemoveFromBoard() {
        // TODO: Update GUI here
        this.isOnboard = false;
    }

    /*
    Check if a piece is on the board.
     */
    public boolean isOnBoard() {
        return this.isOnboard;
    }

    /*
    Getter for the ChessboardCell variable. (reference)
     */
    public ChessboardCell getChessboardCell() {
        return this.cell;
    }

    /*
    getter for the owner variable.
     */
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

    /*
    Check if a piece can move to the target location. Different kind of pieces should implement this function differently.
     */
    abstract public boolean checkViablePath(ChessboardCell targetCell);

    public boolean isMyTurn(boolean  animated) {
        if (animated == false) {
            return true;
        }
        if ((this.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerA()) == this.getChessboardCell().getChessboardModel().getAShouldMove()) {
            return true;
        }
        return false;
    }
}