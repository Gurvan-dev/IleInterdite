import java.awt.image.*;

public class MonteeEau extends Objet {
    public boolean autoUse() {
        return true;
    }

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        m.MonteeEau();
        return true;
    }

    public String toString() {
        return "Montee Eau";
    }

    public BufferedImage toImage() {
        return ImageLoader.objet_montee_eau;
    }

}
