package chess.pieces;

import chess.Color;

/*
Representa o Peão no jogo de xadrez;    
Movimento: será implementado na Fase 2.
 */

public class Pawn extends Piece {

    public Pawn(Color color, int row, int col) {
        super(color, row, col); // Chama o construtor da classe pai Piece
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♙" : "♟";
    }
}