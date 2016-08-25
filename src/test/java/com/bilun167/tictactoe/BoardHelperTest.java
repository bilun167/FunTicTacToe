package com.bilun167.tictactoe;

import com.google.common.collect.ImmutableList;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class BoardHelperTest {

  @Test
  public void testIsPerfectSquareWithOddSquare() {
    assertTrue(BoardHelper.isPerfectSquare(9));
  }

  @Test
  public void testIsPerfectSquareWithEvenSquare() {
    assertTrue(BoardHelper.isPerfectSquare(64));
  }

  @Test
  public void testIsPerfectSquareWithOddNonSquare() {
    assertFalse(BoardHelper.isPerfectSquare(37));
  }

  @Test
  public void testIsPerfectSquareWithEvenNonSquare() {
    assertFalse(BoardHelper.isPerfectSquare(62));
  }

  @Test
  public void testDefaultBoard() {
    Board expected = BoardDsl.board(
      BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
      BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty(),
      BoardDsl.empty(), BoardDsl.empty(), BoardDsl.empty()
    );

    assertEquals(expected, new Board(BoardHelper.defaultBoard()));
  }

  @Test
  public void testCalculateConsecutiveIndices() {
    List<List<Integer>> expected = ImmutableList.of(
            ImmutableList.of(0, 1, 2),
            ImmutableList.of(3, 4, 5),
            ImmutableList.of(6, 7, 8),
            ImmutableList.of(0, 3, 6),
            ImmutableList.of(1, 4, 7),
            ImmutableList.of(2, 5, 8),
            ImmutableList.of(0, 4, 8),
            ImmutableList.of(3, 7),
            ImmutableList.of(6),
            ImmutableList.of(1, 5),
            ImmutableList.of(2),
            ImmutableList.of(2, 4, 6),
            ImmutableList.of(1, 3),
            ImmutableList.of(0),
            ImmutableList.of(5, 7),
            ImmutableList.of(8)
    );
    List<List<Integer>> actual = BoardHelper.calculateConsecutiveIndices(9, 3);

    assertListsEqual(expected, actual);
  }

  @Test
  public void testCalculateConsecutiveIndicesLargeBoard() {
    List<List<Integer>> expected = ImmutableList.of(
            ImmutableList.of(0,  1,  2,  3,  4),
            ImmutableList.of(5,  6,  7,  8,  9),
            ImmutableList.of(10, 11, 12, 13, 14),
            ImmutableList.of(15, 16, 17, 18, 19),
            ImmutableList.of(20, 21, 22, 23, 24),
            ImmutableList.of(0,  5,  10, 15, 20),
            ImmutableList.of(1,  6,  11, 16, 21),
            ImmutableList.of(2,  7,  12, 17, 22),
            ImmutableList.of(3,  8,  13, 18, 23),
            ImmutableList.of(4,  9,  14, 19, 24),
            ImmutableList.of(0,  6,  12, 18, 24),
            ImmutableList.of(5,  11, 17, 23),
            ImmutableList.of(10, 16, 22),
            ImmutableList.of(15, 21),
            ImmutableList.of(20),
            ImmutableList.of(1,  7, 13, 19),
            ImmutableList.of(2,  8, 14),
            ImmutableList.of(3,  9),
            ImmutableList.of(4),
            ImmutableList.of(4,  8,  12, 16, 20),
            ImmutableList.of(3,  7,  11, 15),
            ImmutableList.of(2,  6,  10),
            ImmutableList.of(1,  5),
            ImmutableList.of(0),
            ImmutableList.of(9,  13, 17, 21),
            ImmutableList.of(14, 18, 22),
            ImmutableList.of(19, 23),
            ImmutableList.of(24)
    );
    List<List<Integer>> actual = BoardHelper.calculateConsecutiveIndices(25, 5);

    assertListsEqual(expected, actual);
  }

  private <T> void assertListsEqual(List<T> first, List<T> second) {
    if (first == null) {
      assertNull(second);
    } else {
      assertNotNull(second);
      assertEquals(first.size(), second.size());
      for (T element : first) {
        assertTrue(second.contains(element));
      }
    }
  }
}
