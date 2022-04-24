import java.awt.image.*;

public class Messager extends Joueur {

    public Messager(Modele modele, int tailleInventaire, Vector2 pos, int numJoueur) {
        super(modele, tailleInventaire, pos, numJoueur);
    }

    @Override
    public boolean DonneObjet(int objNumber, Joueur other) {
        if (inventaire.size() <= objNumber
                || objNumber < 0
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

    @Override
    public BufferedImage getIcon() {
        return ImageLoader.personnage_messager_icone;
    }

}
