import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * Created by songyuli on 9/24/17.
 */
public class ChessView {
    private ChessboardModel board;
    private JFrame window;
    public static int tileDimension = 50;

    public ChessView(ChessboardModel board){
        this.board = board;
        this.window = new JFrame("Chess Game");
        window.setSize(tileDimension * 8 + 6, tileDimension * 9 + 48);
        JPanel mainPanel = initializeMainPanel();
        setUpControllers(mainPanel);
        JPanel bottomBar = initializeMenuPanel();
        setUpButtons(bottomBar);
        mainPanel.add(bottomBar, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu usersMenu = new JMenu("Users");
        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.stack.isEmpty()){
                    board.stack.pop().execute();
                }
            }
        });
        JMenuItem checkScoreItem = new JMenuItem("Check Score");
        checkScoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, board.getPlayerA().getName() + " score: " + board.getPlayerA().getScore() + "\n" +
                        board.getPlayerB().getName() + " score: " + board.getPlayerB().getScore() + "\n");
            }
        });
        JMenuItem changeNameForA = new JMenuItem("Change left player's name");
        changeNameForA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = (String)JOptionPane.showInputDialog(
                        null, "Enter new name for left player.", "Change Name", JOptionPane.PLAIN_MESSAGE);
                board.getPlayerA().setName(newName);
            }
        });

        JMenuItem changeNameForB = new JMenuItem("Change right player's name");
        changeNameForB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = (String)JOptionPane.showInputDialog(
                        null, "Enter new name for right player.", "Change Name", JOptionPane.PLAIN_MESSAGE);
                board.getPlayerB().setName(newName);
            }
        });
        menuBar.add(usersMenu);
        usersMenu.add(undoItem);
        usersMenu.add(checkScoreItem);
        usersMenu.add(changeNameForA);
        usersMenu.add(changeNameForB);
        window.setJMenuBar(menuBar);

        window.setContentPane(mainPanel);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
    Display game results through a diaglog box.
     */
    public void triggerWin(String playerName) {
        JOptionPane.showMessageDialog(window,
                "Let's congratulate " + playerName + ".",
                "Player " + playerName + " wins!" ,
                JOptionPane.PLAIN_MESSAGE);
        board.initializeBoard();
    }

    /*
    Returns a JPanel object that should be used as menu bar.
     */
    private JPanel initializeMenuPanel() {
        JPanel menuBar = new JPanel();
        menuBar.setPreferredSize(new Dimension(tileDimension * 3, tileDimension));
        menuBar.setLayout(new FlowLayout());
        return menuBar;
    }

    /*
    Setup controllers for the view.
     */
    private void setUpControllers(JPanel panel) {
        JList<JPanel> myList = new ChessboardViewModel(this.board);
        JScrollPane listScroller = new JScrollPane(myList);
        listScroller.setPreferredSize(new Dimension(tileDimension * 8, tileDimension * 8));
        panel.add(listScroller);
    }

    /*
    Set up buttons for menu bar.
     */
    private void setUpButtons(JPanel menuBar) {
        JButton initializeButton = new JButton("Start over");
        initializeButton.setSize(3 * tileDimension, tileDimension);
        initializeButton.setVisible(true);
        initializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Do both players agree to a draw and start over?", "Warning", YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    board.initializeBoard();
                }
            }
        });
        JButton initializeFairyButton = new JButton("Fairy pieces");
        initializeFairyButton.setSize(3 * tileDimension, tileDimension);
        initializeFairyButton.setVisible(true);
        initializeFairyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Do both players agree to a draw and start over with fairy pieces?", "Warning",YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    board.initializeFairyBoard();
                }
            }
        });

        menuBar.add(initializeButton);
        menuBar.add(initializeFairyButton);
    }

    /*
    returns a JPanel object that should be displayed in the center of the app.
     */
    private JPanel initializeMainPanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(tileDimension * 8, tileDimension * 8));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }
}
