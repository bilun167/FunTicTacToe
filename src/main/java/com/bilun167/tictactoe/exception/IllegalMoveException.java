package com.bilun167.tictactoe.exception;

public class IllegalMoveException extends RuntimeException {
  public IllegalMoveException(String message) {
    super(message);
  }
}
