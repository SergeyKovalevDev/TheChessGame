public class Rook extends ChessPiece {
    public static final String SYMBOL = "R";

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn) &&
                // Ограничиваем движение только вдоль линии или
                ((line - toLine) == 0 && Math.abs(column - toColumn) > 0 ||
                        // только вдоль столбца
                        (column - toColumn) == 0 && Math.abs(line - toLine) > 0)) {
            return checkFreePath(cb, line, column, toLine, toColumn) && canMoveOrCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}
