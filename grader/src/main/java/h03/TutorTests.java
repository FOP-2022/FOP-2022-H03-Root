package h03;
import fopbot.*;



import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.function.Function;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h03")
public class TutorTests {


  public static int setupWorld(int size) {
    World.reset();
    World.setSize(size, size);
    World.setDelay(0);
    World.setVisible(false);
    return size;
  }


}
