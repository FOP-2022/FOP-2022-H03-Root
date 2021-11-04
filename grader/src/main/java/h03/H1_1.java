package h03;

import fopbot.Robot;
import org.junit.jupiter.api.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestForSubmission("h03")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("H1.1")
public class H1_1 {

  @Test
  @Order(1)
  public void robotIsSuperType() {
    assertEquals(Robot.class, RobotWithInitialState.class.getSuperclass());
  }

  @Test
  @Order(2)
  public void constructorCallsSuperCorrect() {
    H1.getConstructor();
    TutorTests.setupWorld(10);
    H1.run(rob -> rob.getX(), rob -> rob.getY(), rob -> rob.getDirection(), rob -> rob.getNumberOfCoins());
  }
}
