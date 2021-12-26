public class Horse extends ChessPiece {
    public static final String PIECE_SYMBOL = "H";

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn)) {
            return ((Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2) ||
                    (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1)) &&
                    canCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }
}
