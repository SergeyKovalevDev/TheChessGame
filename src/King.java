public class King extends ChessPiece {
    public static final String PIECE_SYMBOL = "K";

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return ((line - toLine) == 0 && Math.abs(column - toColumn) == 1 ||
                (column - toColumn) == 0 && Math.abs(line - toLine) == 1 ||
                Math.abs(line - toLine) == Math.abs(column - toColumn) && Math.abs(line - toLine) == 1) &&
                isOnTheField(toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return PIECE_SYMBOL;
    }

    public boolean isUnderAttack(ChessBoard cb, int line, int column) {
        return isUnderAttackByHorse(cb, line, column) ||
                isUnderVerticalAttack(cb ,line, column) ||
                isUnderFirstDiagonalAttack(cb, line, column) ||
                isUnderSecondDiagonalAttack(cb, line, column);
    }

    private boolean isUnderAttackByHorse (ChessBoard cb, int line, int column) {
            // King("White").isUnderAttack(this, 0, 2)
        if (line == 0 && column == 2) {
            return cb.board[1][0].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[2][1].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[2][3].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[1][4].getSymbol().equals(Horse.PIECE_SYMBOL);
            // King("White").isUnderAttack(this, 0, 6)
        } else if (line == 0 && column == 6) {
            return cb.board[1][4].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[2][5].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[2][7].getSymbol().equals(Horse.PIECE_SYMBOL);
            // King("Black").isUnderAttack(this, 7, 2)
        } else if (line == 7 && column == 2) {
            return cb.board[6][0].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[5][1].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[5][3].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[6][4].getSymbol().equals(Horse.PIECE_SYMBOL);
            // King("Black").isUnderAttack(this, 7, 6)
        } else if (line == 7 && column == 6) {
            return cb.board[6][4].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[5][5].getSymbol().equals(Horse.PIECE_SYMBOL) ||
                    cb.board[5][7].getSymbol().equals(Horse.PIECE_SYMBOL);
        } else {
            return true;
        }
    }

    private boolean isUnderVerticalAttack(ChessBoard cb, int line, int column) {
        if ((line == 0 || line == 7) && (column == 2 || column == 6)) {
            for (int i = 1; i <= 7; i ++) {
                int bcl = line + (line == 0 ? i : -i); // beingCheckedLine
                if (cb.board[bcl][column] != null) {
                    if (cb.board[bcl][column].getColor().equals(line == 0 ? ChessPiece.PIECE_WHITE : ChessPiece.PIECE_BLACK)) {
                        return false;
                    } else {
                        String pieceSymbol = cb.board[bcl][column].getSymbol();
                        return pieceSymbol.equals(Rook.PIECE_SYMBOL) ||
                                pieceSymbol.equals(Queen.PIECE_SYMBOL) ||
                                (pieceSymbol.equals(King.PIECE_SYMBOL) && bcl == 1);
                    }
                }
            }
        }
        return true;
    }

    private boolean isUnderFirstDiagonalAttack(ChessBoard cb, int line, int column) {
        if ((line == 0 || line == 7) && (column == 2 || column == 6)) {
            for (int i = 1; i <= column ; i++) {
                int bcl = line + (line == 0 ? i : -i); // beingCheckedLine
                int bcc = column - i; // beingCheckedColumn
                if (cb.board[bcl][bcc] != null) {
                    if (cb.board[bcl][bcc].getColor().equals(line == 0 ? ChessPiece.PIECE_WHITE : ChessPiece.PIECE_BLACK)) {
                        return false;
                    } else {
                        String pieceSymbol = cb.board[bcl][bcc].getSymbol();
                        return pieceSymbol.equals(Bishop.PIECE_SYMBOL) ||
                                pieceSymbol.equals(Queen.PIECE_SYMBOL) ||
                                (pieceSymbol.equals(King.PIECE_SYMBOL) && bcl == 1);
                    }
                }
            }
        }
        return true;
    }

    private boolean isUnderSecondDiagonalAttack(ChessBoard cb, int line, int column) {
        if ((line == 0 || line == 7) && (column == 2 || column == 6)) {
            for (int i = 1; i <= (column == 2 ? 5 : 1) ; i++) {
                int bcl = line + (line == 0 ? i : -i); // beingCheckedLine
                int bcc = column + i; // beingCheckedColumn
                if (cb.board[bcl][bcc] != null) {
                    if (cb.board[bcl][bcc].getColor().equals(line == 0 ? ChessPiece.PIECE_WHITE : ChessPiece.PIECE_BLACK)) {
                        return false;
                    } else {
                        String pieceSymbol = cb.board[bcl][bcc].getSymbol();
                        return pieceSymbol.equals(Bishop.PIECE_SYMBOL) ||
                                pieceSymbol.equals(Queen.PIECE_SYMBOL) ||
                                (pieceSymbol.equals(King.PIECE_SYMBOL) && bcl == 1);
                    }
                }
            }
        }
        return true;
    }
}
