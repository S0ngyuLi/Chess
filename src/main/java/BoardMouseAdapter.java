import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by songyuli on 9/24/17.
 */
class BoardMouseAdapter extends MouseAdapter {
    private ChessboardModel board;
    private Piece selectedPiece;

    public BoardMouseAdapter(ChessboardModel board) {
        super();
        this.board = board;
        this.selectedPiece = null;
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        JList list = (JList) mouseEvent.getSource();
        int index = list.locationToIndex(mouseEvent.getPoint());
        int i = index / 8;
        int j = index % 8;
        if (index >= 0) {
            if (this.selectedPiece == null) {
                System.out.println("Selected: " + i + ", " + j);
                if (board.getCell(i, j).isVacant()) {
                    return; // Return if selected cell is vacant
                }
                else {
                    selectedPiece = board.getCell(i, j).getPiece();
                }
            }
            else {
                System.out.println("Attempted to move to: " + i + ", " + j);
                this.selectedPiece.willMove(board.getCell(i, j), true);
                this.selectedPiece = null;
            }
        }
    }
}