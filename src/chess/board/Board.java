package chess.board;

import chess.Color;
import chess.pieces.*;

/*
 Representa o tabuleiro de xadrez.
 Gerencia as 64 casas (8x8) e o posicionamento inicial das peças.
 */

public class Board {

    // Array bidimensional 8x8 que representa as casas do tabuleiro
    // Cada posição guarda uma peça ou null se a casa estiver vazia
    private Piece[][] squares;

    public Board() {
        squares = new Piece[8][8];
        setup();
    }

    private void setup() {
        // Peças pretas
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

        // Peças brancas
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

    public Piece getPiece(int row, int col) {
        return squares[row][col];
    }

    /*
     Imprime o tabuleiro no console com as peças nas suas posições.
     Casas vazias são representadas por '.'
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