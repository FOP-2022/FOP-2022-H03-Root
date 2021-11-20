package h03;

import fopbot.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h03")
public class TutorTests {

  static int[][] testvec = {
    {1, 1, 0, 10},
    {2, 5, 3, 100},
    {3, 3, 2, 0}
  }; //TODO: Add

  public static int setupWorld(int size) {
    World.reset();
    World.setSize(size, size);
    World.setDelay(0);
    World.setVisible(false);

    /*
    //set in MAIN -> //TODO: Wollen wir auch andere Größen für die Tests?
    try {
      Field field_size_x = Main.class.getDeclaredField("WORLD_SIZE_X");
      field_size_x.setAccessible(true);

      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);
      modifiers.setInt(field_size_x, field_size_x.getModifiers() & ~Modifier.FINAL);

      field_size_x.setInt(null, size);

      Field field_size_y = Main.class.getDeclaredField("WORLD_SIZE_Y");
      field_size_y.setAccessible(true);

      modifiers.setInt(field_size_y, field_size_y.getModifiers() & ~Modifier.FINAL);

      field_size_y.setInt(null, size);

    } catch (NoSuchFieldException | IllegalAccessException e) {
      //throw new RuntimeException("WORLD_SIZE_* konnte nicht gesetzt werden!!!");
      e.printStackTrace();
    }*/

    return size;
  }

  public static void checkAttributeExist(Class<?> c, String name, Class<?> type, boolean finalReq) {
    Field f = null;
    try {
      f = c.getDeclaredField(name);
    } catch (NoSuchFieldException e) {
      fail("Attribut " + name + " in " + c.getName() + "nicht gefunden");
    }
    assertEquals(type, f.getType(), "Typ von " + name + "nicht korrekt.");

    assertTrue(Modifier.isPrivate(f.getModifiers()), "Attribut " + name + " ist nicht private!");

    if (finalReq) {
      assertTrue(Modifier.isFinal(f.getModifiers()), "Attribut " + name + " ist nicht final!");
    }
  }

  public static <C> Object getAttributeValue(Class<C> c, String name, C obj) {
    Field f = null;
    try {
      f = c.getDeclaredField(name);
    } catch (NoSuchFieldException e) {
      fail("Attribut " + name + " in " + c.getName() + "nicht gefunden");
    }
    f.setAccessible(true);

    try {
      return f.get(obj);
    } catch (IllegalAccessException e) {
      fail("Zugriff auf " + name + " nicht möglich!");
    }
    return null;
  }

  public static <C> void setAttributeValue(Class<C> c, String name, C obj, Object val) {
    Field f = null;
    try {
      f = c.getDeclaredField(name);
    } catch (NoSuchFieldException e) {
      fail("Attribut " + name + " in " + c.getName() + "nicht gefunden");
    }
    f.setAccessible(true);

    try {
      f.set(obj, val);
    } catch (IllegalAccessException e) {
      fail("Zugriff auf " + name + " nicht möglich!");
    }
  }

  public static void checkMethodExist(Class<?> c, String name, Class<?>[] parameterTypes, Class<?> returnType) {
    Method m = null;
    try {
      m = c.getDeclaredMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      fail("Methode " + name + " in " + c.getName() + "nicht gefunden");
    }
    assertSame(returnType, m.getReturnType(), "Rückgabetyp von " + name + "nicht korrekt.");
    assertTrue(Modifier.isPublic(m.getModifiers()), "Methode " + name + " ist nicht public!");
  }

  public static <C> Object callMethod(Class<C> c, String name, Class<?>[] parameterTypes, C obj, Object[] args) {
    Method m = null;
    try {
      m = c.getDeclaredMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      fail("Methode " + name + " in " + c.getName() + "nicht gefunden");
    }

    try {
      return m.invoke(obj, args);
    } catch (IllegalAccessException e) {
      fail("Methode " + name + " in " + c.getName() + "nicht aufrufbar (IllegalAccess)");
    } catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof RuntimeException) {
        throw (RuntimeException) e.getTargetException();
      } else {
        fail("Methode " + name + " in " + c.getName() + "nicht aufrufbar (InvocationTarget)");
      }
    }
    return null;
  }

  public static boolean checkStackTrace(RuntimeException e, String name) {
    for (StackTraceElement element : e.getStackTrace()) {
      if (element.getMethodName().equals(name)) {
        return true;
      }
    }
    return false;
  }
}
