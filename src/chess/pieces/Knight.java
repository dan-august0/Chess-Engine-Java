package chess.pieces;

import chess.Color;

/*
 Representa o Cavalo no jogo de xadrez.
 Símbolo: N (branco) / n (preto)
 Movimento: será implementado na Fase 2.
 O Cavalo se move em formato de L e é a única peça que pode saltar por cima das outras.
*/

public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♘" : "♞";
    }
}