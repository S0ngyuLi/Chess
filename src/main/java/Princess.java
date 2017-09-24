import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/23/17.
 */
public class Princess extends Knight {
    public Princess(Player player, ChessboardCell cell) {
        super(player, cell);
    }
    public boolean checkViablePath(ChessboardCell targetCell){
        if(super.checkViablePath(targetCell) == true) {
            return true;
        }
        else {
            if (targetCell == this.getChessboardCell()) {
                return false;
            }
            if (abs(targetCell.getX() - this.getChessboardCell().getX()) == abs(targetCell.getY() - this.getChessboardCell().getY())) {
                int targetOnLeft = targetCell.getX() < this.getChessboardCell().getX() ? -1 : 1;
                int targetOnUpper = targetCell.getY() < this.getChessboardCell().getY() ? -1 : 1;
                int verticalDistance = abs(targetCell.getX() - this.getChessboardCell().getX());

                for (int i = 0; i < verticalDistance; i++) {
                    ChessboardCell currentCell = this.getChessboardCell().getCell(this.getChessboardCell().getX() + targetOnLeft * (i+1), this.getChessboardCell().getY() + targetOnUpper * (i+1));
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
}
