package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.List;

/**
 * Classe abstrata que representa uma peça de xadrez.
 * Todas as peças do jogo herdam desta classe, compartilhando
 * atributos comuns como cor e posição no tabuleiro.
 */
public abstract class Piece {

    // Cor da peça (WHITE ou BLACK)
    protected Color color;

    // Posição da peça no tabuleiro (linha e coluna)
    protected int row;
    protected int col;

    /**
     * Construtor da peça.
     * @param color Cor da peça (WHITE ou BLACK)
     * @param row   Linha inicial da peça no tabuleiro (0 a 7)
     * @param col   Coluna inicial da peça no tabuleiro (0 a 7)
     */
    public Piece(Color color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    // Retorna a cor da peça
    public Color getColor() {
        return color;
    }

    // Retorna a linha atual da peça no tabuleiro
    public int getRow() {
        return row;
    }

    // Retorna a coluna atual da peça no tabuleiro
    public int getCol() {
        return col;
    }

    /**
     * Atualiza a posição da peça no tabuleiro após um movimento.
     * @param row Nova linha
     * @param col Nova coluna
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Retorna o símbolo visual da peça para exibição no console.
     * Cada peça filha implementa esse método com seu próprio símbolo.
     */
    public abstract String getSymbol();

    /**
     * Retorna a lista de movimentos válidos para a peça
     * na posição atual, considerando o estado do tabuleiro.
     * Cada peça filha implementa suas próprias regras de movimento.
     * @param board Estado atual do tabuleiro
     */
    public abstract List<Position> getLegalMoves(Board board);
}