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
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn) &&
                // Restrict movement only along the line or
                ((line - toLine) == 0 && Math.abs(column - toColumn) > 0 ||
                        // only along the column
                        (column - toColumn) == 0 && Math.abs(line - toLine) > 0)) {
            return checkFreePath(chessBoard, line, column, toLine, toColumn) && canMoveOrCut(chessBoard, toLine, toColumn);
        } else {
            return false;
        }
    }
}
