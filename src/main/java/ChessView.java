import javax.swing.*;
import java.awt.*;

/**
 * Created by songyuli on 9/24/17.
 */
public class ChessView {
    private ChessboardModel board;
    private int tileDimension = 50;
    public ChessView(ChessboardModel board){
        this.board = board;
        JFrame window = new JFrame("Basic Application Example");
        window.setSize(tileDimension * 8, tileDimension * 8);
        JPanel mainPanel = initializePanel();

        setUpTiles(mainPanel);

        window.setContentPane(mainPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setUpTiles(JPanel panel) {
        JList<JPanel> myList = new JList<JPanel>(this.board);
        JScrollPane listScroller = new JScrollPane(myList);
        listScroller.setPreferredSize(new Dimension(tileDimension * 8, tileDimension * 8));
        myList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myList.setVisibleRowCount(8);
        myList.setCellRenderer(new TileRender(this.board));
        myList.addMouseListener(new BoardMouseAdapter(this.board));
        panel.add(listScroller);
    }

    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(tileDimension * 8, tileDimension * 8));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }
}
