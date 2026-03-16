package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a Torre no jogo de xadrez.
 * A Torre se move em linha reta — horizontal ou vertical —
 * quantas casas quiser até encontrar um obstáculo.
 */
public class Rook extends Piece {

    public Rook(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♖" : "♜";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        // As 4 direções que a Torre pode se mover (cima, baixo, esquerda, direita)
        int[][] directions = {
            {-1, 0}, // cima
            {1, 0},  // baixo
            {0, -1}, // esquerda
            {0, 1}   // direita
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            // Continua andando na direção enquanto a posição for válida
            while (new Position(r, c).isValid()) {
                Piece target = board.getPiece(r, c);

                if (target == null) {
                    // Casa vazia — pode mover
                    moves.add(new Position(r, c));
                } else {
                    // Casa ocupada — pode capturar se for inimigo
                    if (target.getColor() != this.color) {
                        moves.add(new Position(r, c));
                    }
                    // Para em ambos os casos — não pode pular peças
                    break;
                }

                r += dir[0];
                c += dir[1];
            }
        }

        return moves;
    }
}