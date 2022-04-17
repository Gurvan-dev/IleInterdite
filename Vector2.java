public class Vector2 {

    public static Vector2 zero = new Vector2(0, 0);
    public static Vector2 up = new Vector2(0, 1);
    public static Vector2 down = new Vector2(0, -1);
    public static Vector2 left = new Vector2(-1, 0);
    public static Vector2 right = new Vector2(1, 0);

    public int x, y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 copy() {
        return new Vector2(x, y);
    }

    public void add(Vector2 other) {
        x += other.x;
        y += other.y;
    }

    public void mult(int scalar) {
        x *= scalar;
        y *= scalar;
    }

    public static Vector2 FromDir(Dir dir) {
        switch (dir) {
            case CENTRE:
                return Vector2.zero;
            case HAUT:
                return Vector2.up;
            case BAS:
                return Vector2.down;
            case GAUCHE:
                return Vector2.left;
            default:
                return Vector2.right;
        }
    }
}