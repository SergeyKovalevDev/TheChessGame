public class Test {
    public static final String WHITE = ChessPiece.WHITE;
    public static final String BLACK = ChessPiece.BLACK;
    public static void main(String[] args) {
        //Проверка движения белой пешки
        ChessBoard cb = new ChessBoard(WHITE);
        System.out.println("Проверка движения белой пешки " + (whitePawnMovingTest(cb) ? "успешна" : "провалена"));
        //Проверка движения черной пешки
        cb = new ChessBoard(WHITE);
        System.out.println("Проверка движения черной пешки " + (blackPawnMovingTest(cb) ? "успешна" : "провалена"));
        //Проверка рубки белой пешкой
        cb = new ChessBoard(WHITE);
        System.out.println("Проверка рубки белой пешкой " + (whitePawnCuttingTest(cb) ? "успешна" : "провалена"));
        cb = new ChessBoard(WHITE);
    }

    public static boolean whitePawnMovingTest(ChessBoard cb) {
        boolean check;
        cb.board[1][1] = new Pawn(WHITE);
        cb.board[1][2] = new Pawn(WHITE);
        cb.board[3][3] = new Pawn(WHITE);
        // Проверка хода белой пешки на один шаг вперед
        cb.nowPlayer = WHITE;
        check = cb.moveToPosition(1, 1, 2, 1);
        // Проверка хода белой пешки на два шага вперед с линии 1
        cb.nowPlayer = WHITE;
        check &= cb.moveToPosition(1, 2, 3, 2);
        // Проверка NOT хода белой пешки на два шага вперед не с линии 1
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(2, 1, 4, 1);
        // Проверка NOT хода белой пешки назад
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 2, 3);
        // Проверка NOT хода белой пешки влево и назад
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 2, 2);
        // Проверка NOT хода белой пешки влево
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 3, 2);
        // Проверка NOT хода белой пешки влево и вверх
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 4, 2);
        // Проверка NOT хода белой пешки вправо и вверх
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 4, 4);
        // Проверка NOT хода белой пешки вправо
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 3, 4);
        // Проверка NOT хода белой пешки вправо и вниз
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 2, 4);
        //Проверка NOT хода белой пешки вперед если перед ней белая пешка
        cb.board[4][3] = new Pawn(WHITE);
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 4, 3);
        //Проверка NOT хода белой пешки вперед если перед ней черная пешка
        cb.board[4][3] = new Pawn(BLACK);
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(3, 3, 4, 3);
        return check;
    }

    public static boolean blackPawnMovingTest(ChessBoard cb) {
        boolean check;
        cb.board[6][1] = new Pawn(WHITE);
        cb.board[6][2] = new Pawn(WHITE);
        cb.board[4][3] = new Pawn(WHITE);
        // Проверка хода черной пешки на один шаг вперед
        cb.nowPlayer = WHITE;
        check = cb.moveToPosition(6, 1, 5, 1);
        System.out.println(check);
        // Проверка хода черной пешки на два шага вперед с линии 6
        cb.nowPlayer = WHITE;
        check &= cb.moveToPosition(6, 2, 4, 2);
        // Проверка NOT хода черной пешки на два шага вперед не с линии 6
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(5, 1, 3, 1);
        // Проверка NOT хода черной пешки назад
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 5, 3);
        // Проверка NOT хода черной пешки влево и назад
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 5, 2);
        // Проверка NOT хода черной пешки влево
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 4, 2);
        // Проверка NOT хода черной пешки влево и вперед
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 3, 2);
        // Проверка NOT хода черной пешки вправо и вперед
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 3, 4);
        // Проверка NOT хода черной пешки вправо
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 4, 4);
        // Проверка NOT хода черной пешки вправо и назад
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 5, 4);
        //Проверка NOT хода черной пешки вперед если перед ней черная пешка
        cb.board[3][3] = new Pawn(BLACK);
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 3, 3);
        //Проверка NOT хода черной пешки вперед если перед ней белая пешка
        cb.board[3][3] = new Pawn(WHITE);
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(4, 3, 3, 3);
        return check;
    }

    public static boolean whitePawnCuttingTest(ChessBoard cb) {
        boolean check;
        cb.board[3][3] = new Pawn(WHITE);
        cb.board[4][2] = new Pawn(BLACK);
        cb.board[5][3] = new Pawn(BLACK);
        cb.board[6][4] = new King(BLACK);
        cb.board[6][2] = new Pawn(WHITE);
        // Проверка рубки белой пешкой влево вверх
        cb.nowPlayer = WHITE;
        check = cb.moveToPosition(3, 3, 4, 2);
        // Проверка рубки белой пешкой вправо вперед
        cb.nowPlayer = WHITE;
        check &= cb.moveToPosition(4, 2, 5, 3);
        // Проверка NOT рубки белой пешкой короля
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(5, 3, 6, 4);
        // Проверка NOT рубки белой пешкой белой пешки
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(5, 3, 6, 2);

        return check;
    }
}
