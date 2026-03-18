package chess.board;

import chess.Color;
import chess.Position;
import chess.pieces.*;
import java.util.List;

public class Board {

    private Piece[][] squares;

    public Board() {
        squares = new Piece[8][8];
        setup();
    }

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

    public Piece getPiece(int row, int col) {
        return squares[row][col];
    }

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

    public boolean isInCheck(Color color) {
        int kingRow = -1;
        int kingCol = -1;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece instanceof King && piece.getColor() == color) {
                    kingRow = row;
                    kingCol = col;
                }
            }
        }

        Color opponent = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece == null || piece.getColor() != opponent) continue;

                List<Position> moves = piece.getLegalMoves(this);
                for (Position move : moves) {
                    if (move.getRow() == kingRow && move.getCol() == kingCol) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isCheckmate(Color color) {
        if (!isInCheck(color)) return false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece == null || piece.getColor() != color) continue;

                List<Position> moves = piece.getLegalMoves(this);
                for (Position to : moves) {
                    Position from = new Position(row, col);
                    Piece captured = squares[to.getRow()][to.getCol()];

                    movePiece(from, to);
                    boolean stillInCheck = isInCheck(color);
                    undoMove(from, to, piece, captured);

                    if (!stillInCheck) return false;
                }
            }
        }

        return true;
    }

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