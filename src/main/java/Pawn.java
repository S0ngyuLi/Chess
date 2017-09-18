import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/17/17.
 *
 * Pawn piece
 */
public class Pawn extends Piece {
    public Pawn(Player player, ChessboardCell cell) {
        super(player, cell);
    }
    private boolean isNeverMoved = true;
    public boolean checkViablePath(ChessboardCell targetCell){
        int targetCellX = targetCell.getX();
        int targetCellY = targetCell.getY();
        if (isNeverMoved) {
            this.isNeverMoved = false;
            if (targetCellX == this.getChessboardCell().getX() && abs(targetCellY - this.getChessboardCell().getY()) == 2 && targetCell.isVacant()) {
                return true;
            }
        }
        if (targetCellX == this.getChessboardCell().getX() && targetCell.isVacant() && ((this.getOwner() == this.getChessboardCell().getChessboard().getPlayerA() && targetCellY == this.getChessboardCell().getY() + 1) || (this.getOwner() == this.getChessboardCell().getChessboard().getPlayerB() && targetCellY == this.getChessboardCell().getY() - 1))) {
            return true;
        }
        if(this.getOwner() == this.getChessboardCell().getChessboard().getPlayerA() && targetCell.isVacant()!= true && targetCell.getPiece().getOwner()!= this.getOwner() && abs(targetCellX - this.getChessboardCell().getX()) == 1 && targetCellY == this.getChessboardCell().getY() + 1) {
            return true;
        }

        if(this.getOwner() == this.getChessboardCell().getChessboard().getPlayerB() && targetCell.isVacant()!=true && targetCell.getPiece().getOwner()!= this.getOwner() && abs(targetCellX - this.getChessboardCell().getX()) == 1 && targetCellY == this.getChessboardCell().getY() - 1) {
            return true;
        }
        return false;
    }
}
