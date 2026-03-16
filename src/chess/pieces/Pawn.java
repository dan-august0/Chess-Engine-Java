package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa o Peão no jogo de xadrez.
 * É a peça mais complexa — tem regras especiais de movimento:
 * - Anda 1 casa pra frente
 * - No primeiro movimento pode andar 2 casas
 * - Captura na diagonal (não pra frente!)
 * - Brancas sobem (row diminui), pretas descem (row aumenta)
 */
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

        // Brancas sobem (-1), pretas descem (+1)
        int direction = (color == Color.WHITE) ? -1 : 1;

        // Linha inicial de cada cor — onde o peão pode andar 2 casas
        int startRow = (color == Color.WHITE) ? 6 : 1;

        // 1 casa pra frente — só se a casa estiver vazia
        Position oneStep = new Position(row + direction, col);
        if (oneStep.isValid() && board.getPiece(row + direction, col) == null) {
            moves.add(oneStep);

            // 2 casas pra frente — só no primeiro movimento e se ambas estiverem vazias
            Position twoSteps = new Position(row + 2 * direction, col);
            if (row == startRow && board.getPiece(row + 2 * direction, col) == null) {
                moves.add(twoSteps);
            }
        }

        // Captura na diagonal esquerda — só se tiver peça inimiga
        Position leftCapture = new Position(row + direction, col - 1);
        if (leftCapture.isValid()) {
            Piece target = board.getPiece(row + direction, col - 1);
            if (target != null && target.getColor() != this.color) {
                moves.add(leftCapture);
            }
        }

        // Captura na diagonal direita — só se tiver peça inimiga
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