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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean check;
        if (color.equals("White")) {
            check = (toLine - line) == 1 || ((toLine - line) == 2 && line == 1);
        } else if (color.equals("Black")){
            check = (toLine - line) == -1 || ((toLine - line) == -2 && line == 6);
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
