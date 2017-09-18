import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/17/17.
 *
 * Knight Piece
 *
 */
public class Knight extends Piece {
    public Knight(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        int targetCellX = targetCell.getX();
        int targetCellY = targetCell.getY();
        if (targetCell.isVacant() || (targetCell.isVacant()==false && targetCell.getPiece().getOwner() != this.getOwner())) {
            if (targetCellX - this.getChessboardCell().getX() == 1 && targetCellY - this.getChessboardCell().getY() == 2) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == -1 && targetCellY - this.getChessboardCell().getY() == 2) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == 1 && targetCellY - this.getChessboardCell().getY() == -2) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == -1 && targetCellY - this.getChessboardCell().getY() == -2) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == 2 && targetCellY - this.getChessboardCell().getY() == 1) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == -2 && targetCellY - this.getChessboardCell().getY() == 1) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == 2 && targetCellY - this.getChessboardCell().getY() == -1) {
                return true;
            }
            if (targetCellX - this.getChessboardCell().getX() == -2 && targetCellY - this.getChessboardCell().getY() == -1) {
                return true;
            }
        }
        return false;
    }
}
