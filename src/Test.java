public class Test {
    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard("White");
        cb.board[1][0] = new Pawn("White");
        cb.board[1][1] = new Pawn("White");
        cb.board[1][2] = new Pawn("White");
        cb.board[1][3] = new Pawn("White");
        cb.board[1][4] = new Pawn("White");
        cb.board[1][5] = new Pawn("White");
        cb.board[1][6] = new Pawn("White");
        cb.board[1][7] = new Pawn("White");
        // Проверка хода белых пешек на один шаг вперед
        boolean check = true;
        for (int i = 0; i < 8; i++) {
            check &= cb.moveToPosition(1, i, 2, i);
        }
        System.out.println("Проверка хода белых пешек на один шаг вперед успешна? - " + check);
    }
}
