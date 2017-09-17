/**
 * Created by songyuli on 9/17/17.
 */
public class Chessboard {
    ChessboardCell [][] cells;
    public Chessboard(){
        cells = new ChessboardCell[5][8];
    }

    public void initializeBoard() {
        // TODO
    }

    public ChessboardCell getCell(int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            return cells[x][y];
        }
        return null;
    }
}
