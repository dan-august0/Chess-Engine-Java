package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa o Cavalo no jogo de xadrez.
 * O Cavalo se move em formato de L — 2 casas em uma direção
 * e 1 casa perpendicular. É a única peça que pode saltar
 * por cima de outras peças.
 * Usamos N de "kNight" pois K já é usado pelo Rei (King).
 */
public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♘" : "♞";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        // Os 8 saltos possíveis em formato de L
        int[][] jumps = {
            {-2, -1}, {-2, 1},  // 2 cima, 1 lado
            { 2, -1}, { 2, 1},  // 2 baixo, 1 lado
            {-1, -2}, {-1, 2},  // 1 cima, 2 lado
            { 1, -2}, { 1, 2}   // 1 baixo, 2 lado
        };

        for (int[] jump : jumps) {
            int r = row + jump[0];
            int c = col + jump[1];

            Position pos = new Position(r, c);

            if (pos.isValid()) {
                Piece target = board.getPiece(r, c);

                // Pode mover se a casa estiver vazia ou tiver peça inimiga
                // O Cavalo ignora peças no caminho — só verifica o destino
                if (target == null || target.getColor() != this.color) {
                    moves.add(pos);
                }
            }
        }

        return moves;
    }
}