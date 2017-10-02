/**
 * Created by songyuli on 9/17/17.
 *
 * main function of the software
 * run the game loop
 */
public class main {
    public static void main(String[] args) {
        AppDelegate app = new AppDelegate("White Player", "Black Player");
        app.initGame();
    }
}
