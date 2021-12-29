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
            return checkFreePath(cb, line, column, toLine, toColumn);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    public boolean isUnderAttack(ChessBoard cb, int line, int column) {
        return isUnderAttackByHorse(cb, line, column) ||
                isUnderVerticalAttack(cb ,line, column) ||
                isUnderFirstDiagonalAttack(cb, line, column) ||
                isUnderSecondDiagonalAttack(cb, line, column);
    }

    private boolean isUnderAttackByHorse (ChessBoard cb, int line, int column) {
        boolean retVal = false;
        if (line == 0 && column == 2) {// King("White").isUnderAttack(this, 0, 2)
            if (cb.board[1][0] != null) retVal = cb.board[1][0].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[2][1] != null) retVal |= cb.board[2][1].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[2][3] != null) retVal |= cb.board[2][3].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[1][4] != null) retVal |= cb.board[1][4].getSymbol().equals(Horse.PIECE_SYMBOL);
            return retVal;
        } else if (line == 0 && column == 6) {// King("White").isUnderAttack(this, 0, 6)
            if (cb.board[1][4] != null) retVal = cb.board[1][4].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[2][5] != null) retVal |= cb.board[2][5].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[2][7] != null) retVal |= cb.board[2][7].getSymbol().equals(Horse.PIECE_SYMBOL);
            return retVal;
        } else if (line == 7 && column == 2) {// King("Black").isUnderAttack(this, 7, 2)
            if (cb.board[6][0] != null) retVal = cb.board[6][0].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[5][1] != null) retVal |= cb.board[5][1].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[5][3] != null) retVal |= cb.board[5][3].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[6][4] != null) retVal |= cb.board[6][4].getSymbol().equals(Horse.PIECE_SYMBOL);
            return retVal;
        } else if (line == 7 && column == 6) {// King("Black").isUnderAttack(this, 7, 6)
            if (cb.board[6][4] != null) retVal = cb.board[6][4].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[5][5] != null) retVal |= cb.board[5][5].getSymbol().equals(Horse.PIECE_SYMBOL);
            if (cb.board[5][7] != null) retVal |= cb.board[5][7].getSymbol().equals(Horse.PIECE_SYMBOL);
            return retVal;
        } else {
            return true;
        }
    }

    private boolean isUnderVerticalAttack(ChessBoard cb, int line, int column) {
        if ((line == 0 || line == 7) && (column == 2 || column == 6)) {
            for (int i = 1; i <= 7; i ++) {
                int bcl = line + (line == 0 ? i : -i); // beingCheckedLine
                if (cb.board[bcl][column] != null) {
                    if (cb.board[bcl][column].getColor().equals(line == 0 ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        return false;
                    } else {
                        String pieceSymbol = cb.board[bcl][column].getSymbol();
                        return pieceSymbol.equals(Rook.SYMBOL) ||
                                pieceSymbol.equals(Queen.SYMBOL) ||
                                (pieceSymbol.equals(King.SYMBOL) && bcl == 1);
                    }
                }
            }
            return false;
        }
        return true;
    }

    private boolean isUnderFirstDiagonalAttack(ChessBoard cb, int line, int column) {
        if ((line == 0 || line == 7) && (column == 2 || column == 6)) {
            for (int i = 1; i <= column ; i++) {
                int bcl = line + (line == 0 ? i : -i); // beingCheckedLine
                int bcc = column - i; // beingCheckedColumn
                if (cb.board[bcl][bcc] != null) {
                    if (cb.board[bcl][bcc].getColor().equals(line == 0 ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        return false;
                    } else {
                        String pieceSymbol = cb.board[bcl][bcc].getSymbol();
                        return pieceSymbol.equals(Bishop.SYMBOL) ||
                                pieceSymbol.equals(Queen.SYMBOL) ||
                                (pieceSymbol.equals(King.SYMBOL) && bcl == 1);
                    }
                }
            }
            return false;
        }
        return true;
    }

    private boolean isUnderSecondDiagonalAttack(ChessBoard cb, int line, int column) {
        if ((line == 0 || line == 7) && (column == 2 || column == 6)) {
            for (int i = 1; i <= (column == 2 ? 5 : 1) ; i++) {
                int bcl = line + (line == 0 ? i : -i); // beingCheckedLine
                int bcc = column + i; // beingCheckedColumn
                if (cb.board[bcl][bcc] != null) {
                    if (cb.board[bcl][bcc].getColor().equals(line == 0 ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        return false;
                    } else {
                        String pieceSymbol = cb.board[bcl][bcc].getSymbol();
                        return pieceSymbol.equals(Bishop.SYMBOL) ||
                                pieceSymbol.equals(Queen.SYMBOL) ||
                                (pieceSymbol.equals(King.SYMBOL) && bcl == 1);
                    }
                }
            }
            return false;
        }
        return true;
    }
}
