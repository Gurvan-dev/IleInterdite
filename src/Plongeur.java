import java.awt.image.*;

public class Plongeur extends Joueur {

    public Plongeur(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        super(modele, tailleInventaire, pos, numJoueur);
    }

    @Override
    public boolean Move(Vector2 newPos, boolean forceMove) {
        if (newPos.distance(this.pos) == 1 || forceMove) {
            if (!modele.plateau.InBounds(newPos))
                return false;

            CaseEtat etat = modele.plateau.GetCase(newPos).etat;

            int coutAction = forceMove ? 0 : 1;
            if ((etat == CaseEtat.INONDE || etat == CaseEtat.SUBMERGE) && modele.GetNombreAction() > 0)
                coutAction = 0;
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
        return ImageLoader.personnage_plongeur_icone;
    }

    @Override
    public BufferedImage getPion() {
        return ImageLoader.personnage_plongeur_pion;
    }

}
