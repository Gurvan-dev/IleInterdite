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

    public void removeItem(int objNumber) {
        inventaire.remove(objNumber);
    }

    public Objet getItem(int objNumber) {
        return objNumber >= 0 && objNumber < inventaire.size() ? inventaire.get(objNumber) : null;
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
        return Move(newPos, false);
    }

    /**
     * @param newPos    : La position a laquelle déplacer le joueur
     * @param forceMove : Si vrai, on va vérifier si la distance est correcte entre
     *                  la nouvelle position du joueur et la position actuelle.
     *                  Sinon, on va simplement déplacer le joueur sur la case (Si
     *                  bien sûr la case n'est pas submergé).
     *                  Si forcemove est vrai, on ne va également pas coûter
     *                  d'action au Joueur.
     * @return Vrai si le joueur a pu se déplacer a la position demandé
     */
    public boolean Move(Vector2 newPos, boolean forceMove) {
        if (newPos.distance(this.pos) == 1 || forceMove) {
            if (!modele.plateau.InBounds(newPos))
                return false;
            if (modele.plateau.GetCase(newPos.x, newPos.y).etat == CaseEtat.SUBMERGE)
                return false;
            pos = newPos;
            int coutAction = forceMove ? 0 : 1;
            modele.EffectueAction(numJoueur, coutAction);
            return true;
        }
        return false;
    }

    /**
     * 
     * @param objNumber Le numéro de l'objet dans l'inventaire a utilisé
     * @param pos       La position a laquelle utiliser l'objet
     * @return
     */
    public boolean UtiliseObjet(int objNumber, Vector2 pos) {
        if (inventaire.size() <= objNumber || objNumber < 0)
            return false;
        boolean res = inventaire.get(objNumber).utiliseObjet(this, modele.plateau.GetCase(pos), modele);
        if (res)
            inventaire.remove(objNumber);
        modele.EffectueAction(numJoueur, 0); // Les actions n'ont pas de coût mais on appelle pour update tout
                                             // correctement

        return res;
    }

    public boolean DonneObjet(int objNumber, Joueur other) {
        if (inventaire.size() <= objNumber
                || objNumber < 0
                || !other.pos.equals(this.pos)
                || modele.GetNombreAction() <= 0
                || other == this
                || modele.GetCurrentJoueur() != this)
            return false;
        boolean result = other.ajouteItem(inventaire.get(objNumber));
        if (result) {
            inventaire.remove(objNumber);
            modele.EffectueAction(numJoueur, 1);
        }
        return result;
    }

}
