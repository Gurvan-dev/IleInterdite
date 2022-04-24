import java.awt.image.*;

public class Helicoptere extends Objet {
    /*
     * L'objet Hélicoptère a deux utilisation :
     * Utilisée sur un héliport, il vérifie si la partie est gagnée i.e. tout les
     * joueurs se trouvent sur l'héliport et tout les artefacts sont actuellement en
     * leurs possession.
     * Sa deuxième utilisation est de permettre de déplacer un Joueur ou groupe de
     * joueur d'une case vers une autre.
     */

    public boolean autoUse() {
        return false;
    }

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        if (c.type == CaseType.HELIPORT && m.GetNombreJoueurSurCase(c.pos) == GameSettings.JOUEUR_NOMBRE) {
            m.VerifieVictoire();
        } else {
            return j.Move(c.pos, true);
        }

        return false;
    }

    public String toString() {
        return "Hélicoptère";
    }

    public BufferedImage toImage() {
        return ImageLoader.objet_helicoptere;
    }
}
