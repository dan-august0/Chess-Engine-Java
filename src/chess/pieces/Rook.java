package chess.pieces;

import chess.Color;

/*
 Representa a Torre no jogo de xadrez;
 Movimento: será implementado na Fase 2.
 A Torre se move em linha reta — horizontal ou vertical.
 */

public class Rook extends Piece {

    public Rook(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♖" : "♜";
    }
}