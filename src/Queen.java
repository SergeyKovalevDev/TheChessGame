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
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn) &&
                // Ограничиваем движение только по диагонали или
                ((Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) > 0 ||
                        // только вдоль линии или
                        (line - toLine) == 0 && Math.abs(column - toColumn) > 0 ||
                        // только вдоль столбца
                        (column - toColumn) == 0 && Math.abs(line - toLine) > 0))) {
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
