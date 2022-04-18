import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class VuePlateau extends JPanel implements Observer {

    private Modele modele;
    private final int CASE_TAILLE_TOTALE;

    public VuePlateau(Modele modele) {

        this.modele = modele;
        modele.addObserver(this);

        /* TMP LOAD IMAGE */

        CASE_TAILLE_TOTALE = VueMain.CASE_TAILLE + VueMain.CASE_ESPACE;

        Vector2 tailleTotale = modele.plateau.taille.copy();
        tailleTotale.multiply(CASE_TAILLE_TOTALE);
        Dimension dim = new Dimension(tailleTotale.x, tailleTotale.y);
        this.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Plateau.PLATEAU_COULEUR);
        g.fillRect(0, 0, modele.plateau.taille.x * (CASE_TAILLE_TOTALE), modele.plateau.taille.y * CASE_TAILLE_TOTALE);
        /* DESSIN DES CASES */
        for (int x = 0; x < modele.plateau.taille.x; x++)
            for (int y = 0; y < modele.plateau.taille.y; y++) {
                g.setColor(modele.plateau.GetCase(x, y).GetColor());
                Vector2 topL = new Vector2(x * (CASE_TAILLE_TOTALE), y * (CASE_TAILLE_TOTALE));
                g.fillRect(topL.x, topL.y, VueMain.CASE_TAILLE, VueMain.CASE_TAILLE);
            }

        /* DESSIN DES PIONS */
        /*
         * On a ici un code qui peut paraître compliqué pour quelque chose de très
         * simple mais il a en réalité bien une utilité
         * Il sert aussi a redimensioner l'affichage des pions pour permettre
         * l'affichage de plusieurs pions sur une même case.
         */
        ArrayList<Integer> joueurPose = new ArrayList<Integer>();// On garde en mémoire tout les joueurs déjà posé
        for (Joueur j : modele.joueurs) {
            Vector2 pos = new Vector2(j.pos.x, j.pos.y);
            if (!joueurPose.contains(j.numJoueur)) {
                ArrayList<Joueur> joueursSurCase = modele.GetJoueursOnCase(pos); // On va regarder tout les joueurs sur
                                                                                 // la case
                pos.multiply(CASE_TAILLE_TOTALE);
                int tailleDessin = VueMain.CASE_TAILLE / joueursSurCase.size(); // La taille du dessin du pion est
                                                                                // proportionelle au nombre de pions sur
                                                                                // ce point
                for (int i = 0; i < joueursSurCase.size(); i++) { // On va placer tout les joueurs sur une même case en
                                                                  // un seul coup
                    Vector2 posFinal = pos.copy();
                    posFinal.plus(new Vector2(tailleDessin * i, 0));
                    g.drawImage(ImageLoader.pion1, posFinal.x, posFinal.y, posFinal.x + tailleDessin,
                            posFinal.y + tailleDessin, 0, 0,
                            ImageLoader.pion1.getWidth(),
                            ImageLoader.pion1.getHeight(), null);
                    joueurPose.add(joueursSurCase.get(i).numJoueur);
                }
            }
        }
    }

    public void Update() {
        super.repaint();
    }

}
