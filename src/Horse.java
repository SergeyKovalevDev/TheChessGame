public class Horse extends ChessPiece {
    public static final String SYMBOL = "H";

    public Horse(String color) {
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
                // Restrict movement only to the angle
                ((Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2) ||
                (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1))) {
            return canMoveOrCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }
}
