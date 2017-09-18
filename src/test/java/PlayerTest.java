import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songyuli on 9/18/17.
 */
public class PlayerTest {
    Player player;
    @Before
    public void setup(){
        player = new Player("Test");
    }

    @Test
    public void checkInitScore(){
        Assert.assertEquals(0, player.getScore());
    }

    @Test
    public void incrementScore(){
        player.win();
        Assert.assertEquals(1, player.getScore());
    }

}
