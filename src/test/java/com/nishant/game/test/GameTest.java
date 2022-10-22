package com.nishant.game.test;

import com.nishant.game.Dice;
import com.nishant.game.Game;
import com.nishant.game.Player;
import com.nishant.game.constants.GameStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
  Game game;

  @Before
  public void init() {
    game = new Game();
    game.init(new ArrayList<>(List.of(getPlayer1(), getPlayer2())), getSnake(), getLadder());
  }

  @Test
  public void game_initialized_successfully() {
    Assert.assertEquals(game.getStatus(), GameStatus.IN_PROGRESS.toString());
    Assert.assertEquals(game.getBoard().getLadder(), getLadder());
    Assert.assertEquals(game.getBoard().getSnake(), getSnake());
    Assert.assertEquals(game.getPlayers(), new ArrayList<>(List.of(getPlayer1(), getPlayer2())));
  }

  @Test
  public void game_successfully_chance_passed_to_next_player() {
    Player playingPerson1 = game.nextPlayer();
    String player1 = playingPerson1.getName();
    game.nextPlayer();
    String samePlayerAgain = game.nextPlayer().getName();
    Assert.assertEquals(player1, samePlayerAgain);
  }

  @Test
  public void successfully_ladder_claimed() {
    Player playingPerson = game.nextPlayer();
    game.makeMove(playingPerson, 2);
    Assert.assertEquals(playingPerson.getPosition(), Integer.valueOf(98));
  }

  @Test
  public void successfully_snake_gulp_down() {
    Player playingPerson = game.nextPlayer();
    playingPerson.setPosition(98);
    game.makeMove(playingPerson, 1);
    Assert.assertEquals(playingPerson.getPosition(), Integer.valueOf(3));
  }

  @Test
  public void game_is_successfully_finished() {
    while (game.getStatus().equalsIgnoreCase(GameStatus.IN_PROGRESS.toString())) {
      Player playingPerson = game.nextPlayer();
      game.makeMove(playingPerson, Dice.getDiceNumber());
      game.updateGameStatus();
    }
    Assert.assertNotNull(game.getWinners());
    Assert.assertEquals(game.getWinners().get(0).getPosition(), Integer.valueOf(100));
  }

  public HashMap<Integer, Integer> getLadder() {
    HashMap<Integer, Integer> ladder = new HashMap<>();
    ladder.put(2, 98);
    return ladder;
  }

  public HashMap<Integer, Integer> getSnake() {
    HashMap<Integer, Integer> ladder = new HashMap<>();
    ladder.put(99, 3);
    return ladder;
  }

  public Player getPlayer1() {
    return new Player("Vihaan");
  }

  public Player getPlayer2() {
    return new Player("Aayan");
  }
}
