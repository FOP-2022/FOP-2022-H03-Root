package h03;

import fopbot.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h03")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class H1_1 {

  @Test
  public void robotIsSuperType() {
    assertEquals(Robot.class, RobotWithInitialState.class.getSuperclass());
  }

  @Test
  public void constructorCallsSuperCorrect() { //TODO: schauen, ob richtiger Konstruktor aufgerufen wird.... -> Bytecode ansehen
    TutorTests.setupWorld(10);

    for (int[] test_element : TutorTests.TEST_VECTORS) {
      Robot rob = new RobotWithInitialState(test_element[0], test_element[1], Direction.values()[test_element[2]], test_element[3]);
      assertEquals(test_element[0], rob.getX(), "x-Koordinate nicht korrekt an super-Konstruktor übergeben");
      assertEquals(test_element[1], rob.getY(), "y-Koordinate nicht korrekt an super-Konstruktor übergeben");
      assertEquals(test_element[2], rob.getDirection().ordinal(), "Direction nicht korrekt an super-Konstruktor übergeben");
      assertEquals(test_element[3], rob.getNumberOfCoins(), "NumberOfCoins nicht korrekt an super-Konstruktor übergeben");
    }
  }
}
