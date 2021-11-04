package h03;

import fopbot.Direction;
import org.junit.jupiter.api.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("H1.2")
@TestForSubmission("h03")
public class H1_2 {
  @Test
  public void attributesCorrect() {
    H1.getFields();

    Assertions.assertEquals(Integer.TYPE, H1.field_initialX.getType());
    Assertions.assertEquals(Integer.TYPE, H1.field_initialY.getType());
    Assertions.assertEquals(Direction.class, H1.field_initialDirection.getType());
    Assertions.assertEquals(Integer.TYPE, H1.field_initialNumberOfCoins.getType());


    assertTrue(Modifier.isPrivate(H1.field_initialX.getModifiers()));
    assertTrue(Modifier.isPrivate(H1.field_initialY.getModifiers()));
    assertTrue(Modifier.isPrivate(H1.field_initialDirection.getModifiers()));
    assertTrue(Modifier.isPrivate(H1.field_initialNumberOfCoins.getModifiers()));
  }


  @Test
  public void attributesCorrectSet() {
    H1.getFields();
    H1.getConstructor();

    H1.field_initialX.setAccessible(true);
    H1.field_initialY.setAccessible(true);
    H1.field_initialDirection.setAccessible(true);
    H1.field_initialNumberOfCoins.setAccessible(true);

    TutorTests.setupWorld(10);

//				run(rob -> (Integer)field_initialX.get(rob),
//						rob -> (Integer)field_initialY.get(rob),
//						rob -> (Direction)field_initialDirection.get(rob),
//						rob -> (Integer)field_initialNumberOfCoins.get(rob));

    int x, y, numberOfCoins;
    Direction direction;
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      x = random.nextInt(10);
      y = random.nextInt(10);
      direction = Direction.values()[random.nextInt(4)];
      numberOfCoins = random.nextInt(100);
      RobotWithInitialState rob = null;
      try {
        rob = H1.constructor.newInstance(x, y, direction, numberOfCoins);

      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
        fail("Konstruktoraufruf schlägt fehl!");
        e.printStackTrace();
      }


      try {
        assertEquals(x, ((Integer) H1.field_initialX.get(rob)));
        assertEquals(y, ((Integer) H1.field_initialY.get(rob)));
        assertEquals(direction, ((Direction) H1.field_initialDirection.get(rob)));
        assertEquals(numberOfCoins, ((Integer) H1.field_initialNumberOfCoins.get(rob)));
      } catch (IllegalArgumentException | IllegalAccessException e) {
        // TODO Auto-generated catch block
        fail("Zugriff nicht möglich");
        e.printStackTrace();
      }
    }
  }


  @Test
  public void getterCorrect() {
    H1.getGetter();
    H1.getConstructor();

    //getter Typen
    Assertions.assertEquals(Integer.TYPE, H1.method_getInitialX.getReturnType());
    Assertions.assertEquals(Integer.TYPE, H1.method_getInitialY.getReturnType());
    Assertions.assertEquals(Direction.class, H1.method_getInitialDirection.getReturnType());
    Assertions.assertEquals(Integer.TYPE, H1.method_getInitialNumberOfCoins.getReturnType());

    //public
    assertTrue(Modifier.isPublic(H1.method_getInitialX.getModifiers()));
    assertTrue(Modifier.isPublic(H1.method_getInitialY.getModifiers()));
    assertTrue(Modifier.isPublic(H1.method_getInitialDirection.getModifiers()));
    assertTrue(Modifier.isPublic(H1.method_getInitialNumberOfCoins.getModifiers()));


    TutorTests.setupWorld(10);


    int x, y, numberOfCoins;
    Direction direction;
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      x = random.nextInt(10);
      y = random.nextInt(10);
      direction = Direction.values()[random.nextInt(4)];
      numberOfCoins = random.nextInt(100);
      RobotWithInitialState rob = null;
      try {
        rob = H1.constructor.newInstance(x, y, direction, numberOfCoins);

      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
        fail("Konstruktoraufruf schlägt fehl!");
        e.printStackTrace();
      }


      try {
        assertEquals(x, (Integer) H1.method_getInitialX.invoke(rob, new Object[]{}));
        assertEquals(y, (Integer) H1.method_getInitialY.invoke(rob, new Object[]{}));
        assertEquals(direction, (Direction) H1.method_getInitialDirection.invoke(rob, new Object[]{}));
        assertEquals(numberOfCoins, (Integer) H1.method_getInitialNumberOfCoins.invoke(rob, new Object[]{}));
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        fail("Gette-Call nicht möglich");
        e.printStackTrace();
      }
    }
  }
}
