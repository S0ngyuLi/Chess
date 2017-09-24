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

    public void initGame() {
        board.initializeBoard();
        ChessView mainView = new ChessView(this.board);
        // TODO: Waiting for input
    }

    public void endWithWinner(Player player){
        player.win();
    }
    public static void main(String[] args) {
        AppDelegate app = new AppDelegate("A", "B");
        app.initGame();
    }
}
