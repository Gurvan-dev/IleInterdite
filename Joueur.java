import java.util.*;

public class Joueur {
    Vector2 pos;
    ArrayList<Objet> inventaire;
    final Modele modele;
    final int numJoueur;
    final int tailleInventaire;

    public Joueur(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        this.modele = modele;
        this.numJoueur = numJoueur;
        this.tailleInventaire = tailleInventaire;
        this.inventaire = new ArrayList<Objet>();
        this.pos = pos;
    }

    public boolean ajouteItem(Objet o) {
        if (inventaire.size() < tailleInventaire) {
            inventaire.add(o);
            return true;
        }
        return false;

    }

    public void removeItem(Objet o) {
        if (inventaire.contains(o))
            inventaire.remove(o);
    }

    /*
     * Implémentation des actions possibles
     * Toutes les actions retournes un booléen vrai si l'action à pu être éfféctué
     * et faux sinon.
     * Les actions sont responsable d'appeler le modèle avec la fonction
     * 'EffectueAction', avec le cout d'action correspondant
     */

    public boolean Asseche(Vector2 caseAssecher) {
        if (caseAssecher.distance(pos) <= 1) {
            if (modele.plateau.InBounds(caseAssecher)
                    && modele.plateau.GetCase(caseAssecher.x, caseAssecher.y).Asseche()) {
                modele.EffectueAction(numJoueur, 1);
                return true;
            }
        }
        return false;
    }

    public boolean Move(Vector2 newPos) {
        if (newPos.distance(this.pos) == 1) {
            if (!modele.plateau.InBounds(newPos))
                return false;
            if (modele.plateau.GetCase(newPos.x, newPos.y).etat == CaseEtat.SUBMERGE)
                return false;
            pos = newPos;
            modele.EffectueAction(numJoueur, 1);
            return true;
        }
        return false;
    }

    public boolean UtiliseObjet(int objNumber, Vector2 pos) {
        if (inventaire.size() <= objNumber || objNumber < 0)
            return false;
        boolean res = inventaire.get(objNumber).utiliseObjet(this, modele.plateau.GetCase(pos));
        modele.EffectueAction(numJoueur, 0); // Les actions n'ont pas de coût mais on appelle pour update tout
                                             // correctement
        return res;
    }

}
