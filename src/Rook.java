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
        if (isOnTheField(toLine, toColumn)) {
            boolean retVal = true;
            if ((line - toLine) == 0) {
                if ((toColumn - column) > 0) {
                    for (int i = column + 1; i <= toColumn - 1; i++) {
                        retVal &= cb.board[line][i] == null;
                    }
                } else if ((toColumn - column) < 0) {
                    for (int i = column - 1; i >= toColumn + 1; i--) {
                        retVal &= cb.board[line][i] == null;
                    }
                } else {
                    retVal = false;
                }
            } else if ((column - toColumn) == 0) {
                if ((toLine - line) > 0) {
                    for (int i = line + 1; i <= toLine - 1 ; i++) {
                        retVal &= cb.board[i][column] == null;
                    }
                } else if ((toLine - line) < 0) {
                    for (int i = line - 1; i >= toLine + 1; i--) {
                        retVal &= cb.board[i][column] == null;
                    }
                } else {
                    retVal = false;
                }
            } else {
                retVal = false;
            }
            return retVal && canCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    private boolean canCut(ChessBoard cb, int toLine, int toColumn) {
        if (cb.board[toLine][toColumn] != null) {
            return cb.board[toLine][toColumn].color.equals(color.equals(WHITE) ? BLACK : WHITE);
        } else {
            return true;
        }
    }
}
