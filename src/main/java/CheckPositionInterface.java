public interface CheckPositionInterface {
    default boolean checkPos(int pos) {
        return pos >=0 && pos <= 7;
    }
}
