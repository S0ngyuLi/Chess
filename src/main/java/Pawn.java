import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/17/17.
 *
 * Pawn piece
 */
public class Pawn extends Piece {
    public Pawn(Player player, ChessboardCell cell) {
        super(player, cell);
        this.timesMoved = 0;
    }
    private int timesMoved;
    public boolean checkViablePath(ChessboardCell targetCell){
        if (targetCell == this.getChessboardCell()) {
            return false;
        }
        int targetCellX = targetCell.getX();
        int targetCellY = targetCell.getY();
        if (timesMoved == 0) {
            if (targetCellX == this.getChessboardCell().getX() && abs(targetCellY - this.getChessboardCell().getY()) == 2 && targetCell.isVacant()) {
                return true;
            }
        }
        if (targetCellX == this.getChessboardCell().getX() && targetCell.isVacant() && ((this.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerA() && targetCellY == this.getChessboardCell().getY() + 1) || (this.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerB() && targetCellY == this.getChessboardCell().getY() - 1))) {
            return true;
        }
        if(this.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerA() && targetCell.isVacant()!= true && targetCell.getPiece().getOwner()!= this.getOwner() && abs(targetCellX - this.getChessboardCell().getX()) == 1 && targetCellY == this.getChessboardCell().getY() + 1) {

            return true;
        }

        if(this.getOwner() == this.getChessboardCell().getChessboardModel().getPlayerB() && targetCell.isVacant()!=true && targetCell.getPiece().getOwner()!= this.getOwner() && abs(targetCellX - this.getChessboardCell().getX()) == 1 && targetCellY == this.getChessboardCell().getY() - 1) {

            return true;
        }
        return false;
    }

    public void didMove(ChessboardCell targetCell, boolean animated) {
        super.didMove(targetCell, animated);
        if (animated == true) {
            timesMoved += 1;
        }
    }

    public void undo() {
        if (timesMoved > 0) {
            timesMoved -= 1;
        }
    }
}
