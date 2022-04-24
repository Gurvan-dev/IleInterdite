public enum Dir {
    BAS,
    DROITE,
    HAUT,
    GAUCHE,
    CENTRE;

    public static Dir FromInt(int i) {
        switch (i) {
            case 0:
                return BAS;
            case 1:
                return DROITE;
            case 2:
                return HAUT;
            case 3:
                return GAUCHE;
            default:
                return CENTRE;
        }
    }
}
