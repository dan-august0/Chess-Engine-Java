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
    private AI ai;
    private boolean vsAI;

    public GameLoop() {
        board = new Board();
        currentTurn = Color.WHITE;
        scanner = new Scanner(System.in);
    }

    public void start() {
        showMainMenu();

        while (true) {
            board.print();
            System.out.println("\nTurno: " + (currentTurn == Color.WHITE ? "BRANCAS" : "PRETAS"));

            if (board.isInCheck(currentTurn)) {
                System.out.println("⚠️  XEQUE! Seu Rei está em perigo!");
            }

            if (board.isCheckmate(currentTurn)) {
                Color winner = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
                System.out.println("\n♛ XEQUE-MATE! " + (winner == Color.WHITE ? "BRANCAS" : "PRETAS") + " VENCERAM!");
                break;
            }

            if (vsAI && currentTurn == Color.BLACK) {
                System.out.println("IA está pensando...");
                ai.makeMove(board);

                if (board.isCheckmate(Color.WHITE)) {
                    board.print();
                    System.out.println("\n♛ XEQUE-MATE! PRETAS VENCERAM!");
                    break;
                }

                currentTurn = Color.WHITE;
                continue;
            }

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

            // Simula o movimento e verifica se deixa o Rei em xeque
            Piece captured = board.getPiece(to.getRow(), to.getCol());
            board.movePiece(from, to);

            if (board.isInCheck(currentTurn)) {
                board.undoMove(from, to, piece, captured);
                System.out.println("Movimento inválido! Seu Rei ficaria em xeque!");
                continue;
            }

            if (board.isCheckmate(currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE)) {
                board.print();
                System.out.println("\n♛ XEQUE-MATE! " + (currentTurn == Color.WHITE ? "BRANCAS" : "PRETAS") + " VENCERAM!");
                break;
            }

            currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }

        scanner.close();
    }

    private void showMainMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║     CHESS ENGINE JAVA      ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║  1. Jogador vs Jogador     ║");
        System.out.println("║  2. Jogador vs IA          ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("Escolha o modo: ");

        String choice = scanner.nextLine().trim();

        if (choice.equals("2")) {
            vsAI = true;
            showDifficultyMenu();
        } else {
            vsAI = false;
        }
    }

    private void showDifficultyMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║   Escolha a dificuldade    ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║  1. Fácil                  ║");
        System.out.println("║  2. Médio                  ║");
        System.out.println("║  3. Difícil                ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("Escolha: ");

        String choice = scanner.nextLine().trim();
        int depth;

        switch (choice) {
            case "1": depth = 1; break;
            case "3": depth = 3; break;
            default:  depth = 2; break;
        }

        ai = new AI(depth, Color.BLACK);
        System.out.println("Dificuldade selecionada! Você joga com as BRANCAS.");
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