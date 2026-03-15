package chess.pieces;

import chess.Color;

/*
  Representa o Bispo no jogo de xadrez;
  Movimento: será implementado na Fase 2.
  O Bispo se move na diagonal e sempre permanece nas casas da mesma cor durante toda a partida.
 */

public class Bishop extends Piece {

    public Bishop(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♗" : "♝";
    }
}