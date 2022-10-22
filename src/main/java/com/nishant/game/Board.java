package com.nishant.game;

import com.nishant.game.constants.CellStatus;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
  private HashMap<Integer, Player> cells;
  private HashMap<Integer, Integer> snake;
  private HashMap<Integer, Integer> ladder;

  public String checkCellStatus(int newPosition) {
    if (cells.containsKey(newPosition)) {
      return CellStatus.PLAYER_PRESENT.toString();
    } else if (snake.containsKey(newPosition)) {
      return CellStatus.SNAKE_PRESENT.toString();
    } else if (ladder.containsKey(newPosition)) {
      return CellStatus.LADDER_PRESENT.toString();
    } else if (newPosition == 100) {
      return CellStatus.WINNING_CELL.toString();
    } else if (newPosition > 100) {
      return CellStatus.VOID_CELL.toString();
    }
    return CellStatus.CLEAR.toString();
  }
}
