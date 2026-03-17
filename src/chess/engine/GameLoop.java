package chess.engine;

import chess.Color;
import chess.Position;
import chess.board.Board;
import chess.pieces.Piece;
import java.util.List;
import java.util.Scanner;

public class GameLoop {

    private Board board;
    private Color currentTurn;
    private Scanner scanner;

    public GameLoop() {
        board = new Board();
        currentTurn = Color.WHITE;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║     CHESS ENGINE JAVA      ║");
        System.out.println("║  Digite 'sair' para sair   ║");
        System.out.println("╚════════════════════════════╝");

        while (true) {
            board.print();
            System.out.println("\nTurno: " + (currentTurn == Color.WHITE ? "BRANCAS" : "PRETAS"));
            System.out.print("Digite o movimento (ex: e2 e4): ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("sair")) {
                System.out.println("Jogo encerrado!");
                break;
            }

            if (!isValidInput(input)) {
                System.out.println("Formato inválido! Use o formato: e2 e4");
                continue;
            }

            Position from = parsePosition(input.split(" ")[0]);
            Position to = parsePosition(input.split(" ")[1]);

            if (from == null || to == null) {
                System.out.println("Posição inválida! Use letras de a-h e números de 1-8.");
                continue;
            }

            Piece piece = board.getPiece(from.getRow(), from.getCol());

            if (piece == null) {
                System.out.println("Não há peça nessa posição!");
                continue;
            }

            if (piece.getColor() != currentTurn) {
                System.out.println("Essa peça não é sua!");
                continue;
            }

            List<Position> legalMoves = piece.getLegalMoves(board);
            boolean isLegal = false;

            for (Position move : legalMoves) {
                if (move.getRow() == to.getRow() && move.getCol() == to.getCol()) {
                    isLegal = true;
                    break;
                }
            }

            if (!isLegal) {
                System.out.println("Movimento inválido para essa peça!");
                continue;
            }

            board.movePiece(from, to);

            if (!board.isKingAlive(currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE)) {
                board.print();
                System.out.println("\n♛ XEQUE-MATE! " + (currentTurn == Color.WHITE ? "BRANCAS" : "PRETAS") + " VENCERAM!");
                break;
            }

            currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }

        scanner.close();
    }

    private boolean isValidInput(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) return false;
        if (parts[0].length() != 2 || parts[1].length() != 2) return false;
        return true;
    }

    private Position parsePosition(String pos) {
        char colChar = pos.charAt(0);
        char rowChar = pos.charAt(1);

        if (colChar < 'a' || colChar > 'h') return null;
        if (rowChar < '1' || rowChar > '8') return null;

        int col = colChar - 'a';
        int row = 8 - (rowChar - '0');

        return new Position(row, col);
    }
}