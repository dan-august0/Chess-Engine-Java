package chess;

import chess.board.Board;
import chess.pieces.Piece;
import java.util.List;

/**
 * Classe principal do projeto Chess Engine Java.
 * Ponto de entrada do programa — aqui o jogo é iniciado.
 */
public class Main {

    public static void main(String[] args) {

        // Cria o tabuleiro e posiciona todas as peças
        Board board = new Board();

        // Imprime o tabuleiro no console
        board.print();

        System.out.println("\n--- Testando movimentos ---");

        // Testando movimentos do Peão branco em e2 (linha 6, coluna 4)
        Piece pawn = board.getPiece(6, 4);
        System.out.println("\nMovimentos do Peão branco em e2:");
        List<Position> pawnMoves = pawn.getLegalMoves(board);
        for (Position pos : pawnMoves) {
            System.out.println("  → " + pos);
        }

        // Testando movimentos do Cavalo branco em g1 (linha 7, coluna 6)
        Piece knight = board.getPiece(7, 6);
        System.out.println("\nMovimentos do Cavalo branco em g1:");
        List<Position> knightMoves = knight.getLegalMoves(board);
        for (Position pos : knightMoves) {
            System.out.println("  → " + pos);
        }
    }
}