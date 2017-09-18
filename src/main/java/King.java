import java.util.List;

/**
 * Created by songyuli on 9/17/17.
 */
public class King extends Piece {
    public King(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        return true; // TODO
    }

    public List<ChessboardCell> getAllPossibleRoutes() {
        return null; // TODO
    }
}
