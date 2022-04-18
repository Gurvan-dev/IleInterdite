import java.awt.*;

public class Case {

    public final Vector2 pos;
    public CaseEtat etat;
    public final CaseType type;

    public Case(Vector2 pos, CaseType type) {
        this.pos = pos;
        this.etat = CaseEtat.NORMAL;
        this.type = type;
    }

    public Case(Vector2 pos, int type_id) {
        this(pos, CaseType.FromInt(type_id));
    }

    public Case(Vector2 pos) {
        this(pos, CaseType.random());
    }

    /**
     * Inonde
     * 
     * @return {boolean} Vrai si la case a pu être inondé, faux sinon (si elle est
     *         déjà submergé)
     */
    public boolean Inonde() {
        switch (this.etat) {
            case NORMAL:
                etat = CaseEtat.INONDE;
                return true;
            case INONDE:
                etat = CaseEtat.SUBMERGE;
                return true;
            default:
                return false;
        }
    }

    /**
     * Asseche
     * 
     * @return {boolean} Vrai si la case a pu être asséché, faux sinon (si elle est
     *         déjà submergé ou déjà seche.)
     */
    public boolean Asseche() {
        switch (this.etat) {
            case INONDE:
                etat = CaseEtat.NORMAL;
                return true;
            default:
                return false;
        }
    }

    public Color GetColor() {
        if (this.etat == CaseEtat.SUBMERGE) // On renvoie une couleur transparente
            return new Color(0, 0, 0, 0);
        Color c = CaseType.colorFromEnum(type);
        if (this.etat == CaseEtat.INONDE) {
            c = new Color((int) (c.getRed() * 0.5), (int) (c.getGreen() * 0.5),
                    Math.min((int) (c.getBlue() * 1.5), 255));
        }
        return c;
    }
}
