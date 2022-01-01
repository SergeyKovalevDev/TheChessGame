public class Test {
    public static final String WHITE = ChessPiece.WHITE;
    public static final String BLACK = ChessPiece.BLACK;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String[] COLOR_ARR = new String[]{WHITE, BLACK};

    public static void main(String[] args) {
        //Проверка движения пешки
        pawnMovingTest();
        //Проверка рубки пешкой
        pawnCuttingTest();
        //Проверка движения ладьи
        rookMovingTest();
        //Проверка рубки ладьей
        rookCuttingTest();
        //Проверка движения коня
        horseMovingTest();
        //Проверка рубки конем
        horseCuttingTest();
        // Проверка движения слона
        bishopMovingTest();
    }

    public static void pawnMovingTest() {
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

    public static void pawnCuttingTest() {
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

    public static void rookMovingTest() {
        try {
            ChessBoard cb;
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                //Проверка движения ладьи
                for (int line = 0; line < 8; line++) {
                    for (int column = 0; column < 8; column++) {
                        for (int toLine = 0; toLine < 8; toLine++) {
                            for (int toColumn = 0; toColumn < 8; toColumn++) {
                                if ((toLine == line ^ toColumn == column)) {
                                    cb = new ChessBoard(WHITE);
                                    cb.board[line][column] = new Rook(color);
                                    cb.nowPlayer = color;
                                    check &= cb.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }

                //Проверка NOT движения ладьи на свое же место
                for (int line = 0; line < 8; line++) {
                    for (int column = 0; column < 8; column++) {
                        for (int toLine = 0; toLine < 8; toLine++) {
                            for (int toColumn = 0; toColumn < 8; toColumn++) {
                                if ((toLine == line && toColumn == column)) {
                                    cb = new ChessBoard(WHITE);
                                    cb.board[line][column] = new Rook(color);
                                    cb.nowPlayer = color;
                                    check &= !cb.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }

                // Проверка NOT перепрыгивания ладьи через другие фигуры
                String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;
                for (ChessPiece piece : new ChessPiece[]{
                        new Pawn(color), new Pawn(oppositeColor),
                        new Rook(color), new Rook(oppositeColor),
                        new Horse(color), new Horse(oppositeColor),
                        new Bishop(color), new Bishop(oppositeColor),
                        new Queen(color), new Queen(oppositeColor),
                        new King(color), new King(oppositeColor)}) {
                    for (int line = 0; line < 8; line++) {
                        for (int column = 0; column < 8; column++) {
                            for (int i = 1; i < 6; i++) {
                                for (int[] shift : new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}}) {
                                    cb = new ChessBoard(WHITE);
                                    cb.board[line][column] = new Rook(color);
                                    int shiftedLine = line + shift[0] * i;
                                    int shiftedColumn = column + shift[1] * i;
                                    if (isOnTheField(shiftedLine, shiftedColumn)) {
                                        cb.board[shiftedLine][shiftedColumn] = piece;
                                        cb.nowPlayer = color;
                                        check &= !cb.moveToPosition(line, column, shiftedLine + shift[0], shiftedColumn + shift[1]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            printResultString("Проверка движения ладьи", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверке движения ладьи!", e);
            e.printStackTrace();
        }
    }

    public static void rookCuttingTest() {
        try {
            ChessBoard cb;
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;

                // Проверка рубки ладьей других фигур
                for (ChessPiece piece : new ChessPiece[]{
                        new Pawn(oppositeColor),
                        new Rook(oppositeColor),
                        new Horse(oppositeColor),
                        new Bishop(oppositeColor),
                        new Queen(oppositeColor),
                        new King(oppositeColor)}) {
                    for (int line = 0; line < 8; line++) {
                        for (int column = 0; column < 8; column++) {
                            for (int i = 1; i < 6; i++) {
                                for (int[] shift : new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}}) {
                                    cb = new ChessBoard(WHITE);
                                    cb.board[line][column] = new Rook(color);
                                    int shiftedLine = line + shift[0] * i;
                                    int shiftedColumn = column + shift[1] * i;
                                    if (isOnTheField(shiftedLine, shiftedColumn)) {
                                        cb.board[shiftedLine][shiftedColumn] = piece;
                                        cb.nowPlayer = color;
                                        check &= cb.moveToPosition(line, column, shiftedLine, shiftedColumn);
                                    }
                                }
                            }
                        }
                    }
                }

                // Проверка NOT рубки ладьей своих фигур
                for (ChessPiece piece : new ChessPiece[]{
                        new Pawn(color),
                        new Rook(color),
                        new Horse(color),
                        new Bishop(color),
                        new Queen(color),
                        new King(color)}) {
                    for (int line = 0; line < 8; line++) {
                        for (int column = 0; column < 8; column++) {
                            for (int i = 1; i < 6; i++) {
                                for (int[] shift : new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}}) {
                                    cb = new ChessBoard(WHITE);
                                    cb.board[line][column] = new Rook(color);
                                    int shiftedLine = line + shift[0] * i;
                                    int shiftedColumn = column + shift[1] * i;
                                    if (isOnTheField(shiftedLine, shiftedColumn)) {
                                        cb.board[shiftedLine][shiftedColumn] = piece;
                                        cb.nowPlayer = color;
                                        check &= !cb.moveToPosition(line, column, shiftedLine, shiftedColumn);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            printResultString("Проверка рубки ладьей", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверка рубки ладьей!", e);
            e.printStackTrace();
        }
    }

    public static void horseMovingTest() {
        try {
            ChessBoard cb;
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;

                // Проверка хода коня по свободному полю
                for (int line = 0; line < 8; line++) {
                    for (int column = 0; column < 8; column++) {
                        for (int[] shift: new int[][] {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}}) {
                            cb = new ChessBoard(WHITE);
                            cb.board[line][column] = new Horse(color);
                            int toLine = line + shift[0];
                            int toColumn = column + shift[1];
                            if (isOnTheField(toLine, toColumn)) {
                                cb.nowPlayer = color;
                                check &= cb.moveToPosition(line, column, toLine, toColumn);
                            }
                        }
                    }
                }

                // Проверка хода коня через фигуры своего и противоположного цветов
                for (String col : new String[] {color, oppositeColor}) {
                    for (int line = 0; line < 8; line++) {
                        for (int column = 0; column < 8; column++) {
                            for (int[] shift: new int[][] {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}}) {
                                cb = new ChessBoard(WHITE);
                                fieldFilling(cb, new Pawn(col));
                                cb.board[line][column] = new Horse(color);
                                int toLine = line + shift[0];
                                int toColumn = column + shift[1];
                                if (isOnTheField(toLine, toColumn)) {
                                    cb.board[toLine][toColumn] = null;
                                    cb.nowPlayer = color;
                                    check &= cb.moveToPosition(line, column, toLine, toColumn);
                                }
                            }
                        }
                    }
                }
            }
            printResultString("Проверка движения коня", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверке движения коня!", e);
            e.printStackTrace();
        }
    }

    public static void horseCuttingTest() {
        try {
            ChessBoard cb;
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;

                // Проверка рубки конем фигур противоположного цвета по всему полю
                for (int line = 0; line < 8; line++) {
                    for (int column = 0; column < 8; column++) {
                        for (int[] shift: new int[][] {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}}) {
                            cb = new ChessBoard(WHITE);
                            fieldFilling(cb, new Pawn(oppositeColor));
                            cb.board[line][column] = new Horse(color);
                            int toLine = line + shift[0];
                            int toColumn = column + shift[1];
                            if (isOnTheField(toLine, toColumn)) {
                                cb.nowPlayer = color;
                                check &= cb.moveToPosition(line, column, toLine, toColumn);
                            }
                        }
                    }
                }

                // Проверка NOT рубки конем фигур своего цвета по всему полю
                for (int line = 0; line < 8; line++) {
                    for (int column = 0; column < 8; column++) {
                        for (int[] shift: new int[][] {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}}) {
                            cb = new ChessBoard(WHITE);
                            fieldFilling(cb, new Pawn(color));
                            cb.board[line][column] = new Horse(color);
                            int toLine = line + shift[0];
                            int toColumn = column + shift[1];
                            cb.nowPlayer = color;
                            check &= !cb.moveToPosition(line, column, toLine, toColumn);
                        }
                    }
                }
            }
            printResultString("Проверка рубки конем", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверке рубки конем!", e);
        }
    }

    public static void bishopMovingTest() {
        try {
            boolean check = true;
            for (String color : new String[] {WHITE, BLACK}) {
                String oppositeColor = color.equals(WHITE) ? BLACK : WHITE;
                int[][] acceptableDirectionsArr = new int[][] {{1, -1}, {1, 1}, {-1, -1}, {-1, 1}};
                ChessPiece[] piecesArr = new ChessPiece[] {
                        new Pawn(color), new Pawn(oppositeColor),
                        new Rook(color), new Rook(oppositeColor),
                        new Horse(color), new Horse(oppositeColor),
                        new Bishop(color), new Bishop(oppositeColor),
                        new Queen(color), new Queen(oppositeColor),
                        new King(color), new King(oppositeColor)};
                ChessPiece piece = new Bishop(color);

                // Проверка движения слона по пустому полю
                check &= freeFieldMovingTest(color, acceptableDirectionsArr, piece);

//                // Проверка NOT перепрыгивания слона через другие фигуры
//                for (ChessPiece piece : piecesArr) {
//                    for (int line = 0; line < 8; line++) {
//                        for (int column = 0; column < 8; column++) {
//                            for (int[] possibleMoving : acceptableDirectionsArr) {
//                                for (int i = 2; i < 8; i++) {
//                                    for (int j = line+1; j < i; j++) {
//                                        int toLine = line + possibleMoving[0] * i;
//                                        int toColumn = column + possibleMoving[1] * i;
//                                        int interferePieceLine = line + possibleMoving[0] * j;
//                                        int interferePieceColumn = column + possibleMoving[1] * j;
//                                        if (isOnTheField(toLine, toColumn)) {
//                                            cb = new ChessBoard(WHITE);
//                                            cb.board[line][column] = new Bishop(color);
//                                            cb.board[interferePieceLine][interferePieceColumn] = piece;
//                                            cb.nowPlayer = color;
//                                            check &= !cb.moveToPosition(line, column, toLine, toColumn);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
            }

            printResultString("Проверка движения слона", check);
        } catch (Exception e) {
            printErrorString("Ошибка при проверке движения слона!", e);
        }
    }

    private static boolean freeFieldMovingTest(String color, int[][] acceptableDirectionsArr, ChessPiece piece) {
        boolean check = true;
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                for (int indent = 1; indent < 8; indent++) {
                    for (int toLine = line - indent; toLine <= line + indent; toLine++) {
                        if ((toLine == line - indent) || (toLine == line + indent)) {
                            for (int toColumn = column - indent; toColumn <= column + indent; toColumn++) {
                                check &= movingTest(color, acceptableDirectionsArr, line, column, indent, toLine, toColumn, piece);
                            }
                        } else {
                            check &= movingTest(color, acceptableDirectionsArr, line, column, indent, toLine, column - indent, piece);
                            check &= movingTest(color, acceptableDirectionsArr, line, column, indent, toLine, column + indent, piece);
                        }
                    }
                }
            }
        }
        return check;
    }

    private static boolean movingTest(String color, int[][] acceptableDirectionsArr, int line, int column, int indent, int toLine, int toColumn, ChessPiece piece) {
        ChessBoard cb = new ChessBoard(WHITE);
        cb.board[line][column] = piece;
        cb.nowPlayer = color;
        boolean moveToPositionResult = cb.moveToPosition(line, column, toLine, toColumn);
        boolean isAcceptable = false;
        for (int[] acceptableDirection : acceptableDirectionsArr) {
            isAcceptable |= (toLine == (line + acceptableDirection[0] * indent) && toColumn == (column + acceptableDirection[1] * indent));
        }
        return (isAcceptable && isOnTheField(toLine, toColumn)) == moveToPositionResult;
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
