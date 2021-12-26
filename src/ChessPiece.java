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

    public boolean canMoveOrCut(ChessBoard cb, int toLine, int toColumn) {
        if (cb.board[toLine][toColumn] != null) {
            return cb.board[toLine][toColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE);
        } else {
            return true;
        }
    }
}
