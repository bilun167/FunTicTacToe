FunTicTacToe
===================

Description
-------------
This is a text based 2­-player tic­tac­toe game. The players would need to take turns at placing an `X` or an `O` on a 3x3 board.

Link: [https://en.wikipedia.org/wiki/Tic­tac­toe](https://en.wikipedia.org/wiki/Tic%C2%ADtac%C2%ADtoe%20%22Tictactoe)

```
0 1 2
3 4 5
6 7 8

Player X, it's your turn, enter a number: 4

0 1 2
3 X 5
6 7 8

Player O, it's your turn, enter a number: 
```

**Game Rules**

1. Player 1’s marker is `X`, Player 2’s marker is `O`.

2. The two players take turns placing their marker on the board, starting with Player 1.

3. The players can place their marker on any vacant spot.

4. The objective of the game is for a player to get three of their markers in a row (horizontally, vertically or diagonally).

**Requirements**

1. Ask for names of players 1 and 2.

2. Alternate between input for player 1 and player 2.

3. Stop when one of the players has won.

4. **Intermediate Challenge**: Extend the board to be a generic NxN board where the game ends if either player gets 3 (not N) ​ in a row in any direction. *(Note: N is determined by user input at the beginning of the game)*

# Quick start

Make sure you have [sbt](http://www.scala-sbt.org/) and 
[Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) installed on your system.

* To run the tic-tac-toe game: `sbt run`
* To run the tests: `sbt test`

See the [http://www.scala-sbt.org/0.13/tutorial/Running.html](SBT documentation) for other commands.

# Navigating the code

Here are the main highlights of the code:

* `Board.java`: an immutable representation of an N x N tic-tac-toe board that can be filled with `X`'s and `O`'s. 
* `GameState.java`: an immutable representation of a game, including the `Board` and the current player's turn. The `makeMove` method places a new X or O on the board and returns a new `GameState`. The `isGameOver` returns true if there is a winner or a tie. The `findWinner` method returns the winner of the game or `Optional.empty` if there is no winner.
* `TicTacToe.java`: this class contains the `main` method and is the only one that performs I/O. It starts with a starting `GameState` and uses `Stream.iterate` and `GameState.makeMove` to generate new `GameStates` based on user input until `Gametate.isGameOver` is true.

# Functional programming

[Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) introduced a number of new features 
to Java, including [lambda expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and 
[streams](http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) that open the door to a more functional style of programming. I wrote this app to see what it was like to write a nearly pure functional app, subject to the following constraints:

* **Immutable data**: just about every variable and field is marked as final. All data structures are immutable.
* **Persistent data structures**: there is no need to worry about performance with a tic-tac-toe game, but for fun, I experimented with the [persistent data structures](http://en.wikipedia.org/wiki/Persistent_data_structure) from the 
[pcollections](http://pcollections.org/) library.
* **Pure functions**: the functions in the `TicTacToe` class are the only ones that performs I/O. All other functions are [pure](http://en.wikipedia.org/wiki/Pure_function).