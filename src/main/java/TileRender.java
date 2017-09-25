import javax.swing.*;
import java.awt.*;

/**
 * Created by songyuli on 9/24/17.
 *
 * Render for JList view. Responsible for rendering every
 */
class TileRender implements ListCellRenderer {
    private ChessboardModel board;
    public TileRender(ChessboardModel board) {
        super();
        this.board = board;
    }
    public Component getListCellRendererComponent(JList jlist, Object value, int cellIndex, boolean isSelected, boolean cellHasFocus) {
        ChessboardCell component = (ChessboardCell) value;
        int i = cellIndex / 8;
        int j = cellIndex % 8;

        if (value instanceof JPanel) {
//            component.setBackground((i+j) % 2 == 0? new Color(178, 158, 158) : new Color(226, 226, 226));
            component.updateLabel();
            return component;
        }
        else
        {
            return new JLabel("Unknown Source");
        }
    }
}