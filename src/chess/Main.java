package chess;

import chess.board.Board;

/**
 Classe principal do projeto Chess Engine Java.
 Ponto de entrada do programa — jogo é iniciado.
 */

public class Main {
    public static void main(String[] args) {

        // Cria o tabuleiro e posiciona todas as peças
        Board board = new Board();
        // Imprime o tabuleiro no console
        board.print();
    }
}