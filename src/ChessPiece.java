public abstract class ChessPiece {
    public static final String WHITE = "White";
    public static final String BLACK = "Black";

    public String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    public boolean isOnTheField(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    // This method checks if the path to the end position is free, not including the end position
    public boolean checkFreePath(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        int lineDir = Integer.compare(toLine, line);
        int columnDir = Integer.compare (toColumn, column);
        for (int i = 1; i < Math.max(Math.abs(toLine - line), Math.abs(toColumn - column)); i++) {
            if (cb.board[line + i * lineDir][column + i  * columnDir] != null) {
                return false;
            }
        }
        return true;
    }

    // This method checks if the piece can get into the end position
    public boolean canMoveOrCut(ChessBoard cb, int toLine, int toColumn) {
        if (cb.board[toLine][toColumn] != null) {
            return cb.board[toLine][toColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE);
        } else {
            return true;
        }
    }
}
