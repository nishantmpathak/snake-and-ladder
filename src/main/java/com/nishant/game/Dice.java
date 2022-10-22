package com.nishant.game;

import java.util.Random;

public class Dice {

  public static int getDiceNumber() {
    return new Random().nextInt(6) + 1;
  }
}
