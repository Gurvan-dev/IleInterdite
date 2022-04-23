public class Pilote extends Joueur {
    private boolean UsedPower = false;

    public Pilote(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        super(modele, tailleInventaire, pos, numJoueur);
    }

    @Override
    public void StartTurn() {
        UsedPower = false;
    }

    @Override
    public boolean Move(Vector2 newPos, boolean forceMove) {
        boolean canMove = newPos.distance(this.pos) == 1 || forceMove;
        if (!canMove && newPos.distance(this.pos) > 1 && !UsedPower) { // On ne peut utiliser le pouvoir qu'une seule
                                                                       // fois par tour.
            UsedPower = true;
            canMove = true;
        }
        if (canMove) {
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
}
