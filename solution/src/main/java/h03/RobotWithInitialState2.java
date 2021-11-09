package h03;

import fopbot.*;

public class RobotWithInitialState2 extends RobotWithInitialState {

  private int relativeNumberOfCoins;

  /**
   * @param x             Initial x-Coordinate
   * @param y             Initial y-Coordinate
   * @param direction     Initial direction
   * @param numberOfCoins Initial numberOfCoins
   */
  public RobotWithInitialState2(int x, int y, Direction direction, int numberOfCoins) {
    super(x, y, direction, numberOfCoins);
    relativeNumberOfCoins = 0;
  }

  @Override
  public void pickCoin() {
    super.pickCoin();
    relativeNumberOfCoins++;
  }

  @Override
  public void putCoin() {
    super.putCoin();
    relativeNumberOfCoins--;
  }

  @Override
  public int getRelativeNumberOfCoins() {
    return relativeNumberOfCoins;
  }
}
