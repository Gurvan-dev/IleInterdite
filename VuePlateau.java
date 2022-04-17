import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VuePlateau extends JPanel implements Observer {

    private Modele modele;
    private final int CASE_TAILLE_TOTALE;

    public VuePlateau(Modele modele) {

        this.modele = modele;
        modele.addObserver(this);

        CASE_TAILLE_TOTALE = VueMain.CASE_TAILLE + VueMain.CASE_ESPACE;

        Vector2 tailleTotale = modele.plateau.taille.copy();
        tailleTotale.mult(CASE_TAILLE_TOTALE);
        Dimension dim = new Dimension(tailleTotale.x, tailleTotale.y);
        this.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        g.setColor(Plateau.PLATEAU_COULEUR);
        g.fillRect(0, 0, modele.plateau.taille.x * (CASE_TAILLE_TOTALE), modele.plateau.taille.y * CASE_TAILLE_TOTALE);
        for (int x = 0; x < modele.plateau.taille.x; x++)
            for (int y = 0; y < modele.plateau.taille.y; y++) {
                g.setColor(modele.plateau.GetCase(x, y).GetColor());
                Vector2 topL = new Vector2(x * (CASE_TAILLE_TOTALE), y * (CASE_TAILLE_TOTALE));
                g.fillRect(topL.x, topL.y, VueMain.CASE_TAILLE, VueMain.CASE_TAILLE);
            }
    }

    public void update() {

        super.repaint();
    }

}
