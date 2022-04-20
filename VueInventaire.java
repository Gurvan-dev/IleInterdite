import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class VueInventaire extends JPanel implements Observer {
    Modele modele;
    InventaireButton[] inventaire;
    Joueur currentJoueur;

    public VueInventaire(Modele modele, Joueur currentJoueur) {
        this.modele = modele;
        this.currentJoueur = currentJoueur;
        this.setLayout(
                new GridLayout(GameSettings.JOUEUR_TAILLE_INVENTAIRE, 1, GameSettings.OBJET_ESPACE,
                        GameSettings.OBJET_ESPACE));
        modele.addObserver(this);

        inventaire = new InventaireButton[GameSettings.JOUEUR_TAILLE_INVENTAIRE];
        for (int i = 0; i < inventaire.length; i++) {
            inventaire[i] = new InventaireButton(modele, currentJoueur, i);
            inventaire[i].setSize(GameSettings.OBJET_WIDTH, GameSettings.OBJET_WIDTH);
            this.add(inventaire[i]);
        }

        Update();

        // Note : Comme le gridlayout stretch ses components, on doit bien vérifier
        // qu'il fait la hauteur que l'on souhaiterais
        Dimension dim = new Dimension(GameSettings.OBJET_WIDTH,
                GameSettings.OBJET_WIDTH * GameSettings.JOUEUR_TAILLE_INVENTAIRE);
        this.setPreferredSize(dim);

    }

    public void Update() {
        /*
         * if (currentJoueur != null) {
         * int i = 0;
         * for (Objet o : currentJoueur.inventaire) {
         * inventaire[i].setText("" + o);
         * i++;
         * }
         * for (int j = i; j < inventaire.length; j++) {
         * inventaire[j].setText("-----------------");
         * }
         * }
         */

    }
}
