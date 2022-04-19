import java.util.*;

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

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        System.out.print("Utilisation d'une clef de type " + type + " Sur une case de type " + c.type);
        if (c.type != type)
            return false;
        System.out.println("Case de même type");
        ArrayList<Clef> clefMemeType = new ArrayList<Clef>();
        clefMemeType.add(this);
        for (Objet o : j.inventaire) {
            if (o != this && o instanceof Clef) {
                Clef co = (Clef) o;
                if (co.type == this.type)
                    clefMemeType.add(co);
            }
        }
        if (clefMemeType.size() >= Modele.PARTIE_NOMBRE_CLEF_POUR_ARTEFACT) { // On peut penser a un paramètre statique
                                                                              // nombre de clef pour ouvrir
            for (Objet o : clefMemeType)
                if (o != this) // This sera remove automatiquement a la fin quand on utiliser un objet
                    j.removeItem(o);
            System.out.println("Artefact récupéré : " + type);
            // TODO : j.addItem(artefact)
            return true;
        }
        return false;
    }
}
