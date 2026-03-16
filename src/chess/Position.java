package chess;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isValid() {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public String toString() {
        char colLetter = (char) ('a' + col);
        int rowNumber = 8 - row;
        return "" + colLetter + rowNumber;
    }
}