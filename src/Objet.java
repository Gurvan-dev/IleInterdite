import java.awt.image.*;

public abstract class Objet {
    public abstract boolean autoUse(); // Retourne si oui ou non on doit utiliser l'objet automatiquement quand on le
                                       // pioche

    public abstract String toString();

    public abstract BufferedImage toImage();

    // Utilise l'objet par le joueur j a la position pos.
    // Retourne vraie si l'objet a pu être utilisé. Si c'est le cas, la carte est
    // retiré.
    public abstract boolean utiliseObjet(Joueur j, Case c, Modele m);
}
