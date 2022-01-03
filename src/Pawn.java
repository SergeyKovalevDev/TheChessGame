public class Pawn extends ChessPiece {
    public static final String SYMBOL = "P";

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn)) {
            if ((column - toColumn) == 0) {
                if ((toLine - line) == (color.equals(WHITE) ? 1 : -1) && ((color.equals(WHITE) ? line > 0 : line < 7))) {
                    return cb.board[toLine][toColumn] == null;
                } else if ((toLine - line) == (color.equals(WHITE) ? 2 : -2) && line == (color.equals(WHITE) ? 1 : 6)) {
                    return cb.board[toLine + (color.equals(WHITE) ? - 1 : 1)][toColumn] == null && cb.board[toLine][toColumn] == null;
                } else {
                    return false;
                }
            } else if (Math.abs(column - toColumn) == 1 && (toLine - line) == (color.equals(WHITE) ? 1 : -1) &&
                    ((color.equals(WHITE) ? line > 0 : line < 7))) {
                if (cb.board[toLine][toColumn] != null) {
                    return cb.board[toLine][toColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE) &&
                            !cb.board[toLine][toColumn].getSymbol().equals(King.SYMBOL);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
