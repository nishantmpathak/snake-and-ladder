package com.nishant.game;

import com.nishant.game.constants.GameStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Ladder ladder = new Ladder(2, 98);
    HashMap<Integer, Ladder> ladders = new HashMap<>();
    ladders.put(ladder.getStart(), ladder);
    Snake snake = new Snake(99, 3);
    HashMap<Integer, Snake> snakes = new HashMap<>();
    snakes.put(snake.getStart(), snake);

    Player player1 = new Player("Vihaan");
    Player player2 = new Player("Aayan");

    Game game = new Game();
    game.init(new ArrayList<>(List.of(player1, player2)), snakes, ladders);
    System.out.println("Game is in " + game.getStatus());

    while (game.getStatus().equalsIgnoreCase(GameStatus.IN_PROGRESS.toString())) {
      Player playingPerson = game.nextPlayer();
      game.makeMove(playingPerson, Dice.getDiceNumber());
      game.updateGameStatus();
    }

    System.out.println("Winners are" + game.getWinners().toString());
  }
}
