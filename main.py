import enum
from typing import List, Optional, Tuple


# Create an Enum to store empty, x's and o's
class Piece(enum.Enum):
    EMPTY = " "
    X = "X"
    O = "O"

    def __str__(self):
        return self.value


class Player:
    def __init__(self, name: str, piece: Piece):
        # Store the player's name and piece type
        self.name = name
        self.piece = piece


class Board:
    def __init__(self, size: int = 3):
        # Store the board's size and create a grid
        self.size = size
        self.grid: List[List[Piece]] = [
            [Piece.EMPTY for _ in range(size)] for _ in range(size)
        ]

    def print_board(self):
        for index, row in enumerate(self.grid):
            row_str = " | ".join(str(cell) for cell in row)
            print(row_str)
            if index < self.size - 1:
                print("-" * (self.size * 4 - 3))
        print("\n")

    def place_piece(self, row: int, col: int, piece: Piece) -> bool:
        if self.grid[row][col] == Piece.EMPTY:
            self.grid[row][col] = piece
            return True
        else:
            return False

    def check_win(self, piece: Piece) -> bool:
        # Check rows
        for row in self.grid:
            if all(cell == piece for cell in row):
                return True

        # Check columns
        for col in range(self.size):
            if all(self.grid[row][col] == piece for row in range(self.size)):
                return True

        # Check diagonals
        if all(self.grid[index][index] == piece for index in range(self.size)):
            return True
        if all(
            self.grid[index][self.size - index - 1] == piece
            for index in range(self.size)
        ):
            return True

        return False

    def is_full(self) -> bool:
        return all(cell != Piece.EMPTY for row in self.grid for cell in row)


class Game:
    def __init__(self, player1: Player, player2: Player):
        self.board = Board()
        self.players = [player1, player2]
        self.current_player_index = 0

    def switch_player(self):
        self.current_player_index = 1 - self.current_player_index

    def get_current_player(self) -> Player:
        return self.players[self.current_player_index]

    def play(self):
        while True:
            self.board.print_board()
            player = self.get_current_player()
            print(f"{player.name}'s turn ({player.piece}).")

            # Get valid move
            row, col = self.get_move()
            if self.board.place_piece(row, col, player.piece):
                if self.board.check_win(player.piece):
                    self.board.print_board()
                    print(f"{player.name} wins!")
                    break
                elif self.board.is_full():
                    self.board.print_board()
                    print("It's a draw!")
                    break
                else:
                    self.switch_player()
            else:
                print("Invalid move. Try again.")

    def get_move(self) -> Tuple[int]:
        while True:
            try:
                move = input("Enter your move as row,col (e.g., 1,2):")
                row_str, col_str = move.strip().split(",")
                row, col = int(row_str) - 1, int(col_str) - 1
                if 0 <= row < self.board.size and 0 <= col < self.board.size:
                    return row, col
                else:
                    print("Move out of bounds. Try again.")
            except ValueError:
                print("Invalid input format. Try again.")

if __name__ == "__main__":
    player1 = Player(name="ReDay", piece=Piece.X)
    player2 = Player(name="Krises", piece=Piece.O)
    game = Game(player1, player2)
    game.play()
