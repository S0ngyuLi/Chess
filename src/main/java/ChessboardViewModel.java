import javax.swing.*;

/**
 * Created by songyuli on 9/25/17.
 *
 * Controller (View Model) for the whole board. Extends from JList class.
 */
public class ChessboardViewModel extends JList<JPanel> {
    public ChessboardViewModel(ChessboardModel board){
        super(board);
        this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setVisibleRowCount(8);
        this.setCellRenderer(new TileRender(board));
        this.addMouseListener(new BoardMouseAdapter(board));
    }
}
