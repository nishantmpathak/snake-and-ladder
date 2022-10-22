package com.nishant.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
  private String name;
  private Integer position;

  public Player(String name) {
    this.name = name;
    position = Integer.valueOf(0);
  }
}
