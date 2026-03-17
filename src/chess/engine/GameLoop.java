package chess.engine;

import chess.Color;
import chess.Position;
import chess.board.Board;
import chess.pieces.Piece;
import java.util.List;
import java.util.Scanner;

/**
 * Controla o loop principal do jogo de xadrez.
 * Gerencia os turnos, lê os movimentos dos jogadores,
 * valida as jogadas e verifica o fim de jogo.
 */
public class GameLoop {

    // Tabuleiro do jogo
    private Board board;

    // Cor do jogador que deve jogar no turno atual
    private Color currentTurn;

    // Lê a entrada do teclado
    private Scanner scanner;

    /**
     * Construtor do GameLoop.
     * Inicializa o tabuleiro, define as brancas como primeiro turno
     * e prepara o scanner para leitura de input.
     */
    public GameLoop() {
        board = new Board();
        currentTurn = Color.WHITE;
        scanner = new Scanner(System.in);
    }

    /**
     * Inicia e controla o loop principal do jogo.
     * Continua rodando até xeque-mate ou desistência.
     */
    public void start() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║     CHESS ENGINE JAVA      ║");
        System.out.println("║  Digite 'sair' para sair   ║");
        System.out.println("╚════════════════════════════╝");

        while (true) {
            // Imprime o estado atual do tabuleiro
            board.print();
            System.out.println("\nTurno: " + (currentTurn == Color.WHITE ? "BRANCAS" : "PRETAS"));
            System.out.print("Digite o movimento (ex: e2 e4): ");

            String input = scanner.nextLine().trim().toLowerCase();

            // Verifica se o jogador quer sair
            if (input.equals("sair")) {
                System.out.println("Jogo encerrado!");
                break;
            }

            // Valida o formato do input (ex: "e2 e4")
            if (!isValidInput(input)) {
                System.out.println("Formato inválido! Use o formato: e2 e4");
                continue;
            }

            // Converte o texto em posições do tabuleiro
            Position from = parsePosition(input.split(" ")[0]);
            Position to = parsePosition(input.split(" ")[1]);

            if (from == null || to == null) {
                System.out.println("Posição inválida! Use letras de a-h e números de 1-8.");
                continue;
            }

            // Verifica se há uma peça na posição de origem
            Piece piece = board.getPiece(from.getRow(), from.getCol());

            if (piece == null) {
                System.out.println("Não há peça nessa posição!");
                continue;
            }

            // Verifica se a peça pertence ao jogador do turno atual
            if (piece.getColor() != currentTurn) {
                System.out.println("Essa peça não é sua!");
                continue;
            }

            // Verifica se o movimento está na lista de movimentos legais da peça
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

            // Executa o movimento no tabuleiro
            board.movePiece(from, to);

            // Verifica se o Rei inimigo foi capturado
            if (!board.isKingAlive(currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE)) {
                board.print();
                System.out.println("\n♛ XEQUE-MATE! " + (currentTurn == Color.WHITE ? "BRANCAS" : "PRETAS") + " VENCERAM!");
                break;
            }

            // Passa o turno para o outro jogador
            currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }

        scanner.close();
    }

    /**
     * Valida se o formato do input está correto.
     * O formato esperado é duas posições separadas por espaço (ex: "e2 e4").
     * @param input String digitada pelo jogador
     * @return true se o formato for válido
     */
    private boolean isValidInput(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) return false;
        if (parts[0].length() != 2 || parts[1].length() != 2) return false;
        return true;
    }

    /**
     * Converte uma posição em texto (ex: "e4") para um objeto Position.
     * A coluna é uma letra de 'a' a 'h' e a linha é um número de 1 a 8.
     * @param pos String da posição (ex: "e4")
     * @return Objeto Position correspondente ou null se inválido
     */
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