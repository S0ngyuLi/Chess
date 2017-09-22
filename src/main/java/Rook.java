import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/17/17.
 *
 * Rook Piece
 */
public class Rook extends Piece {
    public Rook(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        if (targetCell == this.getChessboardCell()) {
            return false;
        }
        if (targetCell.getX() == this.getChessboardCell().getX() && targetCell.getY() != this.getChessboardCell().getY()) {
            int targetIsUpper = targetCell.getY() < this.getChessboardCell().getY() ? -1 : 1;
            int verticalDistance = abs(targetCell.getY() - this.getChessboardCell().getY());
            for (int i = 0; i < verticalDistance; i++) {
                ChessboardCell currentCell = this.getChessboardCell().getCell(this.getChessboardCell().getX(), this.getChessboardCell().getY() + targetIsUpper * (i+1));
                if (currentCell.isVacant() != true && currentCell.getPiece().getOwner() == this.getOwner()) {
                    return false;
                }
                else if (currentCell.isVacant() != true && i == verticalDistance - 1 && currentCell.getPiece().getOwner() != this.getOwner()) {
                    return true;
                }
                else if (currentCell.isVacant() != true) {
                    return false;
                }
            }
            return true;
        }

        else if (targetCell.getX() != this.getChessboardCell().getX() && targetCell.getY() == this.getChessboardCell().getY()) {
            int targetIsLeft = targetCell.getX() < this.getChessboardCell().getX() ? -1 : 1;
            int horizentalDistance = abs(targetCell.getX() - this.getChessboardCell().getX());
            for (int i = 0; i < horizentalDistance; i++) {
                ChessboardCell currentCell = this.getChessboardCell().getCell(this.getChessboardCell().getX() + targetIsLeft * (i+1), this.getChessboardCell().getY());
                if (currentCell.isVacant() != true && currentCell.getPiece().getOwner() == this.getOwner()) {
                    return false;
                }
                else if (currentCell.isVacant() != true && i == horizentalDistance - 1 && currentCell.getPiece().getOwner() != this.getOwner()) {
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
