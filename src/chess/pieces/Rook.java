package chess.pieces;

import chess.Color;

public class Rook extends Piece {

    public Rook(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♖" : "♜";
    }
}