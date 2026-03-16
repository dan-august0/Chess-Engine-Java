package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa o Rei no jogo de xadrez.
 * O Rei é a peça mais importante — se for capturado o jogo acaba.
 * Move-se em qualquer direção mas apenas 1 casa por vez.
 */
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

        // As 8 direções possíveis — igual a Rainha mas só 1 casa
        int[][] directions = {
            {-1,  0},
            { 1,  0},
            { 0, -1},
            { 0,  1},
            {-1, -1},
            {-1,  1},
            { 1, -1},
            { 1,  1}
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            Position pos = new Position(r, c);

            // Verifica apenas 1 casa em cada direção
            if (pos.isValid()) {
                Piece target = board.getPiece(r, c);

                // Pode mover se a casa estiver vazia ou tiver peça inimiga
                if (target == null || target.getColor() != this.color) {
                    moves.add(pos);
                }
            }
        }

        return moves;
    }
}