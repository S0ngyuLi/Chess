/**
 * Created by songyuli on 9/17/17.
 *
 * Delegate the whole software logic
 *
 */
public class AppDelegate {
    private ChessboardModel board;
    private Player playerA;
    private Player playerB;
    private ChessView mainView;

    public AppDelegate(Player playerA, Player playerB, ChessboardModel board) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.board = board;
    }

    public AppDelegate(String nameA, String nameB) {
        this.playerA = new Player(nameA);
        this.playerB = new Player(nameB);
        this.board = new ChessboardModel(this.playerA, this.playerB, this);
    }

    /*
    Initialize the game with a GUI running
     */
    public void initGame() {
        board.initializeBoard();
        mainView = new ChessView(this.board);
        // TODO: Waiting for input
    }

    /*
    Temporarily suspend the game to trigger a winning scenario.
     */
    public void endWithWinner(Player player){
        player.win();
        if(mainView != null) {
            mainView.triggerWin(player.getName());
        }
    }
}
