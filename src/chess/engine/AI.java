package chess.engine;

import chess.Color;
import chess.Position;
import chess.board.Board;
import chess.pieces.*;
import java.util.List;

public class AI {

    private int depth;
    private Color aiColor;

    public AI(int depth, Color aiColor) {
        this.depth = depth;
        this.aiColor = aiColor;
    }

    public void makeMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        Position bestFrom = null;
        Position bestTo = null;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);

                if (piece == null || piece.getColor() != aiColor) continue;

                List<Position> moves = piece.getLegalMoves(board);

                for (Position to : moves) {
                    Position from = new Position(row, col);

                    Piece captured = board.getPiece(to.getRow(), to.getCol());
                    board.movePiece(from, to);

                    int score = minimax(board, depth - 1, false);

                    board.undoMove(from, to, piece, captured);

                    if (score > bestScore) {
                        bestScore = score;
                        bestFrom = from;
                        bestTo = to;
                    }
                }
            }
        }

        if (bestFrom != null && bestTo != null) {
            board.movePiece(bestFrom, bestTo);
            System.out.println("IA jogou: " + bestFrom + " → " + bestTo);
        }
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        if (depth == 0) {
            return evaluate(board);
        }

        Color currentColor = isMaximizing ? aiColor : getOpponent(aiColor);

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Piece piece = board.getPiece(row, col);

                    if (piece == null || piece.getColor() != currentColor) continue;

                    for (Position to : piece.getLegalMoves(board)) {
                        Position from = new Position(row, col);
                        Piece captured = board.getPiece(to.getRow(), to.getCol());

                        board.movePiece(from, to);
                        best = Math.max(best, minimax(board, depth - 1, false));
                        board.undoMove(from, to, piece, captured);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Piece piece = board.getPiece(row, col);

                    if (piece == null || piece.getColor() != currentColor) continue;

                    for (Position to : piece.getLegalMoves(board)) {
                        Position from = new Position(row, col);
                        Piece captured = board.getPiece(to.getRow(), to.getCol());

                        board.movePiece(from, to);
                        best = Math.min(best, minimax(board, depth - 1, true));
                        board.undoMove(from, to, piece, captured);
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(Board board) {
        int score = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece == null) continue;

                int value = getPieceValue(piece);

                if (piece.getColor() == aiColor) {
                    score += value;
                } else {
                    score -= value;
                }
            }
        }

        return score;
    }

    private int getPieceValue(Piece piece) {
        if (piece instanceof Pawn)   return 1;
        if (piece instanceof Knight) return 3;
        if (piece instanceof Bishop) return 3;
        if (piece instanceof Rook)   return 5;
        if (piece instanceof Queen)  return 9;
        if (piece instanceof King)   return 1000;
        return 0;
    }

    private Color getOpponent(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}