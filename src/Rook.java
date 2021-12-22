public class Rook extends ChessPiece {
    public static final String PIECE_SYMBOL = "R";

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return (((line - toLine) == 0 && Math.abs(column - toColumn) > 0) ||
                ((column - toColumn) == 0 && Math.abs(line - toLine) > 0)) &&
                isOnTheField(toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }
}
