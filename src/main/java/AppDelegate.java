/**
 * Created by songyuli on 9/17/17.
 *
 * Delegate the whole software logic
 *
 */
public class AppDelegate {
    private Chessboard board;
    private Player playerA;
    private Player playerB;

    public AppDelegate(Player playerA, Player playerB, Chessboard board) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.board = board;
    }

    public AppDelegate(String nameA, String nameB) {
        this.playerA = new Player(nameA);
        this.playerB = new Player(nameB);
        this.board = new Chessboard(this.playerA, this.playerB, this);
    }

    public void initGame() {
        board.initializeBoard();
        // TODO: Waiting for input
    }
    public void endWithWinner(Player player){
        player.win();
    }
}
