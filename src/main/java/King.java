public class King extends ChessPiece implements CheckPositionInterface {
    public static final String SYMBOL = "K";

    public King(String color) {
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
                // Restrict movement only diagonally by 1 step or
                ((Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) == 1 ||
                        // only along the line by 1 step or
                        (line - toLine) == 0 && Math.abs(column - toColumn) == 1 ||
                        // only along the column by 1 step
                        (column - toColumn) == 0 && Math.abs(line - toLine) == 1))) {
            return checkFreePath(chessBoard, line, column, toLine, toColumn) && canMoveOrCut(chessBoard, toLine, toColumn);
        } else {
            return false;
        }
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        return isUnderAttackByHorse(chessBoard, line, column) || isUnderDiagonalOrVerticalAttack(chessBoard, line, column);
    }

    private boolean isUnderAttackByHorse(ChessBoard chessBoard, int line, int column) {
        if (line == 0 && column == 2) {
            int[][] beingCheckedArray = new int[][] {{1, 0}, {2, 1}, {2, 3}, {1, 4}};
            return isUnderAttackFormArray(chessBoard, beingCheckedArray, Horse.SYMBOL);
        } else if (line == 0 && column == 6) {
            int[][] beingCheckedArray = new int[][] {{1, 4}, {2, 5}, {2, 7}};
            return isUnderAttackFormArray(chessBoard, beingCheckedArray, Horse.SYMBOL);
        } else if (line == 7 && column == 2) {
            int[][] beingCheckedArray = new int[][] {{6, 0}, {5, 1}, {5, 3}, {6, 4}};
            return isUnderAttackFormArray(chessBoard, beingCheckedArray, Horse.SYMBOL);
        } else if (line == 7 && column == 6) {
            int[][] beingCheckedArray = new int[][] {{6, 4}, {5, 5}, {5, 7}};
            return isUnderAttackFormArray(chessBoard, beingCheckedArray, Horse.SYMBOL);
        } else {
            return true;
        }
    }

    private boolean isUnderDiagonalOrVerticalAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 1; i < 8 ; i++) {
            int beingCheckedLine = line + (line == 0 ? i : -i);
            int[][] checkingArray = new int[][]
                    {{beingCheckedLine, column - i},
                            {beingCheckedLine, column},
                            {beingCheckedLine, column + i}};
            if (isUnderAttackFormArray(chessBoard, checkingArray, Bishop.SYMBOL) ||
                    isUnderAttackFormArray(chessBoard, checkingArray, Queen.SYMBOL) ||
                    (isUnderAttackFormArray(chessBoard, checkingArray, King.SYMBOL)&& (beingCheckedLine == 1 || beingCheckedLine == 6))) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnderAttackFormArray(ChessBoard chessBoard, int[][] beingCheckedArray, String pieceSymbol) {
        boolean retVal = false;
        for (int[] beingCheckedPos :
                beingCheckedArray) {
            int beingCheckedLine = beingCheckedPos[0];
            int beingCheckedColumn = beingCheckedPos[1];
            retVal |= isUnderAttackFormPosition(chessBoard, beingCheckedLine, beingCheckedColumn, pieceSymbol);
        }
        return retVal;
    }

    private boolean isUnderAttackFormPosition(ChessBoard chessBoard, int beingCheckedLine, int beingCheckedColumn, String pieceSymbol) {
        if (checkPos(beingCheckedLine) && checkPos(beingCheckedColumn)) {
            if (chessBoard.board[beingCheckedLine][beingCheckedColumn] != null) {
                if (chessBoard.board[beingCheckedLine][beingCheckedColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE)) {
                    return chessBoard.board[beingCheckedLine][beingCheckedColumn].getSymbol().equals(pieceSymbol);
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
