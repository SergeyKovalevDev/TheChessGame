public class Test {
    public static final String WHITE = ChessPiece.WHITE;
    public static final String BLACK = ChessPiece.BLACK;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final int[][] pawnAcceptableMovingDirectionsArr = new int[][]{{1, 0}};
    public static final int[][] pawnAcceptableCuttingDirectionsArr = new int[][] {{1, -1}, {1, 1}};
    public static final int[][] rookAcceptableDirectionsArr = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public static final int[][] horseAcceptableMovementsArr = new int[][] {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    public static final int[][] bishopAcceptableDirectionsArr = new int[][] {{1, -1}, {1, 1}, {-1, -1}, {-1, 1}};
    public static final int[][] queenAcceptableDirectionsArr = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public static final int[][] kingAcceptableDirectionsArr = queenAcceptableDirectionsArr;
    public static final ChessPiece[] piecesArr = new ChessPiece[]{
            new Pawn(WHITE), new Pawn(BLACK),
            new Rook(WHITE), new Rook(BLACK),
            new Horse(WHITE), new Horse(BLACK),
            new Bishop(WHITE), new Bishop(BLACK),
            new Queen(WHITE), new Queen(BLACK),
            new King(WHITE), new King(BLACK)};

    public static void main(String[] args) {
        //Проверка движения пешки
        pawnMovingTest();
        //Проверка рубки пешкой
        pawnCuttingTest();
        System.out.println();
        // Проверка движения пешки
        RHBQK_movingAndCuttingTest(new Pawn(WHITE), 1, "пешки");
        // Проверка движения ладьи
        RHBQK_movingAndCuttingTest(new Rook(WHITE), 7, "ладьи");
        // Проверка движения коня
        RHBQK_movingAndCuttingTest(new Horse(WHITE), 2, "коня");
        // Проверка движения слона
        RHBQK_movingAndCuttingTest(new Bishop(WHITE), 7, "слона");
        // Проверка движения королевы
        RHBQK_movingAndCuttingTest(new Queen(WHITE), 7, "королевы");
        // Проверка движения короля
        RHBQK_movingAndCuttingTest(new King(WHITE), 1, "короля");

    }

    private static void pawnMovingTest() {
        try {
            ChessBoard cb;
            boolean check = true;
            for (String color : new String[]{WHITE, BLACK}) {
                // Проверка хода пешки на один шаг вперед без помехи впереди
                cb = new ChessBoard(WHITE);
                for (int line = 1; line < 7; line++) {
                    for (int column = 0; column < 8; column++) {
                        cb.board[color.equals(WHITE) ? line : 7 - line][column] = new Pawn(color);
                    }
                    for (int column = 0; column < 8; column++) {
                        cb.nowPlayer = color;
                        check &= cb.moveToPosition(color.equals(WHITE) ? line : 7 - line, column,
                                color.equals(WHITE) ? line + 1 : 6 - line, column);
                    }
                }

                // Проверка хода пешки на два шага вперед без помехи впереди
                cb = new ChessBoard(WHITE);
                for (int line = 1; line < 2; line++) {
                    for (int column = 0; column < 8; column++) {
                        cb.board[color.equals(WHITE) ? line : 7 - line][column] = new Pawn(color);
                    }
                    for (int column = 0; column < 8; column++) {
                        cb.nowPlayer = color;
                        check &= cb.moveToPosition(color.equals(WHITE) ? line : 7 - line, column,
                                color.equals(WHITE) ? line + 2 : 5 - line, column);
                    }
                }

                // Проверка NOT хода пешки на один шаг вперед с помехой впереди
                cb = new ChessBoard(WHITE);
                for (int line = 1; line < 7; line++) {
                    for (int column = 0; column < 8; column++) {
                        cb.board[color.equals(WHITE) ? line : 7 - line][column] = new Pawn(color);
                        cb.board[color.equals(WHITE) ? line + 1 : 6 - line][column] = new Pawn(color);
                    }
                    for (int column = 0; column < 8; column++) {
                        cb.nowPlayer = color;
                        check &= !cb.moveToPosition(color.equals(WHITE) ? line : 7 - line, column,
                                color.equals(WHITE) ? line + 1 : 6 - line, column);
                    }
                }

                // Проверка NOT хода пешки на два шага вперед с помехой на один шаг впереди
                cb = new ChessBoard(WHITE);
                for (int line = 1; line < 2; line++) {
                    for (int column = 0; column < 8; column++) {
                        cb.board[color.equals(WHITE) ? line : 7 - line][column] = new Pawn(color);
                        cb.board[color.equals(WHITE) ? line + 1 : 6 - line][column] = new Pawn(color);
                    }
                    for (int column = 0; column < 8; column++) {
                        cb.nowPlayer = color;
                        check &= !cb.moveToPosition(color.equals(WHITE) ? line : 7 - line, column,
                                color.equals(WHITE) ? line + 2 : 5 - line, column);
                    }
                }

                // Проверка NOT хода пешки на два шага вперед с помехой на два шага вперед
                cb = new ChessBoard(WHITE);
                for (int line = 1; line < 2; line++) {
                    for (int column = 0; column < 8; column++) {
                        cb.board[color.equals(WHITE) ? line : 7 - line][column] = new Pawn(color);
                        cb.board[color.equals(WHITE) ? line + 2 : 5 - line][column] = new Pawn(color);
                    }
                    for (int column = 0; column < 8; column++) {
                        cb.nowPlayer = color;
                        check &= !cb.moveToPosition(color.equals(WHITE) ? line : 7 - line, column,
                                color.equals(WHITE) ? line + 2 : 5 - line, column);
                    }
                }

                // Проверка NOT хода пешки на один шаг во всех направлениях кроме вперед и на свое место
                for (int i = 1; i < 7; i++) {
                    for (int[] shift : new int[][]{{-i, -i}, {0, -i}, {i, -i}, {i, i}, {0, i}, {-i, i}, {-i, 0}, {0, 0}}) {
                        for (int line = 0; line < 8; line++) {
                            for (int column = 0; column < 8; column++) {
                                cb = new ChessBoard(WHITE);
                                cb.board[line][column] = new Pawn(color);
                                cb.nowPlayer = color;
                                check &= !cb.moveToPosition(line, column,
                                        line + (color.equals(WHITE) ? shift[0] : -shift[0]), column + shift[1]);
                            }
                        }
                    }
                }
            }
            printResultString("Проверка движения пешки", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверке движения пешки!", e);
        }
    }

    private static void pawnCuttingTest() {
        try {
            ChessBoard cb;
            boolean check = true;
            for (String color :
                    new String[]{WHITE, BLACK}) {
                // Проверка рубки пешкой фигуры противоположного цвета кроме короля
                String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;
                for (ChessPiece piece :
                        new ChessPiece[] {
                                new Pawn(oppositeColor),
                                new Rook(oppositeColor),
                                new Horse(oppositeColor),
                                new Bishop(oppositeColor),
                                new Queen(oppositeColor)}) {
                    check &= pawnCuttingTestB(check, color, piece);
                }

                // Проверка NOT рубки пешкой короля противоположного цвета
                check &= !pawnCuttingTestB(check, color, new King(oppositeColor));

                // Проверка NOT рубки пешкой фигуры своего цвета
                for (ChessPiece piece :
                        new ChessPiece[] {
                                new Pawn(color),
                                new Rook(color),
                                new Horse(color),
                                new Bishop(color),
                                new Queen(color),
                                new King(color)}) {
                    check &= !pawnCuttingTestB(check, color, piece);
                }

                // Проверка NOT рубки пешкой назад
                for (ChessPiece piece :
                        new ChessPiece[] {
                                new Pawn(oppositeColor),
                                new Rook(oppositeColor),
                                new Horse(oppositeColor),
                                new Bishop(oppositeColor),
                                new Queen(oppositeColor)}) {
                    check &= !pawnCuttingTestC(check, color, piece);
                }
            }
            printResultString("Проверка рубки пешкой", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверке рубки пешкой!", e);
        }
    }

    private static void RHBQK_movingAndCuttingTest(ChessPiece testingPiece, int maxDistance, String printingPieceName) {
        try {
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                testingPiece.color = color;
                int[][] acceptableMovingArr = new int[0][0];
                int[][] acceptableCuttingArr = new int[0][0];
                switch (testingPiece.getSymbol()) {
                    case Pawn.SYMBOL -> {
                        acceptableMovingArr = pawnAcceptableMovingDirectionsArr;
                        acceptableCuttingArr = pawnAcceptableCuttingDirectionsArr;
                    }
                    case Rook.SYMBOL -> {
                        acceptableMovingArr = rookAcceptableDirectionsArr;
                        acceptableCuttingArr = rookAcceptableDirectionsArr;
                    }
                    case Horse.SYMBOL -> {
                        acceptableMovingArr = horseAcceptableMovementsArr;
                        acceptableCuttingArr = horseAcceptableMovementsArr;
                    }
                    case Bishop.SYMBOL -> {
                        acceptableMovingArr = bishopAcceptableDirectionsArr;
                        acceptableCuttingArr = bishopAcceptableDirectionsArr;
                    }
                    case Queen.SYMBOL -> {
                        acceptableMovingArr = queenAcceptableDirectionsArr;
                        acceptableCuttingArr = queenAcceptableDirectionsArr;
                    }
                    case King.SYMBOL -> {
                        acceptableMovingArr = kingAcceptableDirectionsArr;
                        acceptableCuttingArr = kingAcceptableDirectionsArr;
                    }
                    default -> {
                    }
                }
                // Проверка движения по пустому полю
                check &= RHBQK_movingTest(acceptableMovingArr, testingPiece, maxDistance);
                // Проверка перепрыгивания через фигуры
                check &= (testingPiece.getSymbol().equals(Horse.SYMBOL)) ?
                        H_jumpOverTest(acceptableMovingArr, testingPiece, maxDistance) :
                        RBQK_jumpOverTest(acceptableMovingArr, testingPiece, maxDistance);
                // Проверка рубки фигур
                check &= RHBQK_cuttingTest(acceptableCuttingArr, testingPiece, maxDistance);
            }
            printResultString("Проверка движения " + printingPieceName, check);
        } catch (Exception e) {
            printErrorString("Исключение при проверке движения " + printingPieceName, e);
        }
    }

    private static boolean RHBQK_movingTest(int[][] acceptableDirectionsArr, ChessPiece testingPiece, int maxDistance) {
        boolean check = true;
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                for (int indent = 1; indent <= maxDistance; indent++) {
                    for (int toLine = line - indent; toLine <= line + indent; toLine++) {
                        int indentB = testingPiece.getSymbol().equals(Horse.SYMBOL) ? 1 : indent;
                        if ((toLine == line - indent) || (toLine == line + indent)) {
                            for (int toColumn = column - indent; toColumn <= column + indent; toColumn++) {
                                boolean temp = RHBQK_movingTestB(acceptableDirectionsArr, line, column, indentB, toLine, toColumn, testingPiece);
                                check &= temp;
                            }
                        } else {
                            check &= RHBQK_movingTestB(acceptableDirectionsArr, line, column, indentB, toLine, column - indent, testingPiece);
                            check &= RHBQK_movingTestB(acceptableDirectionsArr, line, column, indentB, toLine, column + indent, testingPiece);
                        }
                    }
                }
            }
        }
        return check;
    }

    private static boolean RHBQK_movingTestB(int[][] acceptableDirectionsArr, int line, int column, int indent, int toLine, int toColumn,
                                             ChessPiece testingPiece) {
        ChessBoard cb = new ChessBoard(WHITE);
        cb.board[line][column] = testingPiece;
        cb.nowPlayer = testingPiece.getColor();
        boolean movingResult = cb.moveToPosition(line, column, toLine, toColumn);
        boolean isAcceptable = false;
        for (int[] acceptableDirection : acceptableDirectionsArr) {
            isAcceptable |= (toLine == (line + acceptableDirection[0] * indent) && toColumn == (column + acceptableDirection[1] * indent));
        }
        return (isAcceptable && isOnTheField(toLine, toColumn)) == movingResult;
    }

    private static boolean RBQK_jumpOverTest(int[][] acceptableDirectionsArr, ChessPiece testingPiece, int maxDistance) {
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
                                    ChessBoard cb = new ChessBoard(WHITE);
                                    cb.board[line][column] = testingPiece;
                                    cb.board[interferingPieceLine][interferingPieceColumn] = interferingPiece;
                                    cb.nowPlayer = testingPiece.getColor();
                                    check &= !cb.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }
            }
        }
        return check;
    }

    private static boolean H_jumpOverTest(int[][] acceptableMovementsArr, ChessPiece testingPiece, int maxDistance) {
        boolean check = true;
        for (ChessPiece piece : piecesArr) {
            for (int line = 0; line < 8; line++) {
                for (int column = 0; column < 8; column++) {
                    for (int[] shift : acceptableMovementsArr) {
                        ChessBoard cb = new ChessBoard(WHITE);
                        fieldFilling(cb, piece);
                        cb.board[line][column] = testingPiece;
                        int toLine = line + shift[0];
                        int toColumn = column + shift[1];
                        if (isOnTheField(toLine, toColumn)) {
                            cb.board[toLine][toColumn] = null;
                            cb.nowPlayer = testingPiece.getColor();
                            check &= cb.moveToPosition(line, column, toLine, toColumn);
                        }
                    }
                }
            }
        }
        return  check;
    }

    private static boolean RHBQK_cuttingTest(int[][] acceptableDirectionsArr, ChessPiece testingPiece, int maxDistance) {
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
                                ChessBoard cb = new ChessBoard(WHITE);
                                cb.board[line][column] = testingPiece;
                                cb.board[toLine][toColumn] = cuttingPiece;
                                cb.nowPlayer = testingPiece.getColor();
                                if (testingPiece.getColor().equals(cuttingPiece.getColor())) {
                                    check &= !cb.moveToPosition(line, column, toLine, toColumn);
                                } else {
                                    check &= cb.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }
            }
        }
        return check;
    }

    private static void fieldFilling(ChessBoard cb, ChessPiece piece) {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                cb.board[line][column] = piece;
            }
        }
    }

    private static boolean pawnCuttingTestB(boolean check, String color, ChessPiece piece) {
        ChessBoard cb;
        for (int shift :
                new int[] {1, -1}) {
            for (int line = 1; line < 7; line++) {
                for (int column = 0; column < 8; column++) {
                    cb = new ChessBoard(WHITE);
                    cb.board[line][column] = new Pawn(color);
                    int toLine = line + (color.equals(WHITE) ? 1 : -1);
                    int toColumn = column + shift;
                    if (toColumn <= 7 && toColumn >= 0) {
                        cb.board[toLine][toColumn] = piece;
                        cb.nowPlayer = color;
                        check &= cb.moveToPosition(line, column, toLine, toColumn);
                    }
                }
            }
        }
        return check;
    }

    private static boolean pawnCuttingTestC(boolean check, String color, ChessPiece piece) {
        ChessBoard cb;
        for (int shift :
                new int[] {1, -1}) {
            for (int line = 1; line < 7; line++) {
                for (int column = 0; column < 8; column++) {
                    cb = new ChessBoard(WHITE);
                    cb.board[line][column] = new Pawn(color);
                    int toLine = line + (color.equals(WHITE) ? -1 : 1);
                    int toColumn = column + shift;
                    if (toColumn <= 7 && toColumn >= 0) {
                        cb.board[toLine][toColumn] = piece;
                        cb.nowPlayer = color;
                        check &= cb.moveToPosition(line, column, toLine, toColumn);
                    }
                }
            }
        }
        return check;
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
