package h03;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import org.junit.jupiter.api.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.*;
import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h03")
public class H1 {

  static Constructor<RobotWithInitialState> constructor;
  static Field field_initialX;
  static Field field_initialY;
  static Field field_initialDirection;
  static Field field_initialNumberOfCoins;
  static Method method_getInitialX, method_getInitialY, method_getInitialDirection, method_getInitialNumberOfCoins;

  public static void getConstructor() {
    try {
      constructor = RobotWithInitialState.class.getConstructor(Integer.TYPE, Integer.TYPE, Direction.class, Integer.TYPE);
    } catch (NoSuchMethodException e) {
      fail("Konstruktor nicht angegeben!");
      e.printStackTrace();
    } catch (SecurityException e) {
      fail();//TODO
      e.printStackTrace();
    }
  }

  public static void getFields() {
    try {
      field_initialX = RobotWithInitialState.class.getDeclaredField("initialX");
      field_initialY = RobotWithInitialState.class.getDeclaredField("initialY");
      field_initialDirection = RobotWithInitialState.class.getDeclaredField("initialDirection");
      field_initialNumberOfCoins = RobotWithInitialState.class.getDeclaredField("initialNumberOfCoins");
    } catch (NoSuchFieldException | SecurityException e) {
      fail("Attribut(e) nicht gefunden"); // TODO -> Wird danach aufgehört?
    }
  }

  public static void getGetter() {
    try {
      method_getInitialX = RobotWithInitialState.class.getDeclaredMethod("getInitialX", new Class[]{});
      method_getInitialY = RobotWithInitialState.class.getDeclaredMethod("getInitialY", new Class[]{});
      method_getInitialDirection = RobotWithInitialState.class.getDeclaredMethod("getInitialDirection", new Class[]{});
      method_getInitialNumberOfCoins = RobotWithInitialState.class.getDeclaredMethod("getInitialNumberOfCoins", new Class[]{});
    } catch (NoSuchMethodException | SecurityException e) {
      fail("Getter nicht gefunden");
      e.printStackTrace();
    }
  }

  public static void run(Function<Robot, Integer> func_x, Function<Robot, Integer> func_y, Function<Robot, Direction> func_direction, Function<Robot, Integer> func_numberOfCoins) {
    int x, y, numberOfCoins;
    Direction direction;
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      x = random.nextInt(10);
      y = random.nextInt(10);
      direction = Direction.values()[random.nextInt(4)];
      numberOfCoins = random.nextInt(100);

      try {
        Robot rob = constructor.newInstance(x, y, direction, numberOfCoins);
        assertEquals(x, func_x.apply(rob));
        assertEquals(y, func_y.apply(rob));
        assertEquals(direction, func_direction.apply(rob));
        assertEquals(numberOfCoins, func_numberOfCoins.apply(rob));

      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
        fail("Konstruktoraufruf schlägt fehl!");
        e.printStackTrace();
      }
    }
  }

}
