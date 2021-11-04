package h03;

import fopbot.*;

public class TwinRobots {

  private final Robot twin1;
  private final Robot twin2;
  private boolean firstTwinIsCurrent;

  /**
   * Constructor of TwinRobots. Set twin1, twin2 and firstTwinIsCurrent
   */
  public TwinRobots() {
    twin1 = new Robot(0, 0, Direction.UP, 0);
    twin2 = new Robot(1, 1, Direction.RIGHT, 0);
    firstTwinIsCurrent = true;
  }

  /**
   * Toggles firstTwinIsCurrent
   */
  public void toggleCurrentRobot() {
    firstTwinIsCurrent = !firstTwinIsCurrent;
  }

  /**
   * @return Twin1, if firstTwinIsCurrent is true; otherwise twin2
   */
  public Robot getCurrentRobot() {
    return firstTwinIsCurrent ? twin1 : twin2;
  }
}
