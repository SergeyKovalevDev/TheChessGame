public class Test {
    public static final String WHITE = ChessPiece.WHITE;
    public static final String BLACK = ChessPiece.BLACK;
    public static void main(String[] args) {
        //Проверка движения белой пешки
        whitePawnMovingTest(new ChessBoard(WHITE));
        //Проверка движения черной пешки
        blackPawnMovingTest(new ChessBoard(BLACK));
        //Проверка рубки белой пешкой
        whitePawnCuttingTest(new ChessBoard(WHITE));
        //Проверка рубки черной пешкой
        blackPawnCuttingTest(new ChessBoard(BLACK));
        //Проверка движения белой ладьи
        whiteRookMovingTest(new ChessBoard(WHITE));
        //Проверка рубки белой ладьей
        whiteRookCuttingTest(new ChessBoard(WHITE));
        //Проверка движения белого коня
        whiteHorseMovingTest(new ChessBoard(WHITE));
        //Проверка рубки конем
        whiteHorseCuttingTest(new ChessBoard(WHITE));
    }

    public static void whitePawnMovingTest(ChessBoard cb) {
        cb.board[1][1] = new Pawn(WHITE);
        cb.board[1][2] = new Pawn(WHITE);
        cb.board[3][3] = new Pawn(WHITE);
        // Проверка хода белой пешки на один шаг вперед
        cb.nowPlayer = WHITE;
        boolean check = cb.moveToPosition(1, 1, 2, 1);
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
        System.out.println("Проверка движения белой пешки " + (check ? "успешна" : "провалена"));
    }

    public static void blackPawnMovingTest(ChessBoard cb) {
        cb.board[6][1] = new Pawn(BLACK);
        cb.board[6][2] = new Pawn(BLACK);
        cb.board[4][3] = new Pawn(BLACK);
        // Проверка хода черной пешки на один шаг вперед
        cb.nowPlayer = BLACK;
        boolean check = cb.moveToPosition(6, 1, 5, 1);
        // Проверка хода черной пешки на два шага вперед с линии 6
        cb.nowPlayer = BLACK;
        check &= cb.moveToPosition(6, 2, 4, 2);
        // Проверка NOT хода черной пешки на два шага вперед не с линии 6
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(5, 1, 3, 1);
        // Проверка NOT хода черной пешки назад
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 5, 3);
        // Проверка NOT хода черной пешки влево и назад
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 5, 2);
        // Проверка NOT хода черной пешки влево
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 4, 2);
        // Проверка NOT хода черной пешки влево и вперед
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 3, 2);
        // Проверка NOT хода черной пешки вправо и вперед
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 3, 4);
        // Проверка NOT хода черной пешки вправо
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 4, 4);
        // Проверка NOT хода черной пешки вправо и назад
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 5, 4);
        //Проверка NOT хода черной пешки вперед если перед ней черная пешка
        cb.board[3][3] = new Pawn(BLACK);
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 3, 3);
        //Проверка NOT хода черной пешки вперед если перед ней белая пешка
        cb.board[3][3] = new Pawn(BLACK);
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(4, 3, 3, 3);
        System.out.println("Проверка движения черной пешки " + (check ? "успешна" : "провалена"));
    }

    public static void whitePawnCuttingTest(ChessBoard cb) {
        cb.board[3][3] = new Pawn(WHITE);
        cb.board[4][2] = new Pawn(BLACK);
        cb.board[5][3] = new Pawn(BLACK);
        cb.board[6][4] = new King(BLACK);
        cb.board[6][2] = new Pawn(WHITE);
        // Проверка рубки белой пешкой влево вверх
        cb.nowPlayer = WHITE;
        boolean check = cb.moveToPosition(3, 3, 4, 2);
        // Проверка рубки белой пешкой вправо вперед
        cb.nowPlayer = WHITE;
        check &= cb.moveToPosition(4, 2, 5, 3);
        // Проверка NOT рубки белой пешкой короля
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(5, 3, 6, 4);
        // Проверка NOT рубки белой пешкой белой пешки
        cb.nowPlayer = WHITE;
        check &= !cb.moveToPosition(5, 3, 6, 2);
        System.out.println("Проверка рубки белой пешкой " + (check ? "успешна" : "провалена"));
    }
    
    public static void blackPawnCuttingTest(ChessBoard cb) {
        cb.board[4][3] = new Pawn(BLACK);
        cb.board[3][2] = new Pawn(WHITE);
        cb.board[2][3] = new Pawn(WHITE);
        cb.board[1][4] = new King(WHITE);
        cb.board[1][2] = new Pawn(BLACK);
        // Проверка рубки черной пешкой влево вперед
        cb.nowPlayer = BLACK;
        boolean check = cb.moveToPosition(4, 3, 3, 2);
        // Проверка рубки черной пешкой вправо вперед
        cb.nowPlayer = BLACK;
        check &= cb.moveToPosition(3, 2, 2, 3);
        // Проверка NOT рубки черной пешкой короля
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(2, 3, 1, 4);
        // Проверка NOT рубки черной пешкой черной пешки
        cb.nowPlayer = BLACK;
        check &= !cb.moveToPosition(2, 3, 2, 2);
        System.out.println("Проверка рубки черной пешкой " + (check ? "успешна" : "провалена"));
    }

    public static void whiteRookMovingTest(ChessBoard cb) {
        try {
            //Проверка движения ладьи
            cb.board[0][0] = new Rook(WHITE);
            cb.nowPlayer = WHITE;
            boolean check = cb.moveToPosition(0, 0, 7, 0);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(7, 0, 7, 7);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(7, 7, 7, 0);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(7, 0, 0, 0);
            //Проверка NOT перепрыгивания через другие фигуры
            cb.board[3][0] = new Pawn(WHITE);
            cb.nowPlayer = WHITE;
            check &= !cb.moveToPosition(0, 0, 7, 0);
            cb.board[3][0] = new Pawn(BLACK);
            cb.nowPlayer = WHITE;
            check &= !cb.moveToPosition(0, 0, 7, 0);
            System.out.println("Проверка движения ладьи " + (check ? "успешна" : "провалена"));
        } catch (Exception e) {
            System.out.println("Ошибка при проверке движения ладьи!");
            e.printStackTrace();
        }
    }

    public static void whiteRookCuttingTest(ChessBoard cb) {
        try {
            //Проверка рубки черной фигуры
            cb.board[0][0] = new Rook(WHITE);
            cb.board[3][0] = new Pawn(BLACK);
            cb.board[6][0] = new Pawn(WHITE);
            cb.nowPlayer = WHITE;
            boolean check = cb.moveToPosition(0, 0, 3, 0);
            //Проверка NOT рубки белой фигуры
            cb.nowPlayer = WHITE;
            check &= !cb.moveToPosition(3, 0, 6,  0);
            System.out.println("Проверка рубки ладьей " + (check ? "успешна" : "провалена"));
        } catch (Exception e) {
            System.out.println("Ошибка при проверка рубки ладьей! ");
            e.printStackTrace();
        }
    }

    public static void whiteHorseMovingTest(ChessBoard cb) {
        try {
            //Проверка хода коня
            cb.board[4][2] = new Rook(WHITE);
            cb.board[4][3] = new Rook(BLACK);
            cb.board[4][4] = new Rook(WHITE);
            cb.board[3][2] = new Rook(BLACK);
            cb.board[3][3] = new Horse(WHITE);
            cb.board[3][4] = new Rook(WHITE);
            cb.board[2][2] = new Rook(BLACK);
            cb.board[2][3] = new Rook(WHITE);
            cb.board[2][4] = new Rook(BLACK);
            cb.nowPlayer = WHITE;
            boolean check = cb.moveToPosition(3, 3, 5, 2);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 5, 4);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 4, 5);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 2, 5);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 1, 4);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 1, 2);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 2, 1);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 4, 1);
            System.out.println("Проверка движения коня " + (check ? "успешна" : "провалена"));
        } catch (Exception e) {
            System.out.println("Ошибка при проверке движения коня!");
            e.printStackTrace();
        }
    }

    public static void whiteHorseCuttingTest(ChessBoard cb) {
        try {
            //Проверка рубки белым конем черных фигур
            cb.board[4][1] = new Rook(BLACK);
            cb.board[4][5] = new Rook(BLACK);
            cb.board[3][3] = new Horse(WHITE);
            cb.board[2][1] = new King(WHITE);
            cb.board[2][5] = new Queen(WHITE);
            cb.nowPlayer = WHITE;
            boolean check = cb.moveToPosition(3, 3, 4, 1);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= cb.moveToPosition(3, 3, 4, 5);
            //Проверка NOT рубки белым конем черных фигур
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= !cb.moveToPosition(3, 3, 2, 1);
            cb.board[3][3] = new Horse(WHITE);
            cb.nowPlayer = WHITE;
            check &= !cb.moveToPosition(3, 3, 2, 5);
            System.out.println("Проверка рубки конем " + (check ? "успешна" : "провалена"));
        } catch (Exception e) {
            System.out.println("Ошибка при проверке рубки конем!");
            e.printStackTrace();
        }
    }
}
