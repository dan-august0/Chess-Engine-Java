package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♙" : "♟";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int direction = (color == Color.WHITE) ? -1 : 1;
        int startRow = (color == Color.WHITE) ? 6 : 1;

        // 1 casa pra frente
        Position oneStep = new Position(row + direction, col);
        if (oneStep.isValid() && board.getPiece(row + direction, col) == null) {
            moves.add(oneStep);

            // 2 casas pra frente (só no primeiro movimento)
            Position twoSteps = new Position(row + 2 * direction, col);
            if (row == startRow && board.getPiece(row + 2 * direction, col) == null) {
                moves.add(twoSteps);
            }
        }

        // Captura na diagonal esquerda
        Position leftCapture = new Position(row + direction, col - 1);
        if (leftCapture.isValid()) {
            Piece target = board.getPiece(row + direction, col - 1);
            if (target != null && target.getColor() != this.color) {
                moves.add(leftCapture);
            }
        }

        // Captura na diagonal direita
        Position rightCapture = new Position(row + direction, col + 1);
        if (rightCapture.isValid()) {
            Piece target = board.getPiece(row + direction, col + 1);
            if (target != null && target.getColor() != this.color) {
                moves.add(rightCapture);
            }
        }

        return moves;
    }
}