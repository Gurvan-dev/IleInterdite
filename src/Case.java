import java.awt.image.*;

public class Case {

    public final Vector2 pos;
    public CaseEtat etat;
    public final CaseType type;
    private final int graphicsID;

    public Case(Vector2 pos, CaseType type) {
        graphicsID = ImageLoader.getCaseID(type);
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

    public BufferedImage GetImage() {
        switch (type) {
            case HELIPORT:
                return ImageLoader.terrain_heliport;
            case BISOUS:
                return ImageLoader.terrain_temple[0][graphicsID];
            case CHOCOLAT:
                return ImageLoader.terrain_temple[1][graphicsID];
            case ROYAL:
                return ImageLoader.terrain_temple[2][graphicsID];
            case GEOMETRIQUE:
                return ImageLoader.terrain_temple[3][graphicsID];
            default:
                return ImageLoader.terrain_random[graphicsID];
        }
    }
}
