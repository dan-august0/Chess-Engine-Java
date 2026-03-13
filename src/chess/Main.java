package chess;

import chess.pieces.Pawn;
import chess.pieces.King;

public class Main {
    public static void main(String[] args) {
        
        // Criando algumas peças de teste
        Pawn pawnWhite = new Pawn(Color.WHITE, 6, 0);
        Pawn pawnBlack = new Pawn(Color.BLACK, 1, 0);
        King kingWhite = new King(Color.WHITE, 7, 4);
        King kingBlack = new King(Color.BLACK, 0, 4);

        // Imprimindo os símbolos
        System.out.println("Peças criadas:");
        System.out.println("Peão branco: " + pawnWhite.getSymbol());
        System.out.println("Peão preto: " + pawnBlack.getSymbol());
        System.out.println("Rei branco: " + kingWhite.getSymbol());
        System.out.println("Rei preto: " + kingBlack.getSymbol());
    }
}