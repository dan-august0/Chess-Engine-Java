package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa o Bispo no jogo de xadrez.
 * O Bispo se move na diagonal quantas casas quiser
 * até encontrar um obstáculo. Sempre permanece nas
 * casas da mesma cor durante toda a partida.
 */
public class Bishop extends Piece {

    public Bishop(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♗" : "♝";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        // As 4 direções diagonais que o Bispo pode se mover
        int[][] directions = {
            {-1, -1}, // diagonal superior esquerda
            {-1,  1}, // diagonal superior direita
            { 1, -1}, // diagonal inferior esquerda
            { 1,  1}  // diagonal inferior direita
        };

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            // Continua andando na diagonal enquanto a posição for válida
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