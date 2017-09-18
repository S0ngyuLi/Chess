/**
 * Created by songyuli on 9/17/17.
 *
 * Queen Piece
 */
public class Queen extends Piece {
    public Queen(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        return true; // TODO
    }
}
