package h03;

import fopbot.Direction;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.*;
import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestForSubmission("h03")
public class H2 {


  private static Constructor<RobotWithInitialState2> constructor;

  private static void getConstructor() {
    try {
      constructor = RobotWithInitialState2.class.getConstructor(Integer.TYPE, Integer.TYPE, Direction.class, Integer.TYPE);
    } catch (NoSuchMethodException e) {
      fail("Konstruktor nicht angegeben!");
      e.printStackTrace();
    } catch (SecurityException e) {
      fail();//TODO
      e.printStackTrace();
    }
  }

  private static void run(Function<RobotWithInitialState, Integer> func_x, Function<RobotWithInitialState, Integer> func_y, Function<RobotWithInitialState, Direction> func_direction, Function<RobotWithInitialState, Integer> func_numberOfCoins) {
    int x, y, numberOfCoins;
    Direction direction;
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      x = random.nextInt(10);
      y = random.nextInt(10);
      direction = Direction.values()[random.nextInt(4)];
      numberOfCoins = random.nextInt(100);

      try {
        RobotWithInitialState rob = constructor.newInstance(x, y, direction, numberOfCoins);
        assertEquals(x, func_x.apply(rob));
        assertEquals(y, func_y.apply(rob));
        assertEquals(direction, func_direction.apply(rob));
        assertEquals(numberOfCoins, func_numberOfCoins.apply(rob));

        assertEquals(0, ((RobotWithInitialState2) rob).getRelativeNumberOfCoins()); //numberOfCoins initial passend

      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
        fail("Konstruktoraufruf schlägt fehl!");
        e.printStackTrace();
      }
    }
  }


  private static Field field_relativeNumberOfCoins;

  private static void getRelativeNumberOfCoinsField() {
    try {
      field_relativeNumberOfCoins = RobotWithInitialState2.class.getDeclaredField("relativeNumberOfCoins");
    } catch (NoSuchFieldException | SecurityException e) {
      fail("relativeNumberOfCoins nicht gefunden");
      e.printStackTrace();
    }
  }

  private static Method method_getRelativeNumberOfCoins;

  private static void getRelativeNumberOfCoinsMethod() {
    try {
      method_getRelativeNumberOfCoins = RobotWithInitialState2.class.getDeclaredMethod("getRelativeNumberOfCoins", new Class[]{});
    } catch (NoSuchMethodException | SecurityException e) {
      fail("getRelativeNumberOfCoins() nicht gefunden");
      e.printStackTrace();
    }
  }


  @Test
  public void isSubType() {
    assertEquals(RobotWithInitialState.class, RobotWithInitialState2.class.getSuperclass());
  }

  @Test
  public void relativeNumberOfCoinsAttributeCorrect() {
    getRelativeNumberOfCoinsField();
    assertEquals(Integer.TYPE, field_relativeNumberOfCoins.getType());
    assertEquals(true, Modifier.isPrivate(field_relativeNumberOfCoins.getModifiers()), "relativeNumberOfCoins nicht privat");
  }

  @Test
  public void constructorCallsSuperCorrect() {
    getConstructor();
    TutorTests.setupWorld(10);
    run(rob -> rob.getX(), rob -> rob.getY(), rob -> rob.getDirection(), rob -> rob.getNumberOfCoins());
  }


  @Test //fail, wenn getter nicht auf Attribut zugreift
  public void getRelativeNumberOfCoinsUseMateralised() {
    getRelativeNumberOfCoinsField();
    getRelativeNumberOfCoinsMethod();

    TutorTests.setupWorld(10);
    RobotWithInitialState2 rob = new RobotWithInitialState2(0, 0, Direction.RIGHT, 10);

    field_relativeNumberOfCoins.setAccessible(true);

    for (int i = 0; i < 50; i = i + 3) {
      try {
        field_relativeNumberOfCoins.set(rob, i);
      } catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
      assertEquals(i, rob.getRelativeNumberOfCoins());
    }
  }

  @Test
  public void relativeNumberOfCoinsTest() {
    getRelativeNumberOfCoinsMethod();
    TutorTests.setupWorld(1);
    RobotWithInitialState2 rob = new RobotWithInitialState2(0, 0, Direction.UP, 100);

    for (int i = 1; i < 50; i++) {
      rob.putCoin();
      rob.putCoin();
      assertEquals(-i - 1, rob.getRelativeNumberOfCoins());
      rob.pickCoin();
      assertEquals(-i, rob.getRelativeNumberOfCoins());
    }
  }


}
