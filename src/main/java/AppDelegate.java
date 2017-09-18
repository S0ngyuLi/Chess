/**
 * Created by songyuli on 9/17/17.
 */
public class AppDelegate {
    private Chessboard board;
    private Player playerA;
    private Player playerB;

    public AppDelegate(String nameA, String nameB) {
        this.playerA = new Player(nameA);
        this.playerB = new Player(nameB);
        this.board = new Chessboard(this.playerA, this.playerB, this);
    }

    public void initGame() {
        board.initializeBoard();
    }
    public void endWithWinner(Player player){
        player.win();
    }
}
