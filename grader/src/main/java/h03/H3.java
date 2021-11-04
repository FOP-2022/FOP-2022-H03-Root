package h03;

import fopbot.Direction;
import fopbot.Robot;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestForSubmission("h03")
public class H3 {

  @Test
  public void noSubClassOfRobot() {
    assertNotEquals(Robot.class, TwinRobots.class);
  }


  private static Field field_twin1, field_twin2, field_firstTwinCurrent;

  private static void getTwinAttributes() {
    try {
      field_twin1 = TwinRobots.class.getDeclaredField("twin1");
      field_twin2 = TwinRobots.class.getDeclaredField("twin2");
      field_firstTwinCurrent = TwinRobots.class.getDeclaredField("firstTwinIsCurrent");
    } catch (NoSuchFieldException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  @Test
  public void attributesTest() {
    getTwinAttributes();

    assertEquals(Robot.class, field_twin1.getType());
    assertEquals(true, Modifier.isPrivate(field_twin1.getModifiers()), "twin1 nicht privat");

    assertEquals(Robot.class, field_twin2.getType());
    assertEquals(true, Modifier.isPrivate(field_twin2.getModifiers()), "twin2 nicht privat");


    assertEquals(Boolean.TYPE, field_firstTwinCurrent.getType());
    assertEquals(true, Modifier.isPrivate(field_firstTwinCurrent.getModifiers()), "field_firstTwinCurrent nicht privat");
  }

  @Test
  public void constructorTest() {
    TutorTests.setupWorld(10);
    getTwinAttributes();
    field_twin1.setAccessible(true);
    field_twin2.setAccessible(true);
    field_firstTwinCurrent.setAccessible(true);

    try {
      TwinRobots.class.getDeclaredConstructor(new Class[]{});
    } catch (NoSuchMethodException | SecurityException e) {
      fail("Konstructor nicht gefunden");
      e.printStackTrace();
    }

    TwinRobots twinRob = new TwinRobots();
    Robot rob1 = null, rob2 = null;
    try {
      rob1 = (Robot) field_twin1.get(twinRob);
      rob2 = (Robot) field_twin2.get(twinRob);
      assertEquals(true, (Boolean) field_firstTwinCurrent.get(twinRob));
    } catch (IllegalArgumentException | IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    assertEquals(0, rob1.getX());
    assertEquals(0, rob1.getY());
    assertEquals(Direction.UP, rob1.getDirection());

    assertEquals(1, rob2.getX());
    assertEquals(1, rob2.getY());
    assertEquals(Direction.RIGHT, rob2.getDirection());
  }


  @Test
  public void toggleTest() {
    TutorTests.setupWorld(10);
    getTwinAttributes();
    field_firstTwinCurrent.setAccessible(true);

    TwinRobots rob = new TwinRobots();

    try {
      for (int i = 0; i < 10; i++) {
        assertEquals(true, (boolean) field_firstTwinCurrent.getBoolean(rob));
        rob.toggleCurrentRobot();
        assertEquals(false, (boolean) field_firstTwinCurrent.getBoolean(rob));
        rob.toggleCurrentRobot();
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
      fail();
    }
  }


  @Test
  public void getCurrentRobotTest() {
    TutorTests.setupWorld(10);
    getTwinAttributes();
    field_firstTwinCurrent.setAccessible(true);
    field_twin1.setAccessible(true);
    field_twin2.setAccessible(true);

    TwinRobots rob = new TwinRobots();


    try {
      for (int i = 0; i < 2; i++) {
        if ((boolean) field_firstTwinCurrent.get(rob))
          assertTrue((Robot) field_twin1.get(rob) == rob.getCurrentRobot());
        else
          assertTrue((Robot) field_twin2.get(rob) == rob.getCurrentRobot());
        rob.toggleCurrentRobot();
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
      fail();
    }

  }

}
