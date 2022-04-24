import java.awt.*;
import java.util.*;

public enum CaseType {
    HELIPORT,
    NORMAL,
    BISOUS,
    CHOCOLAT,
    ROYAL,
    GEOMETRIQUE;

    private static Random rand = new Random();

    public static CaseType FromInt(int i) {
        switch (i) {
            case 0:
                return HELIPORT;
            case 1:
                return BISOUS;
            case 2:
                return CHOCOLAT;
            case 3:
                return ROYAL;
            case 4:
                return GEOMETRIQUE;
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
            case ROYAL:
                return 3;
            case GEOMETRIQUE:
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
