package com.nishant.game;

import com.nishant.game.constants.CellStatus;
import com.nishant.game.constants.GameStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Game {
  /** @param players list of all players */
  private ArrayList<Player> players;

  private Board board;
  private ArrayList<Player> winners;
  private String status;

  private Queue<Player> queue;

  public void init(
      ArrayList<Player> players,
      HashMap<Integer, Integer> snake,
      HashMap<Integer, Integer> ladder) {
    this.players = players;
    queue = new LinkedList<>(players);
    board = new Board(new HashMap<>(), snake, ladder);
    status = GameStatus.IN_PROGRESS.toString();
    winners = new ArrayList<>();
  }

  public void makeMove(Player player, int number) {
    int currentCell = player.getPosition();
    int newCell = currentCell + number;
    String newCellStatus = board.checkCellStatus(newCell);
    if (StringUtils.equalsAnyIgnoreCase(newCellStatus, CellStatus.CLEAR.toString())) {

      movePlayerFromOldCellToCell(player, currentCell, newCell);
    } else if (StringUtils.equalsAnyIgnoreCase(
        newCellStatus, CellStatus.LADDER_PRESENT.toString())) {
      newCell = board.getLadder().get(newCell);

      movePlayerFromOldCellToCell(player, currentCell, newCell);
    } else if (StringUtils.equalsAnyIgnoreCase(
        newCellStatus, CellStatus.SNAKE_PRESENT.toString())) {
      newCell = board.getSnake().get(newCell);

      movePlayerFromOldCellToCell(player, currentCell, newCell);
    } else if (StringUtils.equalsAnyIgnoreCase(
        newCellStatus, CellStatus.PLAYER_PRESENT.toString())) {
      Player existingPlayer = board.getCells().get(newCell);
      existingPlayer.setPosition(0);

      movePlayerFromOldCellToCell(player, currentCell, newCell);
    } else if (StringUtils.equalsAnyIgnoreCase(newCellStatus, CellStatus.WINNING_CELL.toString())) {
      movePlayerFromOldCellToCell(player, currentCell, newCell);
      winners.add(player);
      queue.remove(player);
    }
  }

  public Player nextPlayer() {
    Player nextPLayer = queue.remove();
    queue.add(nextPLayer);
    return nextPLayer;
  }

  private void movePlayerFromOldCellToCell(Player player, int currentCell, int newCell) {
    player.setPosition(newCell);
    board.getCells().remove(currentCell);
    currentCell = newCell;
    board.getCells().put(currentCell, player);
  }

  public void updateGameStatus() {
    if (queue.size() == 1) {
      setStatus(GameStatus.COMPLETED.toString());
    }
  }
}
