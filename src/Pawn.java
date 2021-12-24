public class Pawn extends ChessPiece {
    public static final String PIECE_SYMBOL = "P";

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        boolean check;
        if (color.equals(WHITE_PIECE)) {
            check = (toLine - line) == 1 && cb.board[toLine][toColumn] == null ||
                    (toLine - line) == 2 && line == 1 && cb.board[toLine - 1][toColumn] == null &&
                            cb.board[toLine][toColumn] == null;
        } else if (color.equals(BLACK_PIECE)){
            check = (toLine - line) == -1 && cb.board[toLine][toColumn] == null ||
                    (toLine - line) == -2 && line == 6 && cb.board[toLine + 1][toColumn] == null &&
                            cb.board[toLine][toColumn] == null;
        } else {
            check = false;
        }
        return check && (column - toColumn) == 0 && isOnTheField(toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }
}
