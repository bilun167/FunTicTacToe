package com.bilun167.tictactoe;

import com.bilun167.tictactoe.exception.IllegalMoveException;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class TicTacToe {
  public void play() {
    final GameState finishedGame = Stream
        .iterate(start(), this::turn)
        .filter(GameState::isGameOver)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("Game did not complete!"));

    final Optional<Player> winner = finishedGame.findWinner();

    System.out.println();
    System.out.println(finishedGame);
    System.out.println(winner.map(player -> "Player " + player + " wins!").orElseGet(() -> "It's a tie!"));
  }

  private GameState start() {
    System.out.println();
    System.out.println("Please enter board size (i.e, N = 3, N = 4, ...)");
    final int inputN = new Scanner(System.in).nextInt();
    final int boardSize = inputN * inputN;

    final Board board = new Board(boardSize);
    return new GameState(board);
  }

  private GameState turn(GameState gameState) {
    System.out.println();
    System.out.println(gameState);
    System.out.print("Player " + gameState.getCurrentPlayer() + ", it's your turn, enter a number: ");

    try {
      final int selection = new Scanner(System.in).nextInt();
      return gameState.makeMove(selection);
    } catch (InputMismatchException e) {
      System.out.println();
      System.out.println("Error: you must enter a number.");
      System.out.println("Please try again!");
    } catch (IllegalMoveException e) {
      System.out.println();
      System.out.println("Error: " + e.getMessage());
      System.out.println("Please try again!");
    }

    return gameState;
  }

  public static void main(String[] args) {
    new TicTacToe().play();
  }
}
