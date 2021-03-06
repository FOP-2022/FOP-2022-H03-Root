package h03;

import fopbot.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("H1.3")
@TestForSubmission("h03")
public class H1_3 {

  @Test
  public void getRelativeXYTest() {
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getRelativeX", new Class[]{}, Integer.TYPE);
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getRelativeY", new Class[]{}, Integer.TYPE);

    TutorTests.setupWorld(10);

    for (int[] test_element_initial : TutorTests.TEST_VECTORS) {
      RobotWithInitialState rob = new RobotWithInitialState(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);
      for (int[] test_element : TutorTests.TEST_VECTORS) {
        rob.setX(test_element[0]);
        rob.setY(test_element[1]);

        assertEquals(test_element[0] - test_element_initial[0], (Integer) TutorTests.callMethod(RobotWithInitialState.class, "getRelativeX", new Class[]{}, rob, new Object[]{}), "getRelativeX ist nicht korrekt!");
        assertEquals(test_element[1] - test_element_initial[1], (Integer) TutorTests.callMethod(RobotWithInitialState.class, "getRelativeY", new Class[]{}, rob, new Object[]{}), "getRelativeY ist nicht korrekt!");
      }
    }
  }

  @Test
  public void getRelativeDirectionTest() {
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getRelativeDirection", new Class[]{}, Direction.class);

    TutorTests.setupWorld(10);

    for (int[] test_element_initial : TutorTests.TEST_VECTORS) {
      for (int[] test_element : TutorTests.TEST_VECTORS) {
        RobotWithInitialState rob = new RobotWithInitialState(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);
        int relDirection = (test_element[2] - test_element_initial[2] + 4) % 4;
        for (int i = 0; i < 4 - relDirection; i++) {
          rob.turnLeft();
        }
        assertEquals(Direction.values()[relDirection], TutorTests.callMethod(RobotWithInitialState.class, "getRelativeDirection", new Class[]{}, rob, new Object[]{}), "getRelativeDirection ist nicht korrekt!");
      }
    }
  }

  @Test
  public void getRelativeNumberOfCoinsTest() {
    TutorTests.checkMethodExist(RobotWithInitialState.class, "getRelativeNumberOfCoins", new Class[]{}, Integer.TYPE);

    TutorTests.setupWorld(10);

    for (int[] test_element_initial : TutorTests.TEST_VECTORS) {
      for (int[] test_element : TutorTests.TEST_VECTORS) {
        RobotWithInitialState rob = new RobotWithInitialState(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);
        if (test_element[3] > test_element_initial[3]) { // M??sste m??nzen aufnehmen, also Testcase hier ung??ltig
          continue;
        }
        int relativeNumberOfCoins = test_element[3] - test_element_initial[3];
        for (int i = 0; i < -relativeNumberOfCoins; i++) {
          rob.putCoin();
        }

        assertEquals(relativeNumberOfCoins, (Integer) TutorTests.callMethod(RobotWithInitialState.class, "getRelativeNumberOfCoins", new Class[]{}, rob, new Object[]{}), "getRelativeNumberOfCoins ist nicht korrekt!");
      }
    }
  }

