package h03;

import fopbot.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("H1.2")
@TestForSubmission("h03")

public class H1_2 {
  @Test
  public void attributesCorrect() {
    TutorTests.checkAttributeExist(RobotWithInitialState.class, "initialX", Integer.TYPE, true);
    TutorTests.checkAttributeExist(RobotWithInitialState.class, "initialY", Integer.TYPE, true);
    TutorTests.checkAttributeExist(RobotWithInitialState.class, "initialDirection", Direction.class, true);
    TutorTests.checkAttributeExist(RobotWithInitialState.class, "initialNumberOfCoins", Integer.TYPE, true);
  }

  @Test
  public void attributesCorrectSet() {
    TutorTests.setupWorld(10);

    for (int[] test_element : TutorTests.TEST_VECTORS) {
      RobotWithInitialState rob = new RobotWithInitialState(test_element[0], test_element[1], Direction.values()[test_element[2]], test_element[3]);
      assertEquals(test_element[0], (Integer) TutorTests.getAttributeValue(RobotWithInitialState.class, "initialX", rob), "x-Koordinate nicht korrekt in initial-Attribut gespeichert");
      assertEquals(test_element[1], (Integer) TutorTests.getAttributeValue(RobotWithInitialState.class, "initialY", rob), "y-Koordinate nicht korrekt in initial-Attribut gespeichert");
      assertEquals(Direction.values()[test_element[2]], TutorTests.getAttributeValue(RobotWithInitialState.class, "initialDirection", rob), "Direction nicht korrekt in initial-Attribut gespeichert");
      assertEquals(test_element[3], (Integer) TutorTests.getAttributeValue(RobotWithInitialState.class, "initialNumberOfCoins", rob), "NumberOfCoins nicht korrekt in initial-Attribut gespeichert");
    }
  }

  @Test
  public void getterCorrect() {

    TutorTests.checkMethodExist(RobotWithInitialState.class, "getInitialX", new Class[]{}, Integer.TYPE);
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getInitialY", new Class[]{}, Integer.TYPE);
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getInitialDirection", new Class[]{}, Direction.class);
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getInitialNumberOfCoins", new Class[]{}, Integer.TYPE);

    TutorTests.setupWorld(10);

    for (int[] test_element : TutorTests.TEST_VECTORS) {
      RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 0);

      TutorTests.setAttributeValue(RobotWithInitialState.class, "initialX", rob, test_element[0]);
      TutorTests.setAttributeValue(RobotWithInitialState.class, "initialY", rob, test_element[1]);
      TutorTests.setAttributeValue(RobotWithInitialState.class, "initialDirection", rob, Direction.values()[test_element[2]]);
      TutorTests.setAttributeValue(RobotWithInitialState.class, "initialNumberOfCoins", rob, test_element[3]);

      assertEquals(test_element[0], (Integer) TutorTests.callMethod(RobotWithInitialState.class, "getInitialX", new Class[]{}, rob, new Object[]{}), "getInitialX liefert nicht den Wert von initalX zurück");
      assertEquals(test_element[1], (Integer) TutorTests.callMethod(RobotWithInitialState.class, "getInitialY", new Class[]{}, rob, new Object[]{}), "getInitialY liefert nicht den Wert von initalY zurück");
      assertEquals(Direction.values()[test_element[2]], TutorTests.callMethod(RobotWithInitialState.class, "getInitialDirection", new Class[]{}, rob, new Object[]{}), "getInitialDirection liefert nicht den Wert von initalDirection zurück");
      assertEquals(test_element[3], (Integer) TutorTests.callMethod(RobotWithInitialState.class, "getInitialNumberOfCoins", new Class[]{}, rob, new Object[]{}), "getInitialNumberOfCoins liefert nicht den Wert von initalNumberOfCoin zurück");
    }
  }
}
