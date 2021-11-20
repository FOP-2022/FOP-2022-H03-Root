package h03;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

@RubricForSubmission("h03")
public class H03_RubricProvider implements RubricProvider {

  ///////////////////////////////////////////////// H1.1

  public static final Criterion H1_1_T1 = Criterion.builder()
    .shortDescription("RobotWithInitialState wurde erkennbar korrekt von Robot abgeleitet")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("robotIsSuperType")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_1_T2 = Criterion.builder()
    .shortDescription("RobotWithInitialState ruft den Konstruktor von Robot mit den richtigen Parameterwerten auf")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("constructorCallsSuperCorrect")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_1 = Criterion.builder()
    .shortDescription("H1.1 Abgeleitete Klasse, Konstruktor und die Attribute der Basisklasse")
    .addChildCriteria(
      H1_1_T1,
      H1_1_T2
    )
    .build();

  ///////////////////////////////////////////////// H1.2

  public static final Criterion H1_2_T1 = Criterion.builder()
    .shortDescription("Alle zusätzlichen Attribute (initial*) von RobotWithInitialState sind privat & final und besitzen den richtigen Typ")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("attributesCorrect")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_2_T2 = Criterion.builder()
    .shortDescription("Alle zusätzlichen Attribute (initial*) von RobotWithInitialState werden auf die korrekten Werte gesetzt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("attributesCorrectSet")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_2_T3 = Criterion.builder()
    .shortDescription("Die Getter (getInitial*) aller zusätzlichen Attribute (initial*) von RobotWithInitialState sind erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("getterCorrect")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_2 = Criterion.builder()
    .shortDescription("H1.2 Zusätzliche Attribute in der abgeleiteten Klasse und ihre get-Methoden")
    .addChildCriteria(
      H1_2_T1,
      H1_2_T2,
      H1_2_T3
    )
    .build();

  ///////////////////////////////////////////////// H1.3

  public static final Criterion H1_3_T1 = Criterion.builder()
    .shortDescription("getRelativeX, getRelativeY & getRelativeNumberOfCoins funktionieren erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_3.class.getMethod("getRelativeXYTest")))
      .requirePass(JUnitTestRef.ofMethod(() -> H1_3.class.getMethod("getRelativeNumberOfCoinsTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_3_T2 = Criterion.builder()
    .shortDescription("getRelativeDirection funktioniert erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_3.class.getMethod("getRelativeDirectionTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_3_T3 = Criterion.builder()
    .shortDescription("setRelativeX & setRelativeY funktionieren erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_3.class.getMethod("setRelativeXYTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_3_T4 = Criterion.builder()
    .shortDescription("setRelativeNumberOfCoins funktioniert erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_3.class.getMethod("setRelativeNumberOfCoinsTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_3_T5 = Criterion.builder()
    .shortDescription("setRelativeDirection funktioniert erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H1_3.class.getMethod("setRelativeDirectionTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H1_3 = Criterion.builder()
    .shortDescription("H1.3 Nichtmaterialisierte Attribute")
    .addChildCriteria(
      H1_3_T1,
      H1_3_T2,
      H1_3_T3,
      H1_3_T4,
      H1_3_T5
    )
    .build();

  ///////////////////////////////////////////////// H2

  public static final Criterion H2_1_T1 = Criterion.builder()
    .shortDescription("RobotWithInitialState2 ist erkennbar korrekt von RobotWithInitialState abgeleitet")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("isSubType")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H2_1_T2 = Criterion.builder()
    .shortDescription("RobotWithInitialState2 besitzt das Attribut relativeNumberOfCoins, welches private, aber nicht final ist")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("relativeNumberOfCoinsAttributeCorrect")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build(); //TODO relativeNumberOfCoinsTest? anfügen??

  public static final Criterion H2_1_T3 = Criterion.builder()
    .shortDescription("RobotWithInitialState2 ruft den Konstruktor von RobotWithInitialState erkennbar korrekt auf")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("constructorCallsSuperCorrect")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H2_1_T4 = Criterion.builder()
    .shortDescription("relativeNumberOfCoins ist tatsächlich materialisiert")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("getRelativeNumberOfCoinsUseMaterialized")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H2_1_T5 = Criterion.builder()
    .shortDescription("relativeNumberOfCoins ist erkennbar korrekt berechnet")
    .maxPoints(0)
    .minPoints(-1)
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("relativeNumberOfCoinsTest")))
      .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("overridePutPickTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H2_1 = Criterion.builder()
    .shortDescription("H2.1 Implementierung")
    .addChildCriteria(
      H2_1_T1,
      H2_1_T2,
      H2_1_T3,
      H2_1_T4,
      H2_1_T5
    )
    .build();

  public static final Criterion H2_2 = Criterion.builder()
    .shortDescription("H2.2 Testen")
    .minPoints(0)
    .maxPoints(2)
    .build();

  ///////////////////////////////////////////////// H3
  public static final Criterion H3_T1 = Criterion.builder()
    .shortDescription("TwinRobots ist nicht von Robot abgeleitet und der Konstruktor von TwinRobots ist erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("constructorTest")))
      .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("noSubClassOfRobot")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H3_T2 = Criterion.builder()
    .shortDescription("Die Attribute von TwinRobots sind erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("attributesTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();


  public static final Criterion H3_T3 = Criterion.builder()
    .shortDescription("Die Methode toggle ist erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("toggleTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H3_T4 = Criterion.builder()
    .shortDescription("Die Methode getCurrentRobot ist erkennbar korrekt")
    .grader(Grader.testAwareBuilder()
      .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("getCurrentRobotTest")))
      .pointsPassedMax()
      .pointsFailedMin()
      .build()
    ).build();

  public static final Criterion H3 = Criterion.builder()
    .shortDescription("H3 Roboter mit überschriebener Methode")
    .addChildCriteria(
      H3_T1,
      H3_T2,
      H3_T3,
      H3_T4
    )
    .build();

  //SO?
  public static final Criterion H1 = Criterion.builder()
    .shortDescription("H1 Roboter mit Erinnerung an den Startzustand")
    .addChildCriteria(
      H1_1,
      H1_2,
      H1_3
    )
    .build();

  public static final Criterion H2 = Criterion.builder()
    .shortDescription("H2 Roboter mit überschriebener Methode")
    .addChildCriteria(
      H2_1,
      H2_2
    )
    .build();

  public static final Criterion VARIABLE_WORLD_SIZE = Criterion.builder()
    .shortDescription("Die Abgabe funktioniert erkennbar korrekt für verschiedene Weltgrößen")
    .maxPoints(0)
    .minPoints(-2)
    .build();

  public static final Criterion JAVADOC = Criterion.builder()
    .shortDescription("Alle selbstgeschriebenen Methoden wurden korrekt mit JavaDoc dokumentiert")
    .maxPoints(0)
    .minPoints(-3)
    .build();

  public static final Rubric RUBRIC = Rubric.builder()
    .title("h03")
    .addChildCriteria(H1, H2, H3, VARIABLE_WORLD_SIZE, JAVADOC)
    .build();

  @Override
  public Rubric getRubric() {
    return RUBRIC;
  }
}
