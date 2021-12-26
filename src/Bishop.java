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
        if (isOnTheField(toLine, toColumn) && Math.abs(line - toLine) == Math.abs(column - toColumn)
                && Math.abs(line - toLine) > 0) {
            boolean retVal = true;
            int lineDir = ((toLine - line) > 0) ? 1 : -1;
            int columnDir = ((toColumn - column) > 0) ? 1 : -1;
            for (int i = 1; i < (toLine - line) * lineDir; i++) {
                retVal &= cb.board[line + i * lineDir][column + i  * columnDir] == null;
            }
            return retVal && canMoveOrCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }
}
