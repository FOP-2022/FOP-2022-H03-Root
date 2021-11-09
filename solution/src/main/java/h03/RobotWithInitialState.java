package h03;

import fopbot.*;

public class RobotWithInitialState extends Robot {

  private final int initialX;
  private final int initialY;
  private final Direction initialDirection;
  private final int initialNumberOfCoins;

  //H1.1

  /**
   * Constructor of RobotWithMemorizedStart
   *
   * @param x             Initial x-Coordinate
   * @param y             Initial y-Coordinate
   * @param direction     Initial direction
   * @param numberOfCoins Initial numberOfCoins
   */
  public RobotWithInitialState(int x, int y, Direction direction, int numberOfCoins) {
    super(x, y, direction, numberOfCoins);
    initialX = x;
    initialY = y;
    initialDirection = direction;
    initialNumberOfCoins = numberOfCoins;
  }

  // H1.2

  /**
   * @return The initial x-Coordinate
   */
  public int getInitialX() {
    return initialX;
  }

  /**
   * @return The initial y-Coordinate
   */
  public int getInitialY() {
    return initialY;
  }

  /**
   * @return The initial Direction
   */
  public Direction getInitialDirection() {
    return initialDirection;
  }

  /**
   * @return The initial Number of Coins
   */
  public int getInitialNumberOfCoins() {
    return initialNumberOfCoins;
  }

  // H1.3

  /**
   * The relative x-Coordinate is calculated by subtracting the initial x-Coordinate from the current x-Coordinate
   *
   * @return The relative x-Coordinate
   */
  public int getRelativeX() {
    return getX() - initialX;
  }

  /**
   * The relative y-Coordinate is calculated by subtracting the initial y-Coordinate from the current y-Coordinate
   *
   * @return The relative y-Coordinate
   */
  public int getRelativeY() {
    return getY() - initialY;
  }

  /**
   * The relative Direction is UP, if initial and current Direction are equal;
   * RIGHT, if current Direction is 90° to the right from initial;
   * DOWN, if current is vis-à-vis from the initial Direction;
   * otherwise LEFT
   *
   * @return The relative Direction
   */
  public Direction getRelativeDirection() {
    int rel = (getDirection().ordinal() - initialDirection.ordinal() + 4) % 4;
    return Direction.values()[rel];
  }

  /**
   * The relative numberOfCoins is calculated by subtracting the initial numberOfCoins from the current numberOfCoins
   *
   * @return The relative y-Coordinate
   */
  public int getRelativeNumberOfCoins() {
    return getNumberOfCoins() - initialNumberOfCoins;
  }

  /**
   * Set the x-Coordinate of Robot, so that a call of getRelativeX() directly afterwards returns relativeX.
   * Robot is only moved, if new x-Coordinate is inside the World
   */
  public void setRelativeX(int relativeX) {
    int newX = initialX + relativeX;
    if (newX < 0 || newX >= Main.WORLD_SIZE_X) {
      crash();
    }else {
      setX(newX);
    }
  }

  /**
   * Set the y-Coordinate of Robot, so that a call of getRelativeY() directly afterwards returns relativeY.
   * Robot is only moved, if new y-Coordinate is inside the World
   */
  public void setRelativeY(int relativeY) {
    int newY = initialX + relativeY;
    if (newY < 0 || newY >= Main.WORLD_SIZE_Y) {
      crash();
    }else {
      setY(newY);
    }
  }

  /**
   * Turn the Robot so, so that a call of getRelativeDirection() directly afterwards returns relativeDirection.
   */
  public void setRelativeDirection(Direction relativeDirection) {
    int newDirection = (initialDirection.ordinal() + relativeDirection.ordinal()) % 4;
    int numOfTurns = 3 - (newDirection - getDirection().ordinal()) % 4;
    for (int i = 0; i < numOfTurns; i++) {
      turnLeft();
    }
  }

  /**
   * Put as many coins, so that a call of getRelativeNumberOfCoins() directly afterwards returns relativeNumberOfCoins.
   * But don't do it, if the new numberOfCoins is not in [0, currentNumberOfCoins]
   */
  public void setRelativeNumberOfCoins(int relativeNumberOfCoins) {
    int newNumberOfCoins = initialNumberOfCoins + relativeNumberOfCoins;
    if (newNumberOfCoins > getNumberOfCoins() || newNumberOfCoins < 0) {
      crash();
    } else {
      final int putNCoins = getNumberOfCoins() - newNumberOfCoins;
      for (int i = 0; i < putNCoins; i++) {
        putCoin();
      }
    }
  }
}
