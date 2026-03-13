package chess.pieces;

import chess.Color;

public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♘" : "♞";
    }
}