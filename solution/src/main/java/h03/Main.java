package h03;

import fopbot.*;

public class Main {

  public final static int WORLD_SIZE_X = 10;
  public final static int WORLD_SIZE_Y = 10;

  /**
   * Main Method. Creates World and call robotTest()
   */
  public static void main(String[] args) {
    World.setSize(WORLD_SIZE_X, WORLD_SIZE_Y);
    World.setDelay(200);
    World.setVisible(true);
    robotTests();
  }

  /**
   * Calls putCoin() of Robot-Object numberOfCoins times
   *
   * @param robot         Robot-Object for which putCoin() get's called
   * @param numberOfCoins times putCoin() get's called
   */
  public static void putNumberOfCoins(Robot robot, int numberOfCoins) {
    for (int i = 0; i < numberOfCoins; i++) {
      robot.putCoin();
    }
  }

  /**
   * robotTests(), where Robots can be tested
   */
  public static void robotTests() {
    //TODO Testing
  }
}
