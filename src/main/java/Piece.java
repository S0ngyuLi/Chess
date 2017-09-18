/**
 * Created by songyuli on 9/17/17.
 *
 * Abstract class of piece
 */
public abstract class Piece {
    private boolean isOnboard;
    private Player owner;
    private ChessboardCell cell;

    public Piece(Player player, ChessboardCell cell) {
        this.owner = player;
        cell.setPiece(this);
        this.cell = cell;
        this.isOnboard = true;
    }

    public void willMove(ChessboardCell targetCell) {
        if(this.checkViablePath(targetCell)) {
            if (targetCell.isVacant() != true) {
                this.getChessboardCell().getChessboard().capturePieces(targetCell);
            }
            this.didMove(targetCell);
        }
    }

    public void didMove(ChessboardCell targetCell) {
        // TODO: Update GUI here
        targetCell.setPiece(this);
        this.cell = targetCell;
        this.cell.checkCheckmate();
    }

    public void willRemoveFromBoard() {
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

    public abstract boolean checkViablePath(ChessboardCell targetCell);
}