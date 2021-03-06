package com.bilun167.tictactoe;

import com.bilun167.tictactoe.exception.IllegalBoardException;
import com.bilun167.tictactoe.exception.IllegalMoveException;
import com.bilun167.tictactoe.slider.CustomCollectors;
import com.google.common.base.Strings;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
  private final PVector<Optional<Player>> board;
  private final int rowSize;
  // List of rows, cols, diagonals
  private final List<List<Integer>> consecutiveIndices;

  public static final int COUNT_TO_WIN = 3;

  public Board() {
    this(BoardHelper.defaultBoard());
  }

  public Board(int boardSize) {
    this(BoardHelper.defaultBoard(boardSize));
  }

  public Board(List<Optional<Player>> board) {
    if (!BoardHelper.isPerfectSquare(board.size())) {
      throw new IllegalBoardException("The size of the board must be a perfect square (e.g. 9, 16, 25)");
    }

    this.rowSize = (int) Math.sqrt(board.size());
    this.board = TreePVector.from(board);
    this.consecutiveIndices = BoardHelper.calculateConsecutiveIndices(board.size(), rowSize);
  }

  public Optional<Player> findWinner() {
    return consecutiveIndices
        .stream()
        .map(this::findWinningPlayer)
        .filter(Optional::isPresent)
        .findFirst()
        .orElseGet(Optional::empty);
  }

  public boolean isFull() {
    return board.stream().allMatch(Optional::isPresent);
  }

  public Board makeMove(Player player, int index) {
    validateMove(index);
    return new Board(board.with(index, Optional.of(player)));
  }

  @Override
  public int hashCode() {
    return board.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof Board && board.equals(((Board) other).board);

  }

  @Override
  public String toString() {
    final int maxColumnSeparatorLength = BoardHelper.numberOfDigits(board.size());
    final String columnSeparator = " ";
    final String rowSeparator = "\n";

    return IntStream
        .range(0, board.size())
        .mapToObj(i -> {
          final String label = board.get(i).map(Player::name).orElseGet(() -> String.valueOf(i));

          final boolean isEndOfRow = i > 0 && (i + 1) % rowSize == 0;
          final int columnSeparatorLength = maxColumnSeparatorLength - BoardHelper.numberOfDigits(i) + 1;
          final String separator = isEndOfRow ? rowSeparator : Strings.repeat(columnSeparator, columnSeparatorLength);

          return label + separator;
        })
        .collect(Collectors.joining());
  }

  private void validateMove(int index) {
    if (index < 0 || index >= board.size()) {
      throw new IllegalMoveException("You may only enter values between 0 and " + (board.size() - 1) + ".");
    }

    if (board.get(index).isPresent()) {
      throw new IllegalMoveException("Board slot " + index + " is already taken.");
    }
  }

  private Optional<Player> findWinningPlayer(List<Integer> indices) {
    if (indices.size() < COUNT_TO_WIN)
      return Optional.empty();

    final Stream<Optional<Player>> occupants = indices.stream().map(board::get);
    final List<List<Optional<Player>>> combinations = occupants.collect(CustomCollectors.sliding(COUNT_TO_WIN));
    for (final List<Optional<Player>> comb : combinations) {
      Optional<Player> winner = comb.stream()
              .reduce((a, b) -> a.equals(b) ? a : Optional.empty())
              .orElseGet(Optional::empty);
      if (winner.isPresent())
        return winner;
    }
    return Optional.empty();
  }
}
