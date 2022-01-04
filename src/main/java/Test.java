public class Test {
    public static final String WHITE = ChessPiece.WHITE;
    public static final String BLACK = ChessPiece.BLACK;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final ChessPiece[] piecesArr = new ChessPiece[]{
            new Pawn(WHITE), new Pawn(BLACK),
            new Rook(WHITE), new Rook(BLACK),
            new Horse(WHITE), new Horse(BLACK),
            new Bishop(WHITE), new Bishop(BLACK),
            new Queen(WHITE), new Queen(BLACK),
            new King(WHITE), new King(BLACK)};

    public static void main(String[] args) {
        // Проверка движения пешки
        pawnMovingAndCuttingTest();
        // Проверка движения ладьи
        movingAndCuttingTest(new Rook(WHITE),
                new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}, 7, "ладьи");
        // Проверка движения коня
        movingAndCuttingTest(new Horse(WHITE),
                new int[][] {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}}, 2, "коня");
        // Проверка движения слона
        movingAndCuttingTest(new Bishop(WHITE),
                new int[][] {{1, -1}, {1, 1}, {-1, -1}, {-1, 1}}, 7, "слона");
        // Проверка движения королевы
        movingAndCuttingTest(new Queen(WHITE),
                new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}, 7, "королевы");
        // Проверка движения короля
        movingAndCuttingTest(new King(WHITE),
                new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}, 1, "короля");

    }

    private static void pawnMovingAndCuttingTest() {
        try {
            boolean check = true;
            for (String color : new String[]{WHITE, BLACK}) {
                check = pawnMovingTest(color);
                check &= pawnCuttingTest(color);
            }
            printResultString("Проверка движения пешки", check);
        } catch (Exception e) {
            printErrorString("Исключение при проверке движения пешки", e);
        }
    }

    private static boolean pawnMovingTest(String color) {
        boolean check = true;
        // Проверка хода пешки на один шаг вперед без помехи впереди
        ChessBoard chessboard = new ChessBoard(WHITE);
        for (int line = 1; line < 7; line++) {
            int aLine = color.equals(WHITE) ? line : 7 - line;
            int bLine = color.equals(WHITE) ? line + 1 : 6 - line;
            check = pawnMovingTestA(color, check, chessboard, aLine, bLine);
        }

        // Проверка хода пешки на два шага вперед без помехи впереди
        chessboard = new ChessBoard(WHITE);
        for (int line = 1; line < 2; line++) {
            int aLine = color.equals(WHITE) ? line : 7 - line;
            int cLine = color.equals(WHITE) ? line + 2 : 5 - line;
            check = pawnMovingTestA(color, check, chessboard, aLine, cLine);
        }

        // Проверка NOT хода пешки на один шаг вперед с помехой впереди
        chessboard = new ChessBoard(WHITE);
        for (int line = 1; line < 7; line++) {
            int aLine = color.equals(WHITE) ? line : 7 - line;
            int bLine = color.equals(WHITE) ? line + 1 : 6 - line;
            check = pawnMovingTestB(color, check, chessboard, aLine, bLine);
        }

        // Проверка NOT хода пешки на два шага вперед с помехой на один шаг впереди
        chessboard = new ChessBoard(WHITE);
        for (int line = 1; line < 2; line++) {
            int aLine = color.equals(WHITE) ? line : 7 - line;
            int bLine = color.equals(WHITE) ? line + 1 : 6 - line;
            int cLine = color.equals(WHITE) ? line + 2 : 5 - line;
            check = pawnMovingTestC(color, check, chessboard, aLine, bLine, cLine);
        }

        // Проверка NOT хода пешки на два шага вперед с помехой на два шага вперед
        chessboard = new ChessBoard(WHITE);
        for (int line = 1; line < 2; line++) {
            int aLine = color.equals(WHITE) ? line : 7 - line;
            int cLine = color.equals(WHITE) ? line + 2 : 5 - line;
            check = pawnMovingTestB(color, check, chessboard, aLine, cLine);
        }

        // Проверка NOT хода пешки на один шаг во всех направлениях кроме вперед и на свое место
        for (int i = 1; i < 7; i++) {
            for (int[] shift : new int[][]{{-i, -i}, {0, -i}, {i, -i}, {i, i}, {0, i}, {-i, i}, {-i, 0}, {0, 0}}) {
                for (int line = 0; line < 8; line++) {
                    for (int column = 0; column < 8; column++) {
                        chessboard = new ChessBoard(WHITE);
                        chessboard.board[line][column] = new Pawn(color);
                        chessboard.nowPlayer = color;
                        check &= !chessboard.moveToPosition(line, column,
                                line + (color.equals(WHITE) ? shift[0] : -shift[0]), column + shift[1]);
                    }
                }
            }
        }
        return check;
    }

    private static boolean pawnMovingTestA(String color, boolean check, ChessBoard chessboard, int aLine, int bLine) {
        for (int column = 0; column < 8; column++) {
            chessboard.board[aLine][column] = new Pawn(color);
        }
        for (int column = 0; column < 8; column++) {
            chessboard.nowPlayer = color;
            check &= chessboard.moveToPosition(aLine, column, bLine, column);
        }
        return check;
    }

    private static boolean pawnMovingTestB(String color, boolean check, ChessBoard chessboard, int bLine, int cLine) {
        check = pawnMovingTestC(color, check, chessboard, bLine, cLine, cLine);
        return check;
    }

    private static boolean pawnMovingTestC(String color, boolean check, ChessBoard chessboard, int aLine, int bLine, int cLine) {
        for (int column = 0; column < 8; column++) {
            chessboard.board[aLine][column] = new Pawn(color);
            chessboard.board[bLine][column] = new Pawn(color);
        }
        for (int column = 0; column < 8; column++) {
            chessboard.nowPlayer = color;
            check &= !chessboard.moveToPosition(aLine, column, cLine, column);
        }
        return check;
    }

    private static boolean pawnCuttingTest(String color) {
        boolean check = true;
        // Проверка рубки пешкой фигуры противоположного цвета кроме короля
        String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;
        for (ChessPiece piece :
                new ChessPiece[]{
                        new Pawn(oppositeColor),
                        new Rook(oppositeColor),
                        new Horse(oppositeColor),
                        new Bishop(oppositeColor),
                        new Queen(oppositeColor)}) {
            check &= pawnCuttingTestA(check, color, piece);
        }

        // Проверка NOT рубки пешкой короля противоположного цвета
        check &= !pawnCuttingTestA(check, color, new King(oppositeColor));

        // Проверка NOT рубки пешкой фигуры своего цвета
        for (ChessPiece piece : new ChessPiece[]{
                new Pawn(color),
                new Rook(color),
                new Horse(color),
                new Bishop(color),
                new Queen(color),
                new King(color)}) {
            check &= !pawnCuttingTestA(check, color, piece);
        }

        // Проверка NOT рубки пешкой назад
        for (ChessPiece piece :
                new ChessPiece[]{
                        new Pawn(oppositeColor),
                        new Rook(oppositeColor),
                        new Horse(oppositeColor),
                        new Bishop(oppositeColor),
                        new Queen(oppositeColor)}) {
            check &= !pawnCuttingTestB(check, color, piece);
        }
        return check;
    }

    private static boolean pawnCuttingTestA(boolean check, String color, ChessPiece piece) {
        ChessBoard chessboard;
        for (int shift :
                new int[] {1, -1}) {
            for (int line = 1; line < 7; line++) {
                for (int column = 0; column < 8; column++) {
                    chessboard = new ChessBoard(WHITE);
                    chessboard.board[line][column] = new Pawn(color);
                    int toLine = line + (color.equals(WHITE) ? 1 : -1);
                    int toColumn = column + shift;
                    if (toColumn <= 7 && toColumn >= 0) {
                        chessboard.board[toLine][toColumn] = piece;
                        chessboard.nowPlayer = color;
                        check &= chessboard.moveToPosition(line, column, toLine, toColumn);
                    }
                }
            }
        }
        return check;
    }

    private static boolean pawnCuttingTestB(boolean check, String color, ChessPiece piece) {
        ChessBoard chessboard;
        for (int shift :
                new int[] {1, -1}) {
            for (int line = 1; line < 7; line++) {
                for (int column = 0; column < 8; column++) {
                    chessboard = new ChessBoard(WHITE);
                    chessboard.board[line][column] = new Pawn(color);
                    int toLine = line + (color.equals(WHITE) ? -1 : 1);
                    int toColumn = column + shift;
                    if (toColumn <= 7 && toColumn >= 0) {
                        chessboard.board[toLine][toColumn] = piece;
                        chessboard.nowPlayer = color;
                        check &= chessboard.moveToPosition(line, column, toLine, toColumn);
                    }
                }
            }
        }
        return check;
    }

    private static void movingAndCuttingTest(ChessPiece testingPiece, int[][] acceptableDirectionsArr,
                                             int maxDistance, String printingPieceName) {

        try {
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                testingPiece.color = color;
                // Проверка движения по пустому полю
                check &= movingTest(acceptableDirectionsArr, testingPiece, maxDistance);
                // Проверка перепрыгивания через фигуры
                check &= (testingPiece.getSymbol().equals(Horse.SYMBOL)) ?
                        horseJumpOverTest(acceptableDirectionsArr, testingPiece) :
                        jumpOverTest(acceptableDirectionsArr, testingPiece, maxDistance);
                // Проверка рубки фигур
                check &= cuttingTest(acceptableDirectionsArr, testingPiece, maxDistance);
            }
            printResultString("Проверка движения " + printingPieceName, check);
        } catch (Exception e) {
            printErrorString("Исключение при проверке движения " + printingPieceName, e);
        }
    }

    private static boolean movingTest(int[][] acceptableDirectionsArr, ChessPiece testingPiece, int maxDistance) {
        boolean check = true;
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                for (int indent = 1; indent <= maxDistance; indent++) {
                    for (int toLine = line - indent; toLine <= line + indent; toLine++) {
                        int indentB = testingPiece.getSymbol().equals(Horse.SYMBOL) ? 1 : indent;
                        if ((toLine == line - indent) || (toLine == line + indent)) {
                            for (int toColumn = column - indent; toColumn <= column + indent; toColumn++) {
                                check &= movingTestB(acceptableDirectionsArr, line, column, indentB, toLine, toColumn, testingPiece);
                            }
                        } else {
                            check &= movingTestB(acceptableDirectionsArr, line, column, indentB, toLine, column - indent, testingPiece);
                            check &= movingTestB(acceptableDirectionsArr, line, column, indentB, toLine, column + indent, testingPiece);
                        }
                    }
                }
            }
        }
        return check;
    }

    private static boolean movingTestB(int[][] acceptableDirectionsArr, int line, int column, int indent, int toLine, int toColumn,
                                       ChessPiece testingPiece) {
        ChessBoard chessboard = new ChessBoard(WHITE);
        chessboard.board[line][column] = testingPiece;
        chessboard.nowPlayer = testingPiece.getColor();
        boolean movingResult = chessboard.moveToPosition(line, column, toLine, toColumn);
        boolean isAcceptable = false;
        for (int[] acceptableDirection : acceptableDirectionsArr) {
            isAcceptable |= (toLine == (line + acceptableDirection[0] * indent) && toColumn == (column + acceptableDirection[1] * indent));
        }
        if (testingPiece.getSymbol().equals(Pawn.SYMBOL)) {
            isAcceptable &= (testingPiece.getColor().equals(WHITE)) ? line > 0 : line < 7;
            if (Math.abs(toLine - line) == 2) {
                isAcceptable &= (testingPiece.getColor().equals(WHITE)) ? line == 1 : line == 6;
            }
        }
        return (isAcceptable && isOnTheField(toLine, toColumn)) == movingResult;
    }

    private static boolean jumpOverTest(int[][] acceptableDirectionsArr, ChessPiece testingPiece, int maxDistance) {
        boolean check = true;
        for (ChessPiece interferingPiece : piecesArr) {
            for (int line = 0; line < 8; line++) {
                for (int column = 0; column < 8; column++) {
                    for (int indent = 2; indent <= maxDistance; indent++) {
                        for (int[] acceptableDirection : acceptableDirectionsArr) {
                            for (int interferingIndent = 1; interferingIndent < indent; interferingIndent++) {
                                int toLine = line + acceptableDirection[0] * indent;
                                int toColumn = column + acceptableDirection[1] * indent;
                                int interferingPieceLine = line + acceptableDirection[0] * interferingIndent;
                                int interferingPieceColumn = column + acceptableDirection[1] * interferingIndent;
                                if (isOnTheField(toLine, toColumn)) {
                                    ChessBoard chessboard = new ChessBoard(WHITE);
                                    chessboard.board[line][column] = testingPiece;
                                    chessboard.board[interferingPieceLine][interferingPieceColumn] = interferingPiece;
                                    chessboard.nowPlayer = testingPiece.getColor();
                                    check &= !chessboard.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }
            }
        }
        return check;
    }

    private static boolean horseJumpOverTest(int[][] acceptableMovementsArr, ChessPiece testingPiece) {
        boolean check = true;
        for (ChessPiece piece : piecesArr) {
            for (int line = 0; line < 8; line++) {
                for (int column = 0; column < 8; column++) {
                    for (int[] shift : acceptableMovementsArr) {
                        ChessBoard chessboard = new ChessBoard(WHITE);
                        fieldFilling(chessboard, piece);
                        chessboard.board[line][column] = testingPiece;
                        int toLine = line + shift[0];
                        int toColumn = column + shift[1];
                        if (isOnTheField(toLine, toColumn)) {
                            chessboard.board[toLine][toColumn] = null;
                            chessboard.nowPlayer = testingPiece.getColor();
                            check &= chessboard.moveToPosition(line, column, toLine, toColumn);
                        }
                    }
                }
            }
        }
        return  check;
    }

    private static boolean cuttingTest(int[][] acceptableDirectionsArr, ChessPiece testingPiece, int maxDistance) {
        boolean check = true;
        for (ChessPiece cuttingPiece : piecesArr) {
            for (int line = 0; line < 8; line++) {
                for (int column = 0; column < 8; column++) {
                    for (int indent = 1; indent <= maxDistance; indent++) {
                        int indentB = testingPiece.getSymbol().equals(Horse.SYMBOL) ? 1 : indent;
                        for (int[] acceptableDirection : acceptableDirectionsArr) {
                            int toLine = line + acceptableDirection[0] * indentB;
                            int toColumn = column + acceptableDirection[1] * indentB;
                            if (isOnTheField(toLine, toColumn)) {
                                ChessBoard chessboard = new ChessBoard(WHITE);
                                chessboard.board[line][column] = testingPiece;
                                chessboard.board[toLine][toColumn] = cuttingPiece;
                                chessboard.nowPlayer = testingPiece.getColor();
                                if (testingPiece.getColor().equals(cuttingPiece.getColor())) {
                                    check &= !chessboard.moveToPosition(line, column, toLine, toColumn);
                                } else {
                                    check &= chessboard.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }
            }
        }
        return check;
    }

    private static void fieldFilling(ChessBoard chessboard, ChessPiece piece) {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                chessboard.board[line][column] = piece;
            }
        }
    }

    private static void printResultString(String str, boolean check) {
        System.out.println(str + " " + (check ? ANSI_GREEN + "успешна" : ANSI_RED + "провалена") + ANSI_RESET);
    }

    private static void printErrorString(String str, Exception e) {
        System.out.println(ANSI_RED + str + ANSI_RESET);
        e.printStackTrace();
    }

    private static boolean isOnTheField(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }
}
