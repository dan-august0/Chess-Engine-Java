package chess.pieces;

import chess.Color;

public class Pawn extends Piece {

    public Pawn(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♙" : "♟";
    }
}