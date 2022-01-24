public class Pawn extends ChessPiece implements CheckPositionInterface {
    public static final String SYMBOL = "P";

    public Pawn(String color) {
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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(toLine) && checkPos(toColumn)) {
            if ((column - toColumn) == 0) {
                if ((toLine - line) == (color.equals(WHITE) ? 1 : -1) && ((color.equals(WHITE) ? line > 0 : line < 7))) {
                    return chessBoard.board[toLine][toColumn] == null;
                } else if ((toLine - line) == (color.equals(WHITE) ? 2 : -2) && line == (color.equals(WHITE) ? 1 : 6)) {
                    return chessBoard.board[toLine + (color.equals(WHITE) ? - 1 : 1)][toColumn] == null && chessBoard.board[toLine][toColumn] == null;
                } else {
                    return false;
                }
            } else if (Math.abs(column - toColumn) == 1 && (toLine - line) == (color.equals(WHITE) ? 1 : -1) &&
                    ((color.equals(WHITE) ? line > 0 : line < 7))) {
                if (chessBoard.board[toLine][toColumn] != null) {
                    return chessBoard.board[toLine][toColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE) &&
                            !chessBoard.board[toLine][toColumn].getSymbol().equals(King.SYMBOL);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
