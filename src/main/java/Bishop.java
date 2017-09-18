import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/17/17.
 *
 * Bishop piece class
 *
 */
public class Bishop extends Piece {
    public Bishop(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        if (abs(targetCell.getX() - this.getChessboardCell().getX()) == abs(targetCell.getY() - this.getChessboardCell().getY())) {
            int targetOnLeft = targetCell.getX() < this.getChessboardCell().getX() ? -1 : 1;
            int targetOnUpper = targetCell.getY() < this.getChessboardCell().getY() ? -1 : 1;
            int verticalDistance = abs(targetCell.getX() - this.getChessboardCell().getX());

            for (int i = 0; i < verticalDistance; i++) {
                ChessboardCell currentCell = this.getChessboardCell().getCell(this.getChessboardCell().getX() + targetOnLeft * i, this.getChessboardCell().getY() + targetOnUpper * i);
                if (i == verticalDistance -1 && currentCell.isVacant() != true && currentCell.getPiece().getOwner() != this.getOwner()) {
                    return true;
                }
                else if (currentCell.isVacant() != true) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
