import java.awt.image.*;

public class Explorateur extends Joueur {

    public Explorateur(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        super(modele, tailleInventaire, pos, numJoueur);
    }

    @Override
    public boolean Move(Vector2 newPos, boolean forceMove) {
        if (newPos.distance(this.pos) == 1 || newPos.isDiag(this.pos) || forceMove) {
            if (!modele.plateau.InBounds(newPos))
                return false;
            if (modele.plateau.GetCase(newPos.x, newPos.y).etat == CaseEtat.SUBMERGE)
                return false;

            int coutAction = forceMove ? 0 : 1;
            if (modele.GetNombreAction() < coutAction)
                return false;
            pos = newPos;
            modele.EffectueAction(numJoueur, coutAction);
            return true;
        }
        return false;
    }

    @Override
    public BufferedImage getIcon() {
        return ImageLoader.personnage_explorateur_icone;
    }

    @Override
    public BufferedImage getPion() {
        return ImageLoader.personnage_explorateur_pion;
    }

}
