public class King extends ChessPiece {
    public static final String SYMBOL = "K";

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cb, int line, int column, int toLine, int toColumn) {
        if (isOnTheField(toLine, toColumn) &&
                // Ограничиваем движение только по диагонали на 1 шаг или
                ((Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) == 1 ||
                        // только вдоль линии на 1 шаг или
                        (line - toLine) == 0 && Math.abs(column - toColumn) == 1 ||
                        // только вдоль столбца на 1 шаг
                        (column - toColumn) == 0 && Math.abs(line - toLine) == 1))) {
            return checkFreePath(cb, line, column, toLine, toColumn) && canMoveOrCut(cb, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    public boolean isUnderAttack(ChessBoard cb, int line, int column) {
        return isUnderAttackByHorseA(cb, line, column) || isUnderDiagonalOrVerticalAttack(cb, line, column);
    }

    private boolean isUnderAttackByHorseA(ChessBoard cb, int line, int column) {
        if (line == 0 && column == 2) {
            int[][] beingCheckedArray = new int[][] {{1, 0}, {2, 1}, {2, 3}, {1, 4}};
            return isUnderAttackFormArray(cb, beingCheckedArray, Horse.SYMBOL);
        } else if (line == 0 && column == 6) {
            int[][] beingCheckedArray = new int[][] {{1, 4}, {2, 5}, {2, 7}};
            return isUnderAttackFormArray(cb, beingCheckedArray, Horse.SYMBOL);
        } else if (line == 7 && column == 2) {
            int[][] beingCheckedArray = new int[][] {{6, 0}, {5, 1}, {5, 3}, {6, 4}};
            return isUnderAttackFormArray(cb, beingCheckedArray, Horse.SYMBOL);
        } else if (line == 7 && column == 6) {
            int[][] beingCheckedArray = new int[][] {{6, 4}, {5, 5}, {5, 7}};
            return isUnderAttackFormArray(cb, beingCheckedArray, Horse.SYMBOL);
        } else {
            return true;
        }
    }

    private boolean isUnderDiagonalOrVerticalAttack(ChessBoard cb, int line, int column) {
        for (int i = 1; i < 8 ; i++) {
            int beingCheckedLine = line + (line == 0 ? i : -i);
            int[][] checkingArray = new int[][]
                    {{beingCheckedLine, column - i},
                            {beingCheckedLine, column},
                            {beingCheckedLine, column + i}};
            if (isUnderAttackFormArray(cb, checkingArray, Bishop.SYMBOL) ||
                    isUnderAttackFormArray(cb, checkingArray, Queen.SYMBOL) ||
                    (isUnderAttackFormArray(cb, checkingArray, King.SYMBOL)&&
                            (beingCheckedLine == 1 || beingCheckedLine == 6))) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnderAttackFormArray(ChessBoard cb, int[][] beingCheckedArray, String pieceSymbol) {
        boolean retVal = false;
        for (int[] beingCheckedPos :
                beingCheckedArray) {
            int beingCheckedLine = beingCheckedPos[0];
            int beingCheckedColumn = beingCheckedPos[1];
            retVal |= isUnderAttackFormPosition(cb, beingCheckedLine, beingCheckedColumn, pieceSymbol);
        }
        return retVal;
    }

    private boolean isUnderAttackFormPosition(ChessBoard cb, int beingCheckedLine, int beingCheckedColumn, String pieceSymbol) {
        if (isOnTheField(beingCheckedLine, beingCheckedColumn)) {
            if (cb.board[beingCheckedLine][beingCheckedColumn] != null) {
                if (cb.board[beingCheckedLine][beingCheckedColumn].getColor().equals(color.equals(WHITE) ? BLACK : WHITE)) {
                    return cb.board[beingCheckedLine][beingCheckedColumn].getSymbol().equals(pieceSymbol);
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
