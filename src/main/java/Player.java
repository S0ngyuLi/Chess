/**
 * Created by songyuli on 9/17/17.
 */
public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.score = 0;
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }
    public void win() {
        score += 1;
    }
}
