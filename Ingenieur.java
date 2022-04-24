import java.awt.image.*;

public class Ingenieur extends Joueur {
    private boolean UsedPower = false;
    private boolean freeAsseche = false;

    public Ingenieur(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        super(modele, tailleInventaire, pos, numJoueur);
    }

    @Override
    public void StartTurn() {
        UsedPower = false;
        freeAsseche = false;
    }

    @Override
    public boolean Move(Vector2 newPos, boolean forceMove) {
        freeAsseche = false;
        return super.Move(newPos, forceMove);
    }

    @Override
    public boolean Asseche(Vector2 caseAssecher) {
        if (caseAssecher.distance(pos) <= 1) {
            if (modele.plateau.InBounds(caseAssecher)
                    && modele.plateau.GetCase(caseAssecher.x, caseAssecher.y).Asseche()) {
                int coutAction = freeAsseche ? 0 : 1;
                if (freeAsseche) {
                    freeAsseche = false;
                    UsedPower = false;
                } else if (!UsedPower) {
                    freeAsseche = true;
                }
                modele.EffectueAction(numJoueur, coutAction);
                return true;
            }
        }
        return false;
    }

    @Override
    public BufferedImage getIcon() {
        return ImageLoader.personnage_ingenieur_icone;
    }

}
