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
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn) &&
                // Restrict movement only diagonally
                Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) > 0) {
            return checkFreePath(chessBoard, line, column, toLine, toColumn) && canMoveOrCut(chessBoard, toLine, toColumn);
        } else {
            return false;
        }
    }
}
