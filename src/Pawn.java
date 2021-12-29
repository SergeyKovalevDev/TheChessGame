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
            if ((column - toColumn) == 0) {
                if ((toLine - line) == (color.equals(WHITE) ? 1 : -1)) {
                    return cb.board[toLine][toColumn] == null;
                } else if ((toLine - line) == (color.equals(WHITE) ? 2 : -2) && line == (color.equals(WHITE) ? 1 : 6)) {
                    return cb.board[toLine + (color.equals(WHITE) ? - 1 : 1)][toColumn] == null && cb.board[toLine][toColumn] == null;
                }
            } else if (Math.abs(column - toColumn) == 1 && (toLine - line) == (color.equals(WHITE) ? 1 : -1)) {
                return canMoveOrCut(cb, toLine, toColumn);
            }
        }
        return false;
    }

    @Override
    // Метод переопределен т.к. пешка не может ходить по диагонали без рубки и рубить короля
    public boolean canMoveOrCut(ChessBoard cb, int toLine, int toColumn) {
        if (cb.board[toLine][toColumn] != null) {
            return cb.board[toLine][toColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE) &&
                    !cb.board[toLine][toColumn].getSymbol().equals(King.SYMBOL);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}
