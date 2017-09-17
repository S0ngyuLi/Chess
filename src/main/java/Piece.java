/**
 * Created by songyuli on 9/17/17.
 */
public interface Piece {
    public void willMove();
    public void didMove();
    public void willRemoveFromBoard();
    public void didRemoveFromBoard();
    public boolean isOnBoard();
    public Chessboard getChessboardCell();
    public boolean checkViablePath(ChessboardCell targetCell);
    public Player getOwner();
}