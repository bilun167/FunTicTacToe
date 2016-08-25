package com.bilun167.tictactoe;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardHelper {
  private static final int DEFAULT_BOARD_SIZE = 9;

  /**
   * Validator for valid input (board size)
   * @param number
   * @return
     */
  public static boolean isPerfectSquare(int number) {
    return Math.sqrt(number) % 1 == 0;
  }

  /**
   * Each "cell" may belong to a player or not belong to any player, hence the usage of Optional<Player>
   * @return List<Optional<Player>>
     */
  public static List<Optional<Player>> defaultBoard() {
    return Stream.generate(Optional::<Player>empty).limit(DEFAULT_BOARD_SIZE).collect(Collectors.toList());
  }

  /**
   * Group the indexes on the table into consecutive straight-lines. This is helpful for determining
   * winning states.
   * @param boardSize
   * @param rowSize
   * @return List<List<Integer>> Something like ((0, 3, 6), (1, 4, 7), ..., (0, 1, 2), (3, 4, 5), ...)
     */
  public static List<List<Integer>> calculateConsecutiveIndices(int boardSize, int rowSize) {
    final Collection<List<Integer>> columnIndices =
            IntStream.range(0, boardSize).boxed().collect(Collectors.groupingBy(i -> i % rowSize)).values();

    final Collection<List<Integer>> rowIndices =
            IntStream.range(0, boardSize).boxed().collect(Collectors.groupingBy(i -> i / rowSize)).values();

    final List<Integer> leftToRightDiagonalIndices =
            IntStream.rangeClosed(0, rowSize - 1).map(i -> i * (rowSize + 1)).boxed().collect(Collectors.toList());

    final List<Integer> rightToLeftDiagonalIndices =
            IntStream.rangeClosed(1, rowSize).map(i -> i * (rowSize - 1)).boxed().collect(Collectors.toList());

    return ImmutableList.<List<Integer>>builder()
            .addAll(columnIndices)
            .addAll(rowIndices)
            .add(leftToRightDiagonalIndices)
            .add(rightToLeftDiagonalIndices)
            .build();
  }

  public static int numberOfDigits(int number) {
    return String.valueOf(number).length();
  }
}
