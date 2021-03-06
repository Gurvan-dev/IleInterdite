import java.util.*;
import java.awt.image.*;

public class Clef extends Objet {
    public final CaseType type;

    public boolean autoUse() {
        return false;
    }

    public Clef(CaseType type) {
        this.type = type;
    }

    public String toString() {
        return "Clef (" + type + ")";
    }

    public BufferedImage toImage() {
        int i = CaseType.ToInt(type);

        return ImageLoader.objet_clef[i - 1];
    }

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        if (c.pos.distance(j.pos) > 0 || c.type != type || m.GetCurrentJoueur() != j || m.GetNombreAction() <= 0)
            return false;

        ArrayList<Clef> clefMemeType = new ArrayList<Clef>();
        clefMemeType.add(this);
        for (Objet o : j.inventaire) {
            if (o != this && o instanceof Clef) {
                Clef co = (Clef) o;
                if (co.type == this.type)
                    clefMemeType.add(co);
            }
        }
        System.out.println(clefMemeType.size());
        if (clefMemeType.size() >= GameSettings.PARTIE_NOMBRE_CLEF_POUR_ARTEFACT) { // On peut penser a un paramètre
                                                                                    // statique
            // nombre de clef pour ouvrir
            for (int i = 0; i < GameSettings.PARTIE_NOMBRE_CLEF_POUR_ARTEFACT; i++) {
                Objet o = clefMemeType.get(i);
                if (o != this) // This sera remove automatiquement a la fin quand on utiliser un objet
                    j.removeItem(o);
            }
            j.ajouteItem(new Artefact(type));
            m.EffectueAction(j.numJoueur, 1);
            return true;
        }
        return false;
    }
}
