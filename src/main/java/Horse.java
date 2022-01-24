public class Horse extends ChessPiece implements CheckPositionInterface {
    public static final String SYMBOL = "H";

    public Horse(String color) {
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
        if (checkPos(toLine) && checkPos(toColumn) &&
                // Restrict movement only to the angle
                ((Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2) ||
                (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1))) {
            return canMoveOrCut(chessBoard, toLine, toColumn);
        } else {
            return false;
        }
    }
}
