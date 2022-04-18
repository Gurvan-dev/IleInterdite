public class Joueur {
    Vector2 pos;
    Objet[] inventaire;
    final Modele modele;
    final int numJoueur;

    public Joueur(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        this.modele = modele;
        this.numJoueur = numJoueur;
        inventaire = new Objet[tailleInventaire];
        this.pos = pos;
    }

    /*
     * Implémentation des actions possibles
     * Toutes les actions retournes un booléen vrai si l'action à pu être éfféctué
     * et faux sinon.
     * Les actions sont responsable d'appeler le modèle avec la fonction
     * 'EffectueAction', avec le cout d'action correspondant
     */

    public boolean Asseche(Dir direction) {
        Vector2 caseAssecher = pos.copy();
        caseAssecher.add(Vector2.FromDir(direction));
        if (modele.plateau.InBounds(caseAssecher) && modele.plateau.GetCase(caseAssecher.x, caseAssecher.y).Asseche()) {
            modele.EffectueAction(numJoueur, 1);
            return true;
        }
        return false;
    }

    public boolean Move(Dir direction) {
        if (direction == Dir.CENTRE)
            return false;

        Vector2 newPos = pos.copy();
        newPos.add(Vector2.FromDir(direction));
        if (!modele.plateau.InBounds(newPos))
            return false;
        if (modele.plateau.GetCase(newPos.x, newPos.y).etat == CaseEtat.SUBMERGE)
            return false;
        pos = newPos;
        modele.EffectueAction(numJoueur, 1);
        return true;
    }
}
