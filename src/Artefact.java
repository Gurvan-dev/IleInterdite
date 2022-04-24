import java.awt.image.*;

public class Artefact extends Objet {
    public final CaseType type;

    public Artefact(CaseType type) {
        this.type = type;
    }

    public boolean autoUse() {
        return false;
    }

    public String toString() {
        return "Artefact (" + type + ")";
    }

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        return false; // On ne peut pas utiliser cet objet.
    }

    public BufferedImage toImage() {
        int i = CaseType.ToInt(type);

        return ImageLoader.objet_artefacts[i - 1];
    }
}
