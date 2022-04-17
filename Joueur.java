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

    /* Implémentation des actions possibles */

    public void Asseche(Dir direction) {
        Vector2 caseAssecher = pos.copy();
        caseAssecher.add(Vector2.FromDir(direction));
        if (modele.plateau.GetCase(caseAssecher.x, caseAssecher.y).Asseche()) {
            modele.EffectueAction(numJoueur, 1);
        }
    }

    public void Move(Dir direction) {
        if (direction == Dir.CENTRE)
            return;
        modele.EffectueAction(numJoueur, 1);
    }
}
