public class Queen extends ChessPiece {
    public static final String PIECE_SYMBOL = "Q";

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn)) {
            return ((line - toLine) == 0 && Math.abs(column - toColumn) > 0 ||
                    (column - toColumn) == 0 && Math.abs(line - toLine) > 0 ||
                    Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) > 0);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }
}
