public class Bishop extends ChessPiece {
    public static final String SYMBOL = "B";

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn) &&
                Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) > 0) { // Ограничиваем движение только по диагонали
            return checkFreePath(cb, line, column, toLine, toColumn);
/*
            int lineDir = Integer.compare(toLine, line);
            int columnDir = Integer.compare (toColumn, column);
            boolean retVal = true;
            for (int i = 1; i < Math.max(Math.abs(toLine - line), Math.abs(toColumn - column)); i++) {
                retVal &= cb.board[line + i * lineDir][column + i  * columnDir] == null;
            }
            return retVal && canMoveOrCut(cb, toLine, toColumn);
*/
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}
