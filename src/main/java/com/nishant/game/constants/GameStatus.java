package com.nishant.game.constants;

public enum GameStatus {
  IN_PROGRESS("in-progress"),
  COMPLETED("completed");
  private String value;

  private GameStatus(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }
}