  @Test
  public void setRelativeXYTest() {
    TutorTests.checkMethodExist(RobotWithInitialState.class, "setRelativeX", new Class[]{Integer.TYPE}, Void.TYPE);
    TutorTests.checkMethodExist(RobotWithInitialState.class, "setRelativeY", new Class[]{Integer.TYPE}, Void.TYPE);

    TutorTests.setupWorld(10);

    //allgemein
    for (int[] test_element_initial : TutorTests.TEST_VECTORS) {
      RobotWithInitialState rob = new RobotWithInitialState(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);
      for (int[] test_element : TutorTests.TEST_VECTORS) {

        int relX = test_element[0] - test_element_initial[0];
        int relY = test_element[1] - test_element_initial[1];

        TutorTests.callMethod(RobotWithInitialState.class, "setRelativeX", new Class[]{Integer.TYPE}, rob, new Object[]{relX});
        TutorTests.callMethod(RobotWithInitialState.class, "setRelativeY", new Class[]{Integer.TYPE}, rob, new Object[]{relY});

        assertEquals(test_element[0], rob.getX(), "setRelativeX ist nicht korrekt!");
        assertEquals(test_element[1], rob.getY(), "setRelativeY ist nicht korrekt!");
      }
    }

    {
      for(int r : new int[]{-1, 10}){
        //crash bei setrelativeX
        RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 0);
        try {
          TutorTests.callMethod(RobotWithInitialState.class, "setRelativeX", new Class[]{Integer.TYPE}, rob, new Object[]{r});
        } catch (RuntimeException e) {
          assertTrue(TutorTests.checkStackTrace(e, "crash"), "crash() nicht aufgerufen");
          assertFalse(TutorTests.checkStackTrace(e, "move"), "move trotz crash aufgerufen");
          assertFalse(TutorTests.checkStackTrace(e, "setX"), "setX trotz crash aufgerufen");
        }
      }
    }
    {
      for(int r : new int[]{-1, 10}) {
        //crash bei setrelativey
        RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 0);
        try {
          TutorTests.callMethod(RobotWithInitialState.class, "setRelativeY", new Class[]{Integer.TYPE}, rob, new Object[]{r});
        } catch (RuntimeException e) {
          assertTrue(TutorTests.checkStackTrace(e, "crash"), "crash() nicht aufgerufen");
          assertFalse(TutorTests.checkStackTrace(e, "move"), "move trotz crash aufgerufen");
          assertFalse(TutorTests.checkStackTrace(e, "setY"), "setY trotz crash aufgerufen");
        }
      }
    }
  }

  @Test
  public void setRelativeNumberOfCoinsTest() {
    TutorTests.checkMethodExist(RobotWithInitialState.class, "setRelativeNumberOfCoins", new Class[]{Integer.TYPE}, Void.TYPE);

    TutorTests.setupWorld(10);

    //allgemeine Testf??lle
    for (int[] test_element_initial : TutorTests.TEST_VECTORS) {
      for (int[] test_element : TutorTests.TEST_VECTORS) {
        RobotWithInitialState rob = new RobotWithInitialState(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);
        //eigentlich auch wiederverwendung von rob

        int relNumberOfCoins = test_element[3] - test_element_initial[3];

        if (relNumberOfCoins > 0) {
          continue;
        }

        TutorTests.callMethod(RobotWithInitialState.class, "setRelativeNumberOfCoins", new Class[]{Integer.TYPE}, rob, new Object[]{relNumberOfCoins});
        assertEquals(test_element[3], rob.getNumberOfCoins(), "setRelativeNumberOfCoins ist nicht korrekt!");
      }
    }

    {
      //Testcase Two Stages -> Realtive abh??ngig von Initial
      RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 10);
      TutorTests.callMethod(RobotWithInitialState.class, "setRelativeNumberOfCoins", new Class[]{Integer.TYPE}, rob, new Object[]{-1});
      TutorTests.callMethod(RobotWithInitialState.class, "setRelativeNumberOfCoins", new Class[]{Integer.TYPE}, rob, new Object[]{-3});
      assertEquals(7, rob.getNumberOfCoins(), "setRelativeNumberOfCoins nicht ausgehend von initialNumberOfCoins");
    }

    {
      //crash
      RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 10);
      try {
        TutorTests.callMethod(RobotWithInitialState.class, "setRelativeNumberOfCoins", new Class[]{Integer.TYPE}, rob, new Object[]{1});
      } catch (RuntimeException e) {
        assertTrue(TutorTests.checkStackTrace(e, "crash"), "crash() nicht aufgerufen");
        assertFalse(TutorTests.checkStackTrace(e, "putCoin"), "putCoin trotz crash aufgerufen");
        assertFalse(TutorTests.checkStackTrace(e, "pickCoin"), "pickCoin trotz crash aufgerufen");
      }
    }

    {
      //crash bei pick-versuch
      RobotWithInitialState rob = new RobotWithInitialState(0, 0, Direction.UP, 10);
      rob.putCoin();
      rob.putCoin();
      try {
        TutorTests.callMethod(RobotWithInitialState.class, "setRelativeNumberOfCoins", new Class[]{Integer.TYPE}, rob, new Object[]{-1});
      } catch (RuntimeException e) {
        assertTrue(TutorTests.checkStackTrace(e, "crash"), "crash() nicht aufgerufen");
        assertFalse(TutorTests.checkStackTrace(e, "putCoin"), "putCoin trotz crash aufgerufen");
        assertFalse(TutorTests.checkStackTrace(e, "pickCoin"), "pickCoin trotz crash aufgerufen");
      }
    }
  }

  @Test
  public void setRelativeDirectionTest() {

    TutorTests.checkMethodExist(RobotWithInitialState.class, "setRelativeDirection", new Class[]{Direction.class}, Void.TYPE);
    TutorTests.setupWorld(10);

    for (int[] test_element_initial : TutorTests.TEST_VECTORS) {
      for (int[] test_element : TutorTests.TEST_VECTORS) {
        RobotWithInitialState rob = new RobotWithInitialState(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);

        int relDirection = (test_element[2] - test_element_initial[2] + 4) % 4;

        TutorTests.callMethod(RobotWithInitialState.class, "setRelativeDirection", new Class[]{Direction.class}, rob, new Object[]{Direction.values()[relDirection]});
        assertEquals(Direction.values()[test_element[2]], rob.getDirection(), "setRelativeDirection ist nicht korrekt!");
      }
    }
  }
}
