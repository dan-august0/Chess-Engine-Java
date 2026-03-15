package chess.pieces;

import chess.Color;

/*
 Representa o Rei no jogo de xadrez;
 Movimento: será implementado na Fase 2.
 O Rei é a peça mais importante — se ele for capturado, o jogo acaba.
 */

public class King extends Piece {

    public King(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♔" : "♚";
    }
}