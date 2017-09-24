import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/23/17.
 */
public class Advisor extends Bishop {
    public Advisor(Player player, ChessboardCell cell) {
        super(player, cell);
    }
    public boolean checkViablePath(ChessboardCell targetCell){
        if (this.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerA()) {
            if (targetCell.getY() >= 4) {
                return false;
            }
        }
        else {
            if (targetCell.getY() < 4) {
                return false;
            }
        }
        return super.checkViablePath(targetCell);
    }
}
