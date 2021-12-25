public class Bishop extends ChessPiece {
    public static final String PIECE_SYMBOL = "B";

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        return Math.abs(line - toLine) == Math.abs(column - toColumn) &&
                Math.abs(line - toLine) > 0 &&
                isOnTheField(toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }
}
