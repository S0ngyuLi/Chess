import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by songyuli on 9/24/17.
 */
public class ChessView {
    private ChessboardModel board;
    public static int tileDimension = 50;
    public ChessView(ChessboardModel board){
        this.board = board;
        JFrame window = new JFrame("Chess Game");
        window.setSize(tileDimension * 8 + 4, tileDimension * 9 + 30);
        JPanel mainPanel = initializeMainPanel();
        setUpTiles(mainPanel);
        JPanel menuBar = initializeMenuPanel();
        setUpButtons(menuBar);
        mainPanel.add(menuBar, BorderLayout.SOUTH);
        window.setContentPane(mainPanel);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel initializeMenuPanel() {
        JPanel menuBar = new JPanel();
        menuBar.setPreferredSize(new Dimension(tileDimension * 3, tileDimension));
        menuBar.setLayout(new FlowLayout());
        return menuBar;
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

    private void setUpButtons(JPanel panel) {
        JButton initializeButton = new JButton("Start over");
        initializeButton.setSize(3 * tileDimension, tileDimension);
        initializeButton.setVisible(true);
        initializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.initializeBoard();
            }
        });
        JButton initializeFairyButton = new JButton("Start with fairy pieces");
        initializeFairyButton.setSize(3 * tileDimension, tileDimension);
        initializeFairyButton.setVisible(true);
        initializeFairyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.initializeFairyBoard();
            }
        });
        panel.add(initializeButton);
        panel.add(initializeFairyButton);
    }

    private JPanel initializeMainPanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(tileDimension * 8, tileDimension * 8));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }
}
