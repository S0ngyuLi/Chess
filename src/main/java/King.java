import java.util.ArrayList;
import static java.lang.Math.abs;

/**
 * Created by songyuli on 9/17/17.
 *
 * King piece
 *
 */
public class King extends Piece {
    public King(Player player, ChessboardCell cell) {
        super(player, cell);
    }

    public boolean checkViablePath(ChessboardCell targetCell){
        if (abs(targetCell.getX() - this.getChessboardCell().getX())<= 1 && abs(targetCell.getY() - this.getChessboardCell().getY())<= 1) {
            if(targetCell.isVacant()!=true && targetCell.getPiece().getOwner() != this.getOwner()) {

                return true;
            }
            else if(targetCell.isVacant()!=true){
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ChessboardCell> getAllPossibleRoutes() {
        ArrayList<ChessboardCell> ret = new ArrayList<ChessboardCell>();
        Chessboard board = this.getChessboardCell().getChessboard();
        int x = this.getChessboardCell().getX();
        int y = this.getChessboardCell().getY();
        if (board.getCell(x-1, y-1) != null && this.checkViablePath(board.getCell(x-1, y-1))) {
            ret.add(board.getCell(x-1, y-1));
        }
        if (board.getCell(x, y-1) != null && this.checkViablePath(board.getCell(x, y-1))) {
            ret.add(board.getCell(x, y-1));
        }
        if (board.getCell(x-1, y) != null && this.checkViablePath(board.getCell(x-1, y))) {
            ret.add(board.getCell(x-1, y));
        }
        if (board.getCell(x+1, y+1) != null && this.checkViablePath(board.getCell(x+1, y+1))) {
            ret.add(board.getCell(x+1, y+1));
        }
        if (board.getCell(x+1, y) != null && this.checkViablePath(board.getCell(x+1, y))) {
            ret.add(board.getCell(x+1, y));
        }
        if (board.getCell(x, y+1) != null && this.checkViablePath(board.getCell(x, y+1))) {
            ret.add(board.getCell(x, y+1));
        }
        if (board.getCell(x+1, y-1) != null && this.checkViablePath(board.getCell(x+1, y-1))) {
            ret.add(board.getCell(x+1, y-1));
        }
        if (board.getCell(x-1, y+1) != null && this.checkViablePath(board.getCell(x-1, y+1))) {
            ret.add(board.getCell(x-1, y+1));
        }
        return ret;
    }
}
