import javax.swing.*;
import java.awt.*;

/**
 * Created by songyuli on 9/24/17.
 */
class TileRender implements ListCellRenderer {
    public Component getListCellRendererComponent(JList jlist,
                                                  Object value,
                                                  int cellIndex,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {
        if (value instanceof JPanel)
        {
            Component component = (Component) value;
            return component;
        }
        else
        {
            return new JLabel("Unknown Source");
        }
    }
}