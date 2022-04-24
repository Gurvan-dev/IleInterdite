import java.awt.*;
import java.util.*;

public enum CaseType {
    HELIPORT,
    NORMAL,
    BISOUS,
    CHOCOLAT,
    PIERRE,
    TERRE;

    private static Random rand = new Random();

    public static Color colorFromEnum(CaseType t) {
        switch (t) {
            case HELIPORT:
                return Color.RED;
            case BISOUS:
                return Color.ORANGE;
            case CHOCOLAT:
                return Color.LIGHT_GRAY;
            case PIERRE:
                return Color.BLACK;
            case TERRE:
                return Color.darkGray;
            default: // Case NORMAL
                return Color.WHITE;

        }
    }

    public static CaseType FromInt(int i) {
        switch (i) {
            case 0:
                return HELIPORT;
            case 1:
                return BISOUS;
            case 2:
                return CHOCOLAT;
            case 3:
                return PIERRE;
            case 4:
                return TERRE;
            default:
                return NORMAL;
        }
    }

    public static int ToInt(CaseType c) {
        switch (c) {
            case HELIPORT:
                return 0;
            case BISOUS:
                return 1;
            case CHOCOLAT:
                return 2;
            case PIERRE:
                return 3;
            case TERRE:
                return 4;
            default:
                return 5;
        }
    }

    public static CaseType random() {
        int n = rand.nextInt(5);
        return FromInt(n);
    }

}
