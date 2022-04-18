import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class VuePlateau extends JPanel implements Observer {

    private final int CASE_TAILLE_TOTALE;

    private Modele modele;
    private CaseButton[] Cases;

    public VuePlateau(Modele modele) {

        this.modele = modele;
        modele.addObserver(this);
        this.setLayout(new GridLayout(modele.plateau.taille.x, modele.plateau.taille.y, VueMain.CASE_ESPACE,
                VueMain.CASE_ESPACE));
        /* INITIALISATION DES CASES */
        CASE_TAILLE_TOTALE = VueMain.CASE_TAILLE + VueMain.CASE_ESPACE;
        Cases = new CaseButton[modele.plateau.taille.x * modele.plateau.taille.y];
        for (int i = 0; i < Cases.length; i++) {
            Cases[i] = new CaseButton(modele.plateau.posFromNumber(i), modele);
            Cases[i].setSize(VueMain.CASE_TAILLE, VueMain.CASE_TAILLE);
            this.add(Cases[i]);
        }

        /* Finition */
        Vector2 tailleTotale = modele.plateau.taille.copy();
        tailleTotale.multiply(CASE_TAILLE_TOTALE);
        Dimension dim = new Dimension(tailleTotale.x, tailleTotale.y);
        this.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Plateau.PLATEAU_COULEUR);
        g.fillRect(0, 0, modele.plateau.taille.x * (CASE_TAILLE_TOTALE), modele.plateau.taille.y * CASE_TAILLE_TOTALE);
    }

    public void Update() {
        super.repaint();
    }

}
