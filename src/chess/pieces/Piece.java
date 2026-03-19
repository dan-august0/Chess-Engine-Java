package chess.pieces;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.List;

public abstract class Piece {

    protected Color color;
    protected int row;
    protected int col;
    protected boolean hasMoved;

    public Piece(Color color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.hasMoved = false;
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasMoved = true;
    }

    public abstract String getSymbol();

    public abstract List<Position> getLegalMoves(Board board);
}