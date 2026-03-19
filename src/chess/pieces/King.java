package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♔" : "♚";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] directions = {
            {-1,  0}, { 1,  0},
            { 0, -1}, { 0,  1},
            {-1, -1}, {-1,  1},
            { 1, -1}, { 1,  1}
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            Position pos = new Position(r, c);

            if (pos.isValid()) {
                Piece target = board.getPiece(r, c);
                if (target == null || target.getColor() != this.color) {
                    moves.add(pos);
                }
            }
        }

        // Roque pequeno
        if (!hasMoved) {
            Piece rookKingSide = board.getPiece(row, 7);
            if (rookKingSide instanceof Rook && !rookKingSide.hasMoved()) {
                if (board.getPiece(row, 5) == null && board.getPiece(row, 6) == null) {
                    if (!board.isInCheck(color) &&
                        !board.isSquareAttacked(row, 5, color) &&
                        !board.isSquareAttacked(row, 6, color)) {
                        moves.add(new Position(row, 6));
                    }
                }
            }
        }

        // Roque grande
        if (!hasMoved) {
            Piece rookQueenSide = board.getPiece(row, 0);
            if (rookQueenSide instanceof Rook && !rookQueenSide.hasMoved()) {
                if (board.getPiece(row, 1) == null &&
                    board.getPiece(row, 2) == null &&
                    board.getPiece(row, 3) == null) {
                    if (!board.isInCheck(color) &&
                        !board.isSquareAttacked(row, 2, color) &&
                        !board.isSquareAttacked(row, 3, color)) {
                        moves.add(new Position(row, 2));
                    }
                }
            }
        }

        return moves;
    }
}