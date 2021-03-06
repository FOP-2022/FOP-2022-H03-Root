package h03;

import fopbot.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class TutorTests {

  static int[][] TEST_VECTORS = {
    {1, 1, 0, 10},
    {2, 5, 3, 100},
    {3, 3, 2, 0},
    {5, 5, 1, 6},
  };

  public static void setupWorld(int size) {
    World.reset();
    World.setSize(size, size);
    World.setDelay(0);
    World.setVisible(false);
  }

  public static void checkAttributeExist(Class<?> c, String name, Class<?> type, boolean finalReq) {
    Field f = null;
    try {
      f = c.getDeclaredField(name);
    } catch (NoSuchFieldException e) {
      fail("Attribut " + name + " in " + c.getName() + " nicht gefunden");
    }
    assertEquals(type, f.getType(), "Typ von " + name + " nicht korrekt.");

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
      fail("Attribut " + name + " in " + c.getName() + " nicht gefunden");
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
      fail("Attribut " + name + " in " + c.getName() + " nicht gefunden");
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
      fail("Methode " + name + " in " + c.getName() + " nicht gefunden");
    }
    assertEquals(returnType, m.getReturnType(), "Rückgabetyp von " + name + " nicht korrekt.");
    assertTrue(Modifier.isPublic(m.getModifiers()), "Methode " + name + " ist nicht public!");
  }

  public static <C> Object callMethod(Class<C> c, String name, Class<?>[] parameterTypes, C obj, Object[] args) {
    Method m = null;
    try {
      m = c.getDeclaredMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      fail("Methode " + name + " in " + c.getName() + " nicht gefunden");
    }

    try {
      return m.invoke(obj, args);
    } catch (IllegalAccessException e) {
      fail("Methode " + name + " in " + c.getName() + " nicht aufrufbar (IllegalAccess)");
    } catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof RuntimeException) {
        throw (RuntimeException) e.getTargetException();
      } else {
        fail("Methode " + name + " in " + c.getName() + " nicht aufrufbar (InvocationTarget)");
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
