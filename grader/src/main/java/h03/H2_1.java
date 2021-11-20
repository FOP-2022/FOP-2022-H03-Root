package h03;

import fopbot.Direction;
import fopbot.Robot;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.*;
import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestForSubmission("h03")
public class H2_1 {


  @Test
  public void isSubType() {
    assertEquals(RobotWithInitialState.class, RobotWithInitialState2.class.getSuperclass());
  }

  @Test
  public void relativeNumberOfCoinsAttributeCorrect() {
    TutorTests.checkAttributeExist(RobotWithInitialState2.class, "relativeNumberOfCoins", Integer.TYPE, false);
  }

  @Test
  public void constructorCallsSuperCorrect() {
    TutorTests.setupWorld(10);

    for(int[] test_element : TutorTests.testvec) {
      RobotWithInitialState rob = new RobotWithInitialState2(test_element[0], test_element[1], Direction.values()[test_element[2]], test_element[3]);
      assertEquals(test_element[0], rob.getX(), "x-Koordinate nicht korrekt an super-Konstruktor übergeben");
      assertEquals(test_element[1], rob.getY(), "y-Koordinate nicht korrekt an super-Konstruktor übergeben");
      assertEquals(test_element[2], rob.getDirection().ordinal(), "Direction nicht korrekt an super-Konstruktor übergeben");
      assertEquals(test_element[3], rob.getNumberOfCoins(), "NumberOfCoins nicht korrekt an super-Konstruktor übergeben");
    }
  }


  @Test //fail, wenn getter nicht auf Attribut zugreift
  public void getRelativeNumberOfCoinsUseMateralised() {
    TutorTests.checkMethodExist(RobotWithInitialState2.class, "getRelativeNumberOfCoins", new Class[]{}, Integer.TYPE);

    final int[] testVec = new int[]{4, 5, 10, -10};

    TutorTests.setupWorld(10);
    RobotWithInitialState2 rob = new RobotWithInitialState2(0,0,Direction.UP, 0);
    for(int testElement : testVec){
      TutorTests.setAttributeValue(RobotWithInitialState2.class, "relativeNumberOfCoins", rob, testElement);
      assertEquals(testElement, (int)TutorTests.callMethod(RobotWithInitialState2.class, "getRelativeNumberOfCoins", new Class[]{}, rob, new Object[]{}));
    }
  }



  @Test
  public void relativeNumberOfCoinsTest() {
    TutorTests.checkMethodExist(RobotWithInitialState2.class, "getRelativeNumberOfCoins", new Class[]{}, Integer.TYPE);

    TutorTests.setupWorld(10);

    for(int[] test_element_initial : TutorTests.testvec){
      for(int[] test_element : TutorTests.testvec) {
        RobotWithInitialState2 rob = new RobotWithInitialState2(test_element_initial[0], test_element_initial[1], Direction.values()[test_element_initial[2]], test_element_initial[3]);
        if(test_element[3] > test_element_initial[3]) { // Müsste münzen aufnehmen, also Testcase hier ungültig
          continue;
        }
        int relativeNumberOfCoins = test_element[3] - test_element_initial[3];
        for(int i = 0; i < -relativeNumberOfCoins; i++) {
          rob.putCoin();
        }

        assertEquals(relativeNumberOfCoins, (Integer) TutorTests.callMethod(RobotWithInitialState2.class, "getRelativeNumberOfCoins", new Class[]{}, rob, new Object[]{}), "getRelativeNumberOfCoins ist nicht korrekt!");
      }
    }
  }

  @Test
  public void overridePutPickTest(){
    //gucken, ob push & put überschrieben
    try {
      Method m = RobotWithInitialState2.class.getMethod("putCoin", new Class[]{});
      assertEquals(RobotWithInitialState2.class, m.getDeclaringClass(), "putCoin nicht in RobotWithInitialState2 überschrieben");
    } catch (NoSuchMethodException e) {
      fail("putCoin in RobotWithInitialState2 nicht gefunden");
    }
    try {
      Method m = RobotWithInitialState2.class.getMethod("pickCoin", new Class[]{});
      assertEquals(RobotWithInitialState2.class, m.getDeclaringClass(), "pickCoin nicht in RobotWithInitialState2 überschrieben");
    } catch (NoSuchMethodException e) {
      fail("pickCoin in RobotWithInitialState2 nicht gefunden");
    }
  }


}
