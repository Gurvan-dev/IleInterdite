import java.awt.image.*;

public class SacSable extends Objet {

    public boolean autoUse() {
        return false;
    }

    public BufferedImage toImage() {
        return ImageLoader.objet_sac_sable;
    }

    public String toString() {
        return "Sac de sable";
    }

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        return c.Asseche();
    }

}
