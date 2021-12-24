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
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn)) {
            if (color.equals(WHITE)) {
                if ((column - toColumn) == 0) {
                    if ((toLine - line) == 1) {
                        return cb.board[toLine][toColumn] == null;
                    } else if ((toLine - line) == 2 && line == 1) {
                        return cb.board[toLine - 1][toColumn] == null && cb.board[toLine][toColumn] == null;
                    }
                } else if (Math.abs(column - toColumn) == 1 && (toLine - line) == 1 && cb.board[toLine][toColumn] != null) {
                    return cb.board[toLine][toColumn].getColor().equals(BLACK) &&
                            !cb.board[toLine][toColumn].getSymbol().equals(King.SYMBOL);
                }
            } else if (color.equals(BLACK)) {
                if ((column - toColumn) == 0) {
                    if ((toLine - line) == -1) {
                        return cb.board[toLine][toColumn] == null;
                    } else if ((toLine - line) == -2 && line == 6) {
                        return cb.board[toLine + 1][toColumn] == null && cb.board[toLine][toColumn] == null;
                    }
                } else if (Math.abs(column - toColumn) == 1 && (toLine - line) == -1 && cb.board[toLine][toColumn] != null) {
                    return cb.board[toLine][toColumn].getColor().equals(WHITE) &&
                            !cb.board[toLine][toColumn].getSymbol().equals(King.SYMBOL);
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}
