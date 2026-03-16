package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♕" : "♛";
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] directions = {
            {-1,  0}, // cima
            { 1,  0}, // baixo
            { 0, -1}, // esquerda
            { 0,  1}, // direita
            {-1, -1}, // diagonal superior esquerda
            {-1,  1}, // diagonal superior direita
            { 1, -1}, // diagonal inferior esquerda
            { 1,  1}  // diagonal inferior direita
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