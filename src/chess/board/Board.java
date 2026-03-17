package chess.board;

import chess.Color;
import chess.Position;
import chess.pieces.*;

/**
 * Representa o tabuleiro de xadrez.
 * Gerencia as 64 casas (8x8), o posicionamento inicial das peças,
 * a movimentação e a verificação de fim de jogo.
 */
public class Board {

    // Array bidimensional 8x8 que representa as casas do tabuleiro
    // Cada posição guarda uma peça ou null se a casa estiver vazia
    private Piece[][] squares;

    /**
     * Construtor do tabuleiro.
     * Inicializa o array e posiciona todas as peças.
     */
    public Board() {
        squares = new Piece[8][8];
        setup();
    }

    /**
     * Posiciona todas as peças nas suas posições iniciais.
     * Linha 0 = peças pretas, linha 1 = peões pretos
     * Linha 6 = peões brancos, linha 7 = peças brancas
     */
    private void setup() {
        squares[0][0] = new Rook(Color.BLACK, 0, 0);
        squares[0][1] = new Knight(Color.BLACK, 0, 1);
        squares[0][2] = new Bishop(Color.BLACK, 0, 2);
        squares[0][3] = new Queen(Color.BLACK, 0, 3);
        squares[0][4] = new King(Color.BLACK, 0, 4);
        squares[0][5] = new Bishop(Color.BLACK, 0, 5);
        squares[0][6] = new Knight(Color.BLACK, 0, 6);
        squares[0][7] = new Rook(Color.BLACK, 0, 7);

        for (int col = 0; col < 8; col++) {
            squares[1][col] = new Pawn(Color.BLACK, 1, col);
        }

        squares[7][0] = new Rook(Color.WHITE, 7, 0);
        squares[7][1] = new Knight(Color.WHITE, 7, 1);
        squares[7][2] = new Bishop(Color.WHITE, 7, 2);
        squares[7][3] = new Queen(Color.WHITE, 7, 3);
        squares[7][4] = new King(Color.WHITE, 7, 4);
        squares[7][5] = new Bishop(Color.WHITE, 7, 5);
        squares[7][6] = new Knight(Color.WHITE, 7, 6);
        squares[7][7] = new Rook(Color.WHITE, 7, 7);

        for (int col = 0; col < 8; col++) {
            squares[6][col] = new Pawn(Color.WHITE, 6, col);
        }
    }

    /**
     * Retorna a peça em uma determinada posição do tabuleiro.
     * @param row Linha (0 a 7)
     * @param col Coluna (0 a 7)
     * @return A peça na posição ou null se estiver vazia
     */
    public Piece getPiece(int row, int col) {
        return squares[row][col];
    }

    /**
     * Move uma peça de uma posição para outra.
     * Se houver uma peça inimiga no destino ela é capturada.
     * @param from Posição de origem
     * @param to   Posição de destino
     */
    public void movePiece(Position from, Position to) {
        Piece piece = squares[from.getRow()][from.getCol()];
        squares[to.getRow()][to.getCol()] = piece;
        squares[from.getRow()][from.getCol()] = null;
        piece.setPosition(to.getRow(), to.getCol());
    }

    public void undoMove(Position from, Position to, Piece piece, Piece captured) {
        squares[from.getRow()][from.getCol()] = piece;
        squares[to.getRow()][to.getCol()] = captured;
        piece.setPosition(from.getRow(), from.getCol());
    }


    /**
     * Verifica se o Rei de uma determinada cor ainda está vivo.
     * Usada para detectar fim de jogo.
     * @param color Cor do Rei a verificar
     * @return true se o Rei estiver no tabuleiro, false se foi capturado
     */
    public boolean isKingAlive(Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece instanceof King && piece.getColor() == color) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Imprime o tabuleiro no console com as peças nas suas posições.
     * Casas vazias são representadas por '.'
     */
    public void print() {
        System.out.println("  a  b  c  d  e  f  g  h");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " ");
            for (int col = 0; col < 8; col++) {
                if (squares[row][col] == null) {
                    System.out.print(".  ");
                } else {
                    System.out.print(squares[row][col].getSymbol() + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("  a  b  c  d  e  f  g  h");
    }
}