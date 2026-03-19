package chess.pieces;

import chess.Color;
import chess.Position;
import chess.TerminalUtils;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        if (TerminalUtils.isUnicodeSupported()) {
            return color == Color.WHITE ? "♗" : "♝";
        }
        return color == Color.WHITE ? "B" : "b";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] directions = {
            {-1, -1}, {-1,  1},
            { 1, -1}, { 1,  1}
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            while (new Position(r, c).isValid()) {
                Piece target = board.getPiece(r, c);

                if (target == null) {
                    moves.add(new Position(r, c));
                } else {
                    if (target.getColor() != this.color) {
                        moves.add(new Position(r, c));
                    }
                    break;
                }

                r += dir[0];
                c += dir[1];
            }
        }

        return moves;
    }
}