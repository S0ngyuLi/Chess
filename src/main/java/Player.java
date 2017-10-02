/**
 * Created by songyuli on 9/17/17.
 *
 * Player Class. An aggregation of App Delegate class.
 */
public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.score = 0;
        this.name = name;
    }

    /*
    getter for the score variable.
     */
    public int getScore() {
        return this.score;
    }

    /*
    Increment player's score when winning.
     */
    public void win() {
        score += 1;
    }

    /*
    getter for the name variable.
     */
    public String getName(){
        return name;
    }

    /*
    setter for the name variable.
     */
    public void setName(String newName){
        this.name = newName;
    }
}
