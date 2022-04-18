import java.awt.*;
import java.util.*;

public enum CaseType {
    HELIPORT,
    NORMAL,
    FEU,
    VENT,
    PIERRE,
    TERRE;

    private static Random rand = new Random();

    public static Color colorFromEnum(CaseType t) {
        switch (t) {
            case HELIPORT:
                return Color.RED;
            case NORMAL:
                return Color.WHITE;
            case FEU:
                return Color.ORANGE;
            case VENT:
                return Color.LIGHT_GRAY;
            case PIERRE:
                return Color.BLACK;
            default: // Case TERRE
                return Color.YELLOW;

        }
    }

    public static CaseType FromInt(int i) {
        switch (i) {
            case 0:
                return HELIPORT;
            case 1:
                return NORMAL;
            case 2:
                return FEU;
            case 3:
                return VENT;
            case 4:
                return PIERRE;
            default:
                return PIERRE;
        }
    }

    public static CaseType random() {
        int n = rand.nextInt(5);
        return FromInt(n);
    }
}
