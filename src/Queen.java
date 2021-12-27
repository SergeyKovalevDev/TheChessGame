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
                ((Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) > 0 || // Ограничиваем движение только по диагонали или
                        (line - toLine) == 0 && Math.abs(column - toColumn) > 0 ||                         // только вдоль линии или
                        (column - toColumn) == 0 && Math.abs(line - toLine) > 0))) {                       // только вдоль столбца
            int lineDir = Integer.compare(toLine, line);
            int columnDir = Integer.compare (toColumn, column);
            boolean retVal = true;
            for (int i = 1; i < Math.max(Math.abs(toLine - line), Math.abs(toColumn - column)); i++) {
                retVal &= cb.board[line + i * lineDir][column + i  * columnDir] == null;
            }
            return retVal && canMoveOrCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}
