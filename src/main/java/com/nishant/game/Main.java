package com.nishant.game;

import com.nishant.game.constants.GameStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    HashMap<Integer, Integer> ladder = new HashMap<>();
    ladder.put(2, 98);
    HashMap<Integer, Integer> snake = new HashMap<>();
    snake.put(99, 3);

    Player player1 = new Player("Vihaan");
    Player player2 = new Player("Aayan");

    Game game = new Game();
    game.init(new ArrayList<>(List.of(player1, player2)), snake, ladder);
    System.out.println("Game is in " + game.getStatus());

    while (game.getStatus().equalsIgnoreCase(GameStatus.IN_PROGRESS.toString())) {
      Player playingPerson = game.nextPlayer();
      game.makeMove(playingPerson, Dice.getDiceNumber());
      game.updateGameStatus();
    }

    System.out.println("Winners are" + game.getWinners().toString());
  }
}
