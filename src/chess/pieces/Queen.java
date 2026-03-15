package chess.pieces;

import chess.Color;

/*
 Representa a Rainha no jogo de xadrez;
 Movimento: será implementado na Fase 2.
 A Rainha é a peça mais poderosa — combina os movimentos da Torre e do Bispo.
 */

public class Queen extends Piece {

    public Queen(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♕" : "♛";
    }
}