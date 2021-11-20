package h03;

import fopbot.*;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h03")
public class H3 {

  @Test
  public void noSubClassOfRobot() {
    assertNotEquals(Robot.class, TwinRobots.class.getSuperclass(), "TwinRobots wurde von Robot abgeleitet");
  }

  @Test
  public void attributesTest() {
    TutorTests.checkAttributeExist(TwinRobots.class, "twin1", Robot.class, false);
    TutorTests.checkAttributeExist(TwinRobots.class, "twin2", Robot.class, false);
    TutorTests.checkAttributeExist(TwinRobots.class, "firstTwinIsCurrent", Boolean.TYPE, false);
  }

  @Test
  public void constructorTest() {
    TutorTests.setupWorld(10);

    TwinRobots twinRobot = new TwinRobots();
    Robot rob1 = (Robot) TutorTests.getAttributeValue(TwinRobots.class, "twin1", twinRobot);
    Robot rob2 = (Robot) TutorTests.getAttributeValue(TwinRobots.class, "twin2", twinRobot);

    assertEquals(0, rob1.getX(), "twin1 nicht an x=0");
    assertEquals(0, rob1.getY(), "twin1 nicht an y=0");
    assertEquals(Direction.UP, rob1.getDirection(), "twin1 guckt nicht nach oben");

    assertEquals(1, rob2.getX(), "twin2 nicht an x=1");
    assertEquals(1, rob2.getY(), "twin2 nicht an y=1");
    assertEquals(Direction.RIGHT, rob2.getDirection(), "twin2 guckt nicht nach rechts");

    assertEquals(true, TutorTests.getAttributeValue(TwinRobots.class, "firstTwinIsCurrent", twinRobot), "firstTwinIsCurrent ist nicht initial auf true gesetzt");
  }

  @Test
  public void toggleTest() {
    TutorTests.setupWorld(10);
    TwinRobots twinRobot = new TwinRobots();

    TutorTests.setAttributeValue(TwinRobots.class, "firstTwinIsCurrent", twinRobot, true);
    for (int i = 0; i < 10; i++) {
      assertEquals(true, TutorTests.getAttributeValue(TwinRobots.class, "firstTwinIsCurrent", twinRobot), "toggleCurrentRobot funktioniert nicht korrekt");
      twinRobot.toggleCurrentRobot();
      assertEquals(false, TutorTests.getAttributeValue(TwinRobots.class, "firstTwinIsCurrent", twinRobot), "toggleCurrentRobot funktioniert nicht korrekt");
      twinRobot.toggleCurrentRobot();
    }
  }

  @Test
  public void getCurrentRobotTest() {
    TutorTests.setupWorld(10);
    TwinRobots twinRobot = new TwinRobots();

    Robot rob1 = (Robot) TutorTests.getAttributeValue(TwinRobots.class, "twin1", twinRobot);
    Robot rob2 = (Robot) TutorTests.getAttributeValue(TwinRobots.class, "twin2", twinRobot);

    TutorTests.setAttributeValue(TwinRobots.class, "firstTwinIsCurrent", twinRobot, true);
    assertEquals(rob1, twinRobot.getCurrentRobot(), "getCurrentRobot liefert nicht twin1 bei firstTwinIsCurrent=true zurück");
    TutorTests.setAttributeValue(TwinRobots.class, "firstTwinIsCurrent", twinRobot, false);
    assertEquals(rob2, twinRobot.getCurrentRobot(), "getCurrentRobot liefert nicht twin2 bei firstTwinIsCurrent=false zurück");
  }
}
