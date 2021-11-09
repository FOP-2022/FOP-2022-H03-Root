package h03;

import fopbot.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class H2_2_UnitTest {

  @Test
  public void testZwillingspaare() {

    World.setSize(10, 10);
    World.setDelay(0);
    World.setVisible(false);

    RobotWithInitialState rob1 = new RobotWithInitialState(0, 2, Direction.UP, 10);
    RobotWithInitialState2 rob2 = new RobotWithInitialState2(3, 0, Direction.UP, 100);

    assertEquals(rob1.getRelativeNumberOfCoins(), rob2.getRelativeNumberOfCoins());

    rob1.move();
    rob2.move();
    assertEquals(rob1.getRelativeNumberOfCoins(), rob2.getRelativeNumberOfCoins());


    rob1.putCoin();
    rob2.putCoin();
    assertEquals(rob1.getRelativeNumberOfCoins(), rob2.getRelativeNumberOfCoins());

    rob1.turnLeft();
    rob2.turnLeft();
    assertEquals(rob1.getRelativeNumberOfCoins(), rob2.getRelativeNumberOfCoins());

    rob1.pickCoin();
    rob2.pickCoin();
    assertEquals(rob1.getRelativeNumberOfCoins(), rob2.getRelativeNumberOfCoins());
  }
}
