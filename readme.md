# Tic-Tac-Toe

* This is part of my practice of exercises provided by the book _Agile Technical Practices Distilled_

Implement the game of Tic-Tac-Toe again, following all TDD practices plus object calisthenics rules. When you are
finished compared the solution you came up with first and the one using object calisthenics

    | Tip
    | Try to make invalid state unrepresentable. Make the compiler work for you. If client 
    | code tries to pass invalid state to your code, the compile should signal an error

### Notes

#### Object Calisthenics

* Only one level of indentation per method
* Don't use ELSE keyword
* Wrap all primitives and strings (wrap primitive types in classes)
* One dot per line
* Don't abbreviate
* Keep all entities small
* No classes with more than two instance variables
* No getter/setters/properties
* All classes must have state

#### Tic-Tac-Toe rules:

* X always goes first
* Players alternate placing X's and O's on the board
* A player with three X's or O's in a row (horizontally, vertically or diagonally) wins
* If all nine squares are filled and neither player achieves three in a row, the game is a draw
