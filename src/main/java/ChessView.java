import javax.swing.*;
import java.awt.*;

/**
 * Created by songyuli on 9/24/17.
 */
public class ChessView {
    public static void main(String[] args) {
        new ChessView();
    }
    public ChessView(){
        JFrame window = new JFrame("Basic Application Example");
        window.setSize(800, 800);
        JPanel mainPanel = initializePanel();

        setUpTiles(mainPanel);

        window.setContentPane(mainPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setUpTiles(JPanel panel) {
        JComponent[] data = new JComponent[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int index = i * 8 + j;
                data[index] = new JPanel();
                data[index].setPreferredSize(new Dimension(100,100));
                data[index].setBackground((i+j) % 2 == 0? Color.black : Color.white);
                data[index].setLayout(new BorderLayout());
            }
        }
        JList<JComponent> myList = new JList<JComponent>(data);
        JScrollPane listScroller = new JScrollPane(myList);
        listScroller.setPreferredSize(new Dimension(800, 800));
        myList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        myList.setVisibleRowCount(8);
        myList.setCellRenderer(new TileRender());
        panel.add(listScroller);
    }

    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(800,800));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }
}
