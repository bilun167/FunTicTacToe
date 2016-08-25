package com.bilun167.tictactoe;

import com.bilun167.tictactoe.exception.IllegalBoardException;
import com.bilun167.tictactoe.exception.IllegalMoveException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

  @Test
  public void testEmptyBoardHasNoWinner() {
    assertEquals(BoardDsl.empty(), new Board().findWinner());
  }

  @Test
  public void testSparseBoardHasNoWinner() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x()
    );
    assertEquals(BoardDsl.empty(), board.findWinner());
  }

  @Test
  public void testSparseBoardLargeHasNoWinner() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(), BoardDsl.x(),     BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o()
    );
    assertEquals(BoardDsl.empty(), board.findWinner());
  }

  @Test
  public void testFullBoardHasNoWinner() {
    Board board = BoardDsl.board(
        BoardDsl.x(), BoardDsl.x(), BoardDsl.o(),
        BoardDsl.o(), BoardDsl.o(), BoardDsl.x(),
        BoardDsl.x(), BoardDsl.o(), BoardDsl.x()
    );
    assertEquals(BoardDsl.empty(), board.findWinner());
  }

  @Test
  public void testWinnerInRow() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.x(),     BoardDsl.x(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );
    assertEquals(BoardDsl.x(), board.findWinner());
  }

  @Test
  public void testWinnerInColumn() {
    Board board = BoardDsl.board(
        BoardDsl.empty(), BoardDsl.o(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.o(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.o(), BoardDsl.empty()
    );
    assertEquals(BoardDsl.o(), board.findWinner());
  }

  @Test
  public void testWinnerInColumnLargeBoard() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(), BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(), BoardDsl.x(),     BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o()
    );
    assertEquals(BoardDsl.o(), board.findWinner());
  }

  @Test
  public void testWinnerInLeftToRightDiagonal() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.o(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.x(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.o(), BoardDsl.x()
    );
    assertEquals(BoardDsl.x(), board.findWinner());
  }

  @Test
  public void testWinnerInRightToLeftDiagonal() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.o(), BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.o(), BoardDsl.empty(),
        BoardDsl.x(),     BoardDsl.o(), BoardDsl.x()
    );
    assertEquals(BoardDsl.o(), board.findWinner());
  }

  @Test
  public void testWinnerInRightToLeftDiagonalLargeBoard() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(), BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.o(),     BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.o(),     BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty()
    );
    assertEquals(BoardDsl.o(), board.findWinner());
  }

  @Test
  public void testEmptyBoardIsNotFull() {
    Board board = new Board();
    assertFalse(board.isFull());
  }

  @Test
  public void testSparseBoardIsNotFull() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );
    assertFalse(board.isFull());
  }

  @Test
  public void testSparseWinningBoardIsNotFull() {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.x(),     BoardDsl.x(),
        BoardDsl.empty(), BoardDsl.o(),     BoardDsl.empty(),
        BoardDsl.o(),     BoardDsl.empty(), BoardDsl.empty()
    );
    assertFalse(board.isFull());
  }

  @Test
  public void testFullBoardIsFull() {
    Board board = BoardDsl.board(
        BoardDsl.x(), BoardDsl.o(), BoardDsl.x(),
        BoardDsl.x(), BoardDsl.o(), BoardDsl.x(),
        BoardDsl.o(), BoardDsl.x(), BoardDsl.o()
    );
    assertTrue(board.isFull());
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveOutOfBoundsPositive() throws IllegalMoveException {
    Board board = BoardDsl.board(
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );
    board.makeMove(Player.X, 10);
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveOutOfBoundsNegative() throws IllegalMoveException {
    Board board = new Board();
    board.makeMove(Player.X, -1);
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveSpotAlreadyTaken() throws IllegalMoveException {
    Board board = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );
    board.makeMove(Player.X, 0);
  }

  @Test
  public void testMakeMoveEmptyBoard() throws IllegalMoveException {
    Board originalBoard = BoardDsl.board(
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );

    Board actualBoard = originalBoard.makeMove(Player.X, 0);

    Board expectedBoard = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );

    assertEquals(expectedBoard, actualBoard);
  }

  @Test
  public void testMakeMoveSparseBoard() throws IllegalMoveException {
    Board originalBoard = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.o(),     BoardDsl.empty(),
        BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(),
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o()
    );

    Board actualBoard = originalBoard.makeMove(Player.O, 2);

    Board expectedBoard = BoardDsl.board(
        BoardDsl.x(),     BoardDsl.o(),     BoardDsl.o(),
        BoardDsl.empty(), BoardDsl.x(),     BoardDsl.empty(),
        BoardDsl.x(),     BoardDsl.empty(), BoardDsl.o()
    );

    assertEquals(expectedBoard, actualBoard);
  }

  @Test(expected = IllegalBoardException.class)
  public void testNonSquareBoard() {
    BoardDsl.board(
        BoardDsl.x(), BoardDsl.empty(), BoardDsl.empty()
    );
  }
}
