/**
 * Created by songyuli on 9/17/17.
 */
public abstract class Piece {
    private boolean isOnboard;
    private Player owner;
    private ChessboardCell cell;

    public Piece(Player player, ChessboardCell cell) {
        super();
        this.owner = player;
        cell.setPiece(this);
        this.cell = cell;
        this.isOnboard = true;
    }

    public void willMove(ChessboardCell targetCell) {
        // Does nothing
    }

    public void didMove(ChessboardCell targetCell) {
        // Does nothing
    }

    public void willRemoveFromBoard() {
        // Does nothing
    }

    public void didRemoveFromBoard() {
        // Does nothing
    }

    public boolean isOnBoard() {
        return this.isOnboard;
    }

    public ChessboardCell getChessboardCell() {
        return this.cell;
    }

    public abstract boolean checkViablePath(ChessboardCell targetCell);

    public Player getOwner() {
        return this.owner;
    }
}