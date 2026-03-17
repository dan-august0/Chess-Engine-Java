package chess;

import chess.engine.GameLoop;

/**
 * Classe principal do projeto Chess Engine Java.
 * Ponto de entrada do programa — aqui o jogo é iniciado.
 */
public class Main {

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.start();
    }
}