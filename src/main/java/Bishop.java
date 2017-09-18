/**
 * Created by songyuli on 9/17/17.
 */
public class Bishop extends Piece {
    public Bishop(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        return true; // TODO
    }
}
