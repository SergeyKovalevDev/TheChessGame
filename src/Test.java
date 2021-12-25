public class Test {
    public static void main(String[] args) {
        ChessBoard cb = Main.buildBoard();
        boolean check = false;
        // Проверка хода пешек на один шаг вперед
        for (int i = 0; i < 8; i++) {
            check = cb.moveToPosition(1, i, 2, i) && cb.moveToPosition(6, i, 5, i);
        }
        System.out.println("Проверка хода пешек на один шаг вперед " + (check ? "успешна" : "провалена"));

        // Проверка хода пешек на два шага вперед
        cb = Main.buildBoard();
        for (int i = 0; i < 8; i++) {
            check = cb.moveToPosition(1, i, 3, i) && cb.moveToPosition(6, i, 4, i);
        }
        System.out.println("Проверка хода пешек на два шага вперед " + (check ? "успешна" : "провалена"));

        // Проверка хода белой пешки назад

        cb = Main.buildBoard();
    }
}
