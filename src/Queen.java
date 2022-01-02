public class Queen extends ChessPiece {
    public static final String SYMBOL = "Q";

    public Queen(String color) {
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
        if (isOnTheField(toLine, toColumn) &&
                // Restrict movement only diagonally or
                ((Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) > 0 ||
                        // only along the line or
                        (line - toLine) == 0 && Math.abs(column - toColumn) > 0 ||
                        // only along the column
                        (column - toColumn) == 0 && Math.abs(line - toLine) > 0))) {
            return checkFreePath(cb, line, column, toLine, toColumn) && canMoveOrCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }
}
