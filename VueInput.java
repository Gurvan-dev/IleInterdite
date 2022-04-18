import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueInput extends JPanel implements Observer {
    private Modele modele;
    private JButton boutonHaut, boutonBas, boutonGauche, boutonDroite;
    private JButton boutonEndTurn;
    private JTextField joueurActuel;
    private JTextField actionRestante;

    public VueInput(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);
        int CASE_TAILLE_TOTALE = VueMain.CASE_ESPACE + VueMain.CASE_TAILLE;

        // Note : BoutonHaut et boutonBas sont inversé pour l'utilisateur car la
        // vuePlateau est 'inversée', i.e. le bas correspond au haut.
        /* Gestion des boutons & inputs */
        boutonHaut = new JButton("v");
        boutonBas = new JButton("^");
        boutonDroite = new JButton(">");
        boutonGauche = new JButton("<");
        boutonEndTurn = new JButton("End Turn");
        joueurActuel = new JTextField("");
        actionRestante = new JTextField("");

        boutonHaut.addActionListener(e -> {
            modele.Haut();
        });
        boutonBas.addActionListener(e -> {
            modele.Bas();
        });
        boutonGauche.addActionListener(e -> {
            modele.Gauche();
        });
        boutonDroite.addActionListener(e -> {
            modele.Droite();
        });
        boutonEndTurn.addActionListener(e -> {
            modele.EndTurn();
        });

        /* Gestion du texte et info */
        actionRestante.setEditable(false);
        joueurActuel.setEditable(false);
        joueurActuel.setSize(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE);
        Update();

        /* On ajoute tout en composants */
        this.add(boutonBas);
        this.add(boutonHaut);
        this.add(boutonGauche);
        this.add(boutonDroite);
        this.add(boutonEndTurn);
        this.add(joueurActuel);
        this.add(actionRestante);

        /* Fintions : Gestion de taille */

        Dimension dim = new Dimension(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE * modele.plateau.taille.y);
        // currentInfo.setPreferredSize(new Dimension(VueMain.INPUT_WIDTH,
        // CASE_TAILLE_TOTALE * 3));

        // currentInfo.setSize((int) dim.getWidth(), CASE_TAILLE_TOTALE * 10);
        this.setPreferredSize(dim);

    }

    public void Update() {
        joueurActuel.setText("Joueur actuel : " + modele.GetCurrentPlayer());
        actionRestante.setText("Action restante : " + modele.GetNombreAction());

    }

}
