package chess.pieces;

import chess.Color;
import chess.Position;
import chess.TerminalUtils;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        if (TerminalUtils.isUnicodeSupported()) {
            return color == Color.WHITE ? "♘" : "♞";
        }
        return color == Color.WHITE ? "N" : "n";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] jumps = {
            {-2, -1}, {-2, 1},
            { 2, -1}, { 2, 1},
            {-1, -2}, {-1, 2},
            { 1, -2}, { 1, 2}
        };

        for (int[] jump : jumps) {
            int r = row + jump[0];
            int c = col + jump[1];

            Position pos = new Position(r, c);

            if (pos.isValid()) {
                Piece target = board.getPiece(r, c);
                if (target == null || target.getColor() != this.color) {
                    moves.add(pos);
                }
            }
        }

        return moves;
    }
}