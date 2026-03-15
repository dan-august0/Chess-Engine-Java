package chess.pieces;

/*
Classe abstrata que representa uma peça do xadrez;
Todas as peças do jogo herdam desta classe, compartilhando 
atributos comuns como cor e posição no tabuleiro;
*/

import chess.Color;

public abstract class Piece {

     // Cor da peça (WHITE ou BLACK)
    protected Color color;
    // Posição da peça no tabuleiro (linha e coluna)
    protected int row;
    protected int col;

    public Piece(Color color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    //Retorna o símbolo visual da peça para exibição no console.
    //Cada peça filha implementa esse método com seu próprio símbolo.
    public abstract String getSymbol();
}