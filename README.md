Project: Simple Chess

Simple Chess is a desktop chess application written in Java using Swing/AWT. It demonstrates object-oriented design for chess pieces, a game loop with rendering, mouse input handling, and full rule enforcement (check, checkmate, castling, promotion, stalemate).

Key features

- Move validation for all piece types: Pawn, Rook, Knight, Bishop, Queen, King
- Game state management: check, checkmate, stalemate
- Castling and pawn promotion
- Move simulation using a separate board state (`simPieces`) to validate moves before committing
- Lightweight Swing UI with turn indicator and in-game status messages

Getting started

Prerequisites

- Java JDK 11+ installed and available on your PATH

Build and run (from project root)

```bash
javac -d out -sourcepath src src/main/Main.java
java -cp out main.Main
```

Project structure

- `src/main/Main.java` — application entry point; creates the window and starts the game
- `src/main/GamePanel.java` — main game loop, input handling, rendering, and game state logic
- `src/piece/Piece.java` and subclasses — piece movement logic, drawing, and collision checks
- `src/main/Board.java` — board rendering utilities