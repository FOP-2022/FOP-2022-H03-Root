package h03;

import fopbot.Direction;
import org.junit.jupiter.api.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("H1.3")
@TestForSubmission("h03")
public class H1_3 {

  static Method method_getRelativeX, method_getRelativeY, method_getRelativeDirection, method_getRelativeNumberOfCoins;

  private static void getRelativeXYGetter() {
    try {
      method_getRelativeX = RobotWithInitialState.class.getDeclaredMethod("getRelativeX", new Class[]{});
      method_getRelativeY = RobotWithInitialState.class.getDeclaredMethod("getRelativeY", new Class[]{});
    } catch (NoSuchMethodException | SecurityException e) {
      fail("Getter nicht gefunden");
      e.printStackTrace();
    }
  }

  private static void getRelativeDirectionGetter() {
    try {
      method_getRelativeDirection = RobotWithInitialState.class.getDeclaredMethod("getRelativeDirection", new Class[]{});
    } catch (NoSuchMethodException | SecurityException e) {
      fail("Getter nicht gefunden");
      e.printStackTrace();
    }
  }

  private static void getRelativeNumberOfCoinsGetter() {
    try {
      method_getRelativeNumberOfCoins = RobotWithInitialState.class.getDeclaredMethod("getRelativeNumberOfCoins", new Class[]{});
    } catch (NoSuchMethodException | SecurityException e) {
      fail("Getter nicht gefunden");
      e.printStackTrace();
    }
  }


  @Test
  public void relativeGetterExist() { //Attribute existirgenNICHT
    getRelativeXYGetter();
    getRelativeDirectionGetter();
    getRelativeNumberOfCoinsGetter();
  }

  @Test
  public void relativeXYGetterCorrect() {
    getRelativeXYGetter();

    TutorTests.setupWorld(20);
    Random random = new Random();
    RobotWithInitialState rob = new RobotWithInitialState(10, 10, Direction.UP, 100);
    for (int i = 0; i < 100; i++) {
      int relX = random.nextInt(20) - 10;
      int relY = random.nextInt(20) - 10;

      rob.setX(10 + relX);
      rob.setY(10 + relY);

      assertEquals(relX, rob.getRelativeX());
      assertEquals(relY, rob.getRelativeY());
    }
  }

  @Test
  public void relativeDirectionTest() {
    getRelativeDirectionGetter();
    TutorTests.setupWorld(1);
    for (int r = 0; r < 4; r++) {
      RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.values()[r], 0);
      //TODO
      assertEquals(Direction.UP, rob.getRelativeDirection());
      rob.turnLeft();
      assertEquals(Direction.LEFT, rob.getRelativeDirection());
      rob.turnLeft();
      assertEquals(Direction.DOWN, rob.getRelativeDirection());
      rob.turnLeft();
      assertEquals(Direction.RIGHT, rob.getRelativeDirection());
    }
  }


  @Test
  public void relativeNumberOfCoinsTest() {
    getRelativeNumberOfCoinsGetter();
    TutorTests.setupWorld(1);
    RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 100);

    for (int i = 1; i < 50; i++) {
      rob.putCoin();
      rob.putCoin();
      assertEquals(-i - 1, rob.getRelativeNumberOfCoins());
      rob.pickCoin();
      assertEquals(-i, rob.getRelativeNumberOfCoins());
    }
  }

  @Test
  public void setXYTest() {
    fail();
  }

  @Test
  public void setNumberTest() {
    fail();
  }

  @Test
  public void setDirectionTest() {
    fail();
  }


}
