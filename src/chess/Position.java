package chess;

/**
 * Representa uma posição no tabuleiro de xadrez.
 * Cada posição é definida por uma linha (row) e uma coluna (col),
 * ambas variando de 0 a 7.
 */
public class Position {

    private int row;
    private int col;

    /**
     * Construtor da posição.
     * @param row Linha (0 a 7)
     * @param col Coluna (0 a 7)
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Retorna a linha da posição
    public int getRow() {
        return row;
    }

    // Retorna a coluna da posição
    public int getCol() {
        return col;
    }

    /**
     * Verifica se a posição está dentro dos limites do tabuleiro.
     * Evita acessar posições inválidas como row=-1 ou col=8.
     */
    public boolean isValid() {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /**
     * Representação textual da posição no formato de xadrez.
     * Exemplo: row=0, col=0 → "a8"
     */
    @Override
    public String toString() {
        char colLetter = (char) ('a' + col);
        int rowNumber = 8 - row;
        return "" + colLetter + rowNumber;
    }
}