package com.nishant.game.constants;

public enum CellStatus {
  CLEAR("clear"),
  SNAKE_PRESENT("snake present"),
  LADDER_PRESENT("ladder present"),
  PLAYER_PRESENT("player present"),
  WINNING_CELL("winning cell"),
  VOID_CELL("void cell");
  private String value;

  private CellStatus(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }
}
