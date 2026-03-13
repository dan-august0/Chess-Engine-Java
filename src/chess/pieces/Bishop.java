package chess.pieces;

import chess.Color;

public class Bishop extends Piece {

    public Bishop(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♗" : "♝";
    }
}